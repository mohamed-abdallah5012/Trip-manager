package com.example.tripreminder2021.ui.activities.register;

import android.content.Context;
import android.widget.ProgressBar;
import com.example.tripreminder2021.pojo.User;
import com.example.tripreminder2021.requests.ConnectionAvailability;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterPresenter implements IRegisterContract.Presenter {


    private IRegisterContract.View toRegisterView;
    private Context context;
    private ConnectionAvailability checkInternetConnection;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;


    public RegisterPresenter(IRegisterContract.View toRegisterView, Context context) {
        this.toRegisterView = toRegisterView;
        this.context = context;
        checkInternetConnection = new ConnectionAvailability(context);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressBar = new ProgressBar(context);
    }

    @Override
    public void register(String name, String email, String password) {
        if (!checkInternetConnection.isConnectingToInternet()) {
            toRegisterView.onInternetDisconnected();
            return;
        } else {
            toRegisterView.onShowProgressBar(true);
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            User user = new User(name, email, password);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(firebaseAuth.getCurrentUser().getUid()).setValue(user)
                                    .addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            toRegisterView.onRegisterSuccess();
                                        } else {
                                            toRegisterView.onRegisterError(task1.getException().getMessage());
                                        }
                                        toRegisterView.onShowProgressBar(false);
                                    });

                            toRegisterView.onShowProgressBar(false);
                            firebaseAuth.signOut();

                        }
                    });
        }
    }
}

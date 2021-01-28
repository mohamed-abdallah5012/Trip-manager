package com.example.tripreminder2021.ui.activities.login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.tripreminder2021.pojo.User;
import com.example.tripreminder2021.requests.ConnectionAvailability;
import com.facebook.login.LoginResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginPresenter implements ILoginContract.Presenter{


    private ILoginContract.View toLoginView;
    private Context context ;
    private ConnectionAvailability checkInternetConnection;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private ProgressBar progressBar;



    public  LoginPresenter(ILoginContract.View toLoginView, Context context)
    {
        this.toLoginView =toLoginView;
        this.context=context;
        checkInternetConnection=new ConnectionAvailability(context);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        progressBar=new ProgressBar(context);
    }

    @Override
    public void login(String email, String password) {

        if (!checkInternetConnection.isConnectingToInternet()){
            toLoginView.onInternetDisconnected();
            return;
        }
        else {
            toLoginView.onShowProgressBar(true);
            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()){
                            toLoginView.onLoginSuccess();
                        }
                        else {
                            toLoginView.onLoginError(task.getException().getMessage());
                        }
                        toLoginView.onShowProgressBar(false);
                    });
        }
    }

    @Override
    public void loginWithGoogle(String email, String password) {

    }

    @Override
    public void loginWithFacebook(LoginResult result) {
        toLoginView.onShowProgressBar(true);
        AuthCredential credential = FacebookAuthProvider.getCredential(result.getAccessToken().getToken());
        firebaseAuth.signInWithCredential(credential).
                addOnCompleteListener((Activity) context, task -> {

                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    User facebookUser=new User(user.getDisplayName(),user.getEmail(),"Login using facebook");
                    databaseReference.child(firebaseAuth.getCurrentUser().getUid()).
                            setValue(facebookUser);
                });
    }
    @Override
    public void loginWithTwitter(String email, String password) {

    }

    @Override
    public void restPassword(String email) {

    }

}

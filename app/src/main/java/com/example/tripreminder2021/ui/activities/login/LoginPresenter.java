package com.example.tripreminder2021.ui.activities.login;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.example.tripreminder2021.pojo.User;
import com.example.tripreminder2021.requests.ConnectionAvailability;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginPresenter implements ILoginContract.Presenter{


    private ILoginContract.View toLoginView;
    private ILoginContract.UserData xyz;
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
    public  LoginPresenter(ILoginContract.UserData xyz)
    {
        this.xyz =xyz;
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
    public void loginWithGoogle(GoogleSignInAccount signInAccount) {
        toLoginView.onShowProgressBar(true);
        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) context, task -> {
                    if (task.isSuccessful()) {

                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        databaseReference.child("Users").child(user.getUid()).child("email").setValue(user.getEmail());
                        databaseReference.child("Users").child(user.getUid()).child("name").setValue(user.getDisplayName());
                        databaseReference.child("Users").child(user.getUid()).child("password").setValue("Login with google");

                        toLoginView.onShowProgressBar(false);
                        toLoginView.onLoginSuccess();
                        xyz.userData(user.getEmail(),user.getUid());
                    } else {
                        toLoginView.onLoginError(task.getException().getMessage());

                    }
                    toLoginView.onShowProgressBar(false);
                });
    }

    @Override
    public void loginWithFacebook(AuthCredential credential) {
        toLoginView.onShowProgressBar(true);
        firebaseAuth.signInWithCredential(credential).
                addOnCompleteListener((Activity) context, task -> {

                    if (task.isSuccessful())
                    {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        User facebookUser=new User(user.getDisplayName(),user.getEmail(),"Login using facebook");
                        databaseReference.child("Users").child(user.getUid()).
                                setValue(facebookUser);
                        toLoginView.onShowProgressBar(false);
                        toLoginView.onLoginSuccess();
                        xyz.userData(user.getEmail(),user.getUid());
                    }
                    else {
                        toLoginView.onLoginError(task.getException().getMessage());
                    }
                    toLoginView.onShowProgressBar(false);

                });
    }
    @Override
    public void loginWithTwitter(AuthCredential credential) {
       toLoginView.onShowProgressBar(true);
       firebaseAuth.signInWithCredential(credential).
               addOnCompleteListener((Activity) context, task -> {

                   if (task.isSuccessful())
                   {
                       FirebaseUser user = firebaseAuth.getCurrentUser();
                       User facebookUser=new User(user.getDisplayName(),user.getEmail(),"Login using twitter");
                       databaseReference.child("Users").child(user.getUid()).
                               setValue(facebookUser);
                       toLoginView.onShowProgressBar(false);
                       toLoginView.onLoginSuccess();
                       xyz.userData(user.getEmail(),user.getUid());
                   }
                   else {
                       toLoginView.onLoginError(task.getException().getMessage());
                   }
                   toLoginView.onShowProgressBar(false);

               });
    }
    @Override
    public void restPassword(String email) {
        toLoginView.onShowProgressBar(true);
        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful())
                        toLoginView.onEmailSentSuccess();
                    else
                        toLoginView.onEmailSentError(task.getException().getMessage());

                    toLoginView.onShowProgressBar(false);
                });

    }

}

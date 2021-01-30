package com.example.tripreminder2021.ui.activities.login;

import com.example.tripreminder2021.pojo.User;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;

public interface ILoginContract {

     interface View{
         void onLoginSuccess();
         void onLoginError(String errorMessage);
         void onInternetDisconnected();
         void onShowProgressBar(boolean val);
         void onEmailSentSuccess();
         void onEmailSentError(String message);

    }
     interface Presenter{
         void login(String email,String password);
         void loginWithGoogle(GoogleSignInAccount signInAccount);
         void loginWithFacebook(AuthCredential credential);
         void loginWithTwitter(AuthCredential credential);
         void restPassword(String email);
    }
}

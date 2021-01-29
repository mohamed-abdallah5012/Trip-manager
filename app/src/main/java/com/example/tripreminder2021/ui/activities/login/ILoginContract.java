package com.example.tripreminder2021.ui.activities.login;

import com.example.tripreminder2021.pojo.User;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.AuthCredential;

public interface ILoginContract {

    public interface View{
        public void onLoginSuccess();
        public void onLoginError(String errorMessage);
        public void onInternetDisconnected();
        public void onShowProgressBar(boolean val);
        public void onEmailSentSuccess();
        public void onEmailSentError(String message);

    }
    public interface Presenter{
        public void login(String email,String password);
        public void loginWithGoogle(GoogleSignInAccount signInAccount);
        public void loginWithFacebook(AuthCredential credential);
        public void loginWithTwitter(AuthCredential credential);
        public void restPassword(String email);
    }
    public interface UserData{
        public void userData(String email,String uid);
    }
}

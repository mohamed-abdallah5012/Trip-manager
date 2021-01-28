package com.example.tripreminder2021.ui.activities.login;

import com.facebook.login.LoginResult;

public interface ILoginContract {

    public interface View{
        public void onLoginSuccess();
        public void onLoginError(String errorMessage);
        public void onInternetDisconnected();
        public void onShowProgressBar(boolean val);

    }
    public interface Presenter{
        public void login(String email,String password);
        public void loginWithGoogle(String email,String password);
        public void loginWithFacebook(LoginResult result);
        public void loginWithTwitter(String email,String password);
        public void restPassword(String email);
    }

}

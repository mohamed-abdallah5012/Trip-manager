package com.example.tripreminder2021.ui.activities.register;

public interface IRegisterContract {

     interface View{
         void onRegisterSuccess();
         void onRegisterError(String errorMessage);
         void onInternetDisconnected();
         void onShowProgressBar(boolean val);

    }
     interface Presenter{
         void register(String name,String email,String password);
    }

}

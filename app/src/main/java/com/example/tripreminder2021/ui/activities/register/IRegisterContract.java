package com.example.tripreminder2021.ui.activities.register;

public interface IRegisterContract {

    public interface View{
        public void onRegisterSuccess();
        public void onRegisterError(String errorMessage);
        public void onInternetDisconnected();
        public void onShowProgressBar(boolean val);

    }
    public interface Presenter{
        public void register(String name,String email,String password);
    }

}

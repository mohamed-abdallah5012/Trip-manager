package com.example.tripreminder2021.dataValidation;

public interface DataValidator {

    interface Presenter {
        public boolean validateName(String name);

        public boolean validateEmail(String name);

        public boolean validatePassword(String name);
    }
    interface View{
        public void onNameIsValidated(boolean val,String message);
        public void onEmailIsValidated(boolean val,String message);
        public void onPasswordIsValidated(boolean val,String message);
    }
}

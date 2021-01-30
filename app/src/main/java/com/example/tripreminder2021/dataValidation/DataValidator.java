package com.example.tripreminder2021.dataValidation;

public interface DataValidator {

    interface Presenter {
         boolean validateName(String name);

         boolean validateEmail(String name);

         boolean validatePassword(String name);
    }
    interface View{
         void onNameIsValidated(boolean val,String message);
         void onEmailIsValidated(boolean val,String message);
         void onPasswordIsValidated(boolean val,String message);
    }
}

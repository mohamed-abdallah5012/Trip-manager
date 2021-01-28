package com.example.tripreminder2021.dataValidation;

import android.content.Context;
import android.text.TextUtils;

public class ValidationServices implements DataValidator.Presenter {


    private DataValidator.View myView;
    private Context context;


    public ValidationServices(DataValidator.View myView,Context context)
    {
        this.myView=myView;
        this.context=context;
    }

    @Override
    public boolean validateName(String name) {
        if (name.isEmpty()) {
            myView.onNameIsValidated(false,"Enter a valid User_Name");
            return false;
        } else {
            myView.onNameIsValidated(true,"valid");
            return true;
        }
    }
    @Override
    public boolean validateEmail(String email) {
        if (email.isEmpty() || !isValidEmail(email)) {
            myView.onEmailIsValidated(false,"Enter valid email address");
            return false;
        } else {
            myView.onEmailIsValidated(true,"valid");
            return true;
        }
    }
    @Override
    public boolean validatePassword(String password) {
        if (password.isEmpty()) {
            myView.onPasswordIsValidated(false,"Enter the password");
            return false;
        } else {
            myView.onPasswordIsValidated(true,"valid");
            return true;
        }
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}
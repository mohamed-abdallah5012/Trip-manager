package com.example.tripreminder2021.ui.activities.register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tripreminder2021.MainActivity;
import com.example.tripreminder2021.R;
import com.example.tripreminder2021.ui.activities.login.Activity_Login;
import com.example.tripreminder2021.ui.activities.login.LoginPresenter;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_Register extends AppCompatActivity implements IRegisterContract.View {


    private IRegisterContract.Presenter getPresenter;
    private ProgressBar progressBar;


    private Button btn_register;
    private EditText Username,Email,Password;
    private TextInputLayout inputLayoutName,inputLayoutEmail, inputLayoutPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register);

        Username=(EditText)findViewById(R.id.register_username);
        Email=(EditText)findViewById(R.id.register_email);
        Password=(EditText)findViewById(R.id.register_password);
        btn_register=(Button)findViewById(R.id.btn_register);

        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_register_username);
        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_register_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_register_password);


        progressBar = findViewById(R.id.login_progress);
        progressBar.setVisibility(View.GONE);


        getPresenter=new RegisterPresenter(this,this);
        register_action();

    }

    @Override
    public void onRegisterSuccess() {
        Toast.makeText(this, "Sign up Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, Activity_Login.class));
    }

    @Override
    public void onRegisterError(String errorMessage) {
        Toast.makeText(this, "Please Try again", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInternetDisconnected() {
        Toast.makeText(this, "Sorry!! No Internet Connection", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onShowProgressBar(boolean val) {
        if (val)
            progressBar.setVisibility(View.VISIBLE);
        else
            progressBar.setVisibility(View.GONE);
    }

    private void register_action() {

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitForm();
            }
        });
    }
    /**
     * Validating form
     */
    private void submitForm() {

        if (!validateName()) {
            return;
        }

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        try_to_register();
    }


    private void try_to_register()
    {
        String username=Username.getText().toString().trim();
        String email=Email.getText().toString().trim();
        String password=Password.getText().toString().trim();
        getPresenter.register(username,email,password);
    }


    /**
     * Validating name
     */
    private boolean validateName() {
        if (Username.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError("Enter a valid User_Name");
            requestFocus(Username);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }
    /**
     * Validating email
     */
    private boolean validateEmail() {
        String email = Email.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError("Enter valid email address");
            requestFocus(Email);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }
    /**
     * Validating password
     */
    private boolean validatePassword() {
        if (Password.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError("Enter the password");
            requestFocus(Password);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }
    /**
     * Validating email
     */
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}

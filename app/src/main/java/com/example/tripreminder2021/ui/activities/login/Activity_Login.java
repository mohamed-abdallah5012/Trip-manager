package com.example.tripreminder2021.ui.activities.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripreminder2021.MainActivity;
import com.example.tripreminder2021.R;
import com.example.tripreminder2021.config.SharedPreferencesManager;
import com.example.tripreminder2021.requests.ConnectionAvailability;
import com.example.tripreminder2021.ui.activities.register.Activity_Register;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;
import com.google.android.material.textfield.TextInputLayout;

public class Activity_Login extends AppCompatActivity implements ILoginContract.View{


    private static final int TIME_DELAY = 2000;
    private static long back_pressed;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    private ProgressBar progressBar;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;

    private TextView tv_register_link;
    private Button btn_login;
    private LoginButton loginWithFacebook;

    private CheckBox checkBox;
    private CallbackManager callbackManager;

    private SharedPreferencesManager sharedPreferencesManager;

    private ILoginContract.Presenter getPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__login);


        mEmailView = findViewById(R.id.login_email);
        mPasswordView = findViewById(R.id.login_password);
        inputLayoutEmail = findViewById(R.id.input_layout_login_email);
        inputLayoutPassword = findViewById(R.id.input_layout_login_password);
        btn_login = findViewById(R.id.btn_login);
        tv_register_link = findViewById(R.id.tv_reg_link);

        checkBox = (findViewById(R.id.activity_login_checkBox));

        progressBar = findViewById(R.id.login_progress);
        progressBar.setVisibility(View.GONE);


        getPresenter=new LoginPresenter(this,this);

        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);


        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        checkBox.setOnClickListener(view -> saveUserData());

        loginWithFacebook=findViewById(R.id.login_with_facebook);
        callbackManager = CallbackManager.Factory.create();
        loginWithFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getPresenter.loginWithFacebook(loginResult);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login_form || id == EditorInfo.IME_NULL) {
                    return true;
                }
                return false;
            }
        });

        login_action();
        register_action();

    }


    private void saveUserData()
    {
        if (checkBox.isChecked()) {
            sharedPreferencesManager.setUserData(mEmailView.getText().toString(), mPasswordView.getText().toString());
        }
    }

    private void getUserData()
    {
        String[] array = sharedPreferencesManager.getUSerData();
        mEmailView.setText(array[0]);
        mPasswordView.setText(array[1]);
        checkBox.setChecked(true);
    }

    private void login_action() {

        btn_login.setOnClickListener(new View.OnClickListener() {
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

        if (!validateEmail()) {
            return;
        }

        if (!validatePassword()) {
            return;
        }

        try_to_login();
    }
    private void try_to_login()
    {
        String email=mEmailView.getText().toString().trim();
        String password=mPasswordView.getText().toString().trim();
        getPresenter.login(email,password);
    }
    private boolean validateEmail() {
        String email = mEmailView.getText().toString().trim();

        if (email.isEmpty() || !isValidEmail(email)) {
            inputLayoutEmail.setError("Enter valid email address");
            requestFocus(mEmailView);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    /**
     * Validating Email
     */
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    /**
     * Validating password
     */
    private boolean validatePassword() {
        if (mPasswordView.getText().toString().trim().isEmpty()) {
            inputLayoutPassword.setError("Enter the password");
            requestFocus(mPasswordView);
            return false;
        } else {
            inputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }
    /**
     * Register _link
     * Go to Register Activity
     */
    private void register_action() {
        tv_register_link.setOnClickListener(v -> {

            startActivity(new Intent(this, Activity_Register.class));
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferencesManager = new SharedPreferencesManager(this);
        if(sharedPreferencesManager.isUserLogin())
        {
            Intent intent = new Intent(Activity_Login.this, Activity_Login.class);
            startActivity(intent);
        }
        if (sharedPreferencesManager.isUserDataSaved()) {
            getUserData();
        }

    }

    @SuppressLint("ResourceType")
    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            }
        } else {

            Toast.makeText(getApplicationContext(),"Click Again To Exit",Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

}

    @Override
    public void onLoginSuccess() {
        // go to the next screen
        Toast.makeText(this, "Login Successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onLoginError(String errorMessage) {

        Toast.makeText(this, "Please Try again\n"+errorMessage, Toast.LENGTH_LONG).show();
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
}

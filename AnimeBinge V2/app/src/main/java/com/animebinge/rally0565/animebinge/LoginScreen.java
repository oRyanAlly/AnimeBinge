package com.animebinge.rally0565.animebinge;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin, btnSignUp;
    private EditText etEmail, etPassword;
    private TextView tvForgotPassword;
    DatabaseHelper dhDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        etEmail = (EditText) findViewById(R.id.txtEmail);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        tvForgotPassword = (TextView) findViewById(R.id.tvResetPassword);
        dhDatabaseHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                btnLogin.setVisibility(View.GONE);
                tvForgotPassword.setVisibility(View.GONE);
                btnSignUp.setText("CREATE ACCOUNT");
                String sPassword = etPassword.getText().toString();
                String sEmail = etEmail.getText().toString();


                if (!isValidEmail(sEmail)) {
                    etEmail.setError("Please enter a valid email address");
                }
                if (sPassword.length() < 8 || sPassword == null) {
                    etPassword.setError("Please enter a password greater than 8 characters");
                }
                if (etEmail.getError() == null || etPassword.getError() == null) {
                    btnLogin.setVisibility(View.VISIBLE);
                    tvForgotPassword.setVisibility(View.VISIBLE);
                    btnSignUp.setText("SIGN UP");
                    etEmail.setText("");
                    etPassword.setText("");
                    addEmailandPass(sEmail, sPassword);
                }
            }
        });
        tvForgotPassword.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent iResetPassword = new Intent(LoginScreen.this, ResetPassword.class);
                startActivity(iResetPassword);
            }
        });
    }

    // When the Login Button is clicked,
    //Verify that the user has entered a valid email
    // and strong password in order to login successfully
    @Override
    public void onClick(View v) {
        String sEmail = etEmail.getText().toString().trim();
        String sPassword = etPassword.getText().toString().trim();
        Cursor member = dhDatabaseHelper.getMember(sEmail);
        Log.d("TAG", member.getString(2));
        String storedEmail = member.getString(2);
        if (!isValidEmail(etEmail.getText()) || sEmail == null) {
            etEmail.setError("Invalid E-mail");

        } else if (storedEmail.equals(sEmail)) {
            String storedPassword = member.getString(3);
            if(sPassword.length() < 8 || sPassword == null) {
                etPassword.setError("Invalid Password");
            } else if(storedPassword.equals(sPassword)) {
                Intent iHomePage = new Intent(LoginScreen.this, HomePage.class);
                startActivity(iHomePage);
            }
        }
    }
    //Source: https://stackoverflow.com/questions/22348212/android-check-if-an-email-address-is-valid-or-not
    //Checks to see if a email matches the according pattern
    public final static boolean isValidEmail(CharSequence email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    //Goes into the DatabaseHelper and calls a specific function
    //to add the username and password to the members database
    public void addEmailandPass(String email, String password) {
        boolean insertDATA = dhDatabaseHelper.addEmailandPass(email, password);

        if (insertDATA) {
            Toast.makeText(this, "Data Successfully Inserted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data was not successfully inserted", Toast.LENGTH_SHORT).show();
        }
    }
}

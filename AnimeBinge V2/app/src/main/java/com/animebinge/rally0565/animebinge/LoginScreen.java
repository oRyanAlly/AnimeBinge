package com.animebinge.rally0565.animebinge;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private String storedEmail;
    DatabaseHelper dhDatabaseHelper;
    SQLiteDatabase animeDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        //Define all Widgets
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        etEmail = (EditText) findViewById(R.id.txtEmail);
        etPassword = (EditText) findViewById(R.id.txtPassword);
        tvForgotPassword = (TextView) findViewById(R.id.tvResetPassword);
        //this.deleteDatabase("Anime.db");
        dhDatabaseHelper = new DatabaseHelper(this);
        dhDatabaseHelper.getWritableDatabase();
        //Attached Listeners to Specified Buttons
        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                //When Sign Up is clicked, hide some of the widgets
                //Rename one, then perform validation(for now)
                //Then return back to home screen
                if (btnSignUp.getText().toString() == "SIGN UP") {
                    etEmail.setText("");
                    etPassword.setText("");
                    etEmail.setError(null);
                    etPassword.setError(null);
                }
                btnLogin.setVisibility(View.GONE);
                tvForgotPassword.setVisibility(View.GONE);
                etEmail.setFocusable(true);
                btnSignUp.setText("CREATE ACCOUNT");
                String sPassword = etPassword.getText().toString();
                String sEmail = etEmail.getText().toString();
                Cursor member = dhDatabaseHelper.getMember(sEmail);
                if (member.getCount() == 0) {
                    if (!isValidEmail(sEmail)) {
                        etEmail.setError("Please enter a valid email address");
                    }
                    if (sPassword.length() < 8 || sPassword == null) {
                        etPassword.setError("Please enter a password greater than 8 characters");
                    }
                } else if (member.getString(2).equals(etEmail.getText().toString()))
                {
                    etEmail.setError("Email already exists!");
                }
                if (etEmail.getError() == null && etPassword.getError() == null) {
                    btnLogin.setVisibility(View.VISIBLE);
                    tvForgotPassword.setVisibility(View.VISIBLE);
                    btnSignUp.setText("SIGN UP");
                    etEmail.setText("");
                    etPassword.setText("");
                    addEmailandPass(sEmail, sPassword);
                } else {
                    etEmail.setError("Please enter a valid email address");
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
        member.moveToFirst();
        if (member.getCount() != 0) {
            if (member.getString(2) != null) {
                storedEmail = member.getString(2);

                if (!isValidEmail(etEmail.getText()) || etEmail.getText() == null) {
                    etEmail.setError("Invalid E-mail");

                } else if (storedEmail.equals(sEmail)) {
                    String storedPassword = member.getString(3);

                    if (sPassword.length() < 8 || sPassword == null) {
                        etPassword.setError("Invalid Password");
                    } else if (storedPassword.equals(sPassword)) {
                        Intent iHomePage = new Intent(LoginScreen.this, HomePage.class);
                        startActivity(iHomePage);
                    }
                }
            } else {
                etEmail.setError("Email does not exist");
            }
        } else {
            Toast.makeText(this, "This account does not exist",
                    Toast.LENGTH_SHORT).show();
            etEmail.setError("Email not found!");
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
            Toast.makeText(this, "Account has been created", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Account was not successfully created.", Toast.LENGTH_SHORT).show();
        }
    }
}
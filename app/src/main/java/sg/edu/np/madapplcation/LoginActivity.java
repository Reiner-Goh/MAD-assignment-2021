package sg.edu.np.madapplcation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private final String TAG = "MADApp";
    private final String FILENAME = "MainActivity.java";

    private TextView register;
    private String email,password;
    private EditText editEmail, editPassword;
    private DBHelper DB;
    private CheckBox rmbBox;
    private Button login;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean rememberMe;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView) findViewById(R.id.textView_NewUser);
        register.setOnClickListener(this);

        login = (Button) findViewById(R.id.loginButton);
        login.setOnClickListener(this);

        editEmail = (EditText) findViewById(R.id.editText_Email);
        editPassword = (EditText) findViewById(R.id.editText_Password);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();
        DB = new DBHelper(this);
        rmbBox = (CheckBox)findViewById(R.id.checkBoxRmb);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        rememberMe = loginPreferences.getBoolean("rememberMe", false);
        if (rememberMe) {
            editEmail.setText(loginPreferences.getString("email", ""));
            editPassword.setText(loginPreferences.getString("password", ""));
            rmbBox.setChecked(true);
        }

        TextView newUser = findViewById(R.id.textView_NewUser);
        newUser.setOnTouchListener((view, motionevent) -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            return false;
        });

        TextView showPass = findViewById(R.id.textView_ShowPass);
        showPass.setOnClickListener(v -> {

            if(showPass.getText().equals("Hide password"))
            {
                showPass.setText("Reveal password");
                editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            else if(showPass.getText().equals("Reveal password"))
            {
                showPass.setText("Hide password");
                editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        email = editEmail.getText().toString();
        password = editPassword.getText().toString();

        /*login = findViewById(R.id.loginButton);
        login.setOnClickListener(view -> {

            username = editUsername.getText().toString();
            password = editPassword.getText().toString();

            if (username.equals("") || password.equals(""))
                Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkuserpass = DB.checkusernamepassword(username, password);
                if (checkuserpass) {
                    Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
                    if (rmbBox.isChecked()) {
                        loginPrefsEditor.putBoolean("rememberMe", true);
                        loginPrefsEditor.putString("username", username);
                        loginPrefsEditor.putString("password", password);
                    } else {
                        loginPrefsEditor.clear();
                    }
                    loginPrefsEditor.commit();
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView_NewUser:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.loginButton:
                UserLogin();
                break;
        }
    }

    private void UserLogin(){
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (email.isEmpty()){
            editEmail.setError("Email is required!");
            editEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please enter a valid email!");
            editEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editPassword.setError("Please input a password!");
            editPassword.requestFocus();
            return;
        }

        if (password.length() < 6){
            editEmail.setError("Min password length should be 6 characters!");
            editEmail.requestFocus();
            return;

        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    if (rmbBox.isChecked()) {
                        loginPrefsEditor.putBoolean("rememberMe", true);
                        loginPrefsEditor.putString("email", email);
                        loginPrefsEditor.putString("password", password);
                    } else {
                        loginPrefsEditor.clear();
                    }
                    loginPrefsEditor.commit();
                    startActivity(new Intent(LoginActivity.this, InfoActivity.class));
                    progressBar.setVisibility(View.GONE);

                }else{
                    Toast.makeText(LoginActivity.this, "Failed to login! Please check your credentials!", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }

            }
        });
    }
}

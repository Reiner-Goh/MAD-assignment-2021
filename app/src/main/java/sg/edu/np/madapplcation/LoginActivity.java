package sg.edu.np.madapplcation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "MADApp";
    private final String FILENAME = "MainActivity.java";

    private String username,password;
    private EditText editUsername, editPassword;
    private DBHelper DB;
    private CheckBox rmbBox;
    private Button login;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean rememberMe;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = (EditText) findViewById(R.id.editText_UserName);
        editPassword = (EditText) findViewById(R.id.editText_Password);
        DB = new DBHelper(this);
        rmbBox = (CheckBox)findViewById(R.id.checkBoxRmb);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        rememberMe = loginPreferences.getBoolean("rememberMe", false);
        if (rememberMe) {
            editUsername.setText(loginPreferences.getString("username", ""));
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

            if(showPass.getText().equals("Hide"))
            {
                showPass.setText("Show");
                editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            else if(showPass.getText().equals("Show"))
            {
                showPass.setText("Hide");
                editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        login = findViewById(R.id.loginButton);
        login.setOnClickListener(view -> {

            username = editUsername.getText().toString();
            password = editPassword.getText().toString();

            if (username.equals("") || password.equals(""))
                Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkuserpass = DB.checkusernamepassword(username, password);
                if (checkuserpass) {
                    Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ListActivity.class);
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
        });
    }
}

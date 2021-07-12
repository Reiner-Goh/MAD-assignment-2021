package sg.edu.np.madapplcation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = "MADApp";
    private final String FILENAME = "MainActivity.java";

    EditText username, password;
    DBHelper DB;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.editText_UserName);
        password = (EditText) findViewById(R.id.editText_Password);
        DB = new DBHelper(this);

        SharedPreferences preferences = getSharedPreferences("madapp", Context.MODE_PRIVATE);
        boolean loginSkip = preferences.getBoolean("skip", false);

        CheckBox rmbBox = findViewById(R.id.checkBoxRmb);
        rmbBox.setChecked(loginSkip);
        rmbBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit().putBoolean("skip", isChecked).apply();
            remembered();
        });

        if (loginSkip) {
            remembered();
        }

        TextView newUser = findViewById(R.id.textView_NewUser);
        newUser.setOnTouchListener((view, motionevent) -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            return false;
        });

        Button loginButton = findViewById(R.id.button);
        loginButton.setOnClickListener(view -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (user.equals("") || pass.equals(""))
                Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                if (checkuserpass) {
                    Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void remembered() {
        Intent intent = new Intent(LoginActivity.this, ListActivity.class);
        startActivity(intent);
    }
}

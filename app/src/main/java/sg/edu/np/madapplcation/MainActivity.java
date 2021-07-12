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

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MADApp";
    private final String FILENAME = "MainActivity.java";

    EditText username, password;
    DBHelper DB;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            return false;
        });

        Button loginButton = findViewById(R.id.button);
        loginButton.setOnClickListener(view -> {

            String user = username.getText().toString();
            String pass = password.getText().toString();

            if (user.equals("") || pass.equals(""))
                Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else {
                Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                if (checkuserpass) {
                    Toast.makeText(MainActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void remembered() {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }
}

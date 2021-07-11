package sg.edu.np.madapplcation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MADApp";
    private String FILENAME = "MainActivity.java";

    private TextView newUser;
    private Button loginButton;
    EditText username, password;
    DBHelper DB;


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

        newUser = findViewById(R.id.textView_NewUser);
        newUser.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionevent) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return false;
            }
        });

        loginButton = findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if (checkuserpass == true) {
                        Toast.makeText(MainActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public boolean isValidCredentials(String username, String password) {
//        UserData dbData = dbHelper.checkusername(username);
//        if(dbData.getUsername().equals(username) && dbData.getPassword().equals(password))
//        {
//            return true;
//        }

        return false;
    }

    private void remembered() {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }
}

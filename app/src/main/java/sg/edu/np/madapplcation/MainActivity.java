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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        newUser.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View view, MotionEvent motionevent){
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                return false;
            }
        });

        loginButton = findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                EditText etUsername = findViewById(R.id.editText_UserName);
                EditText etPassword = findViewById(R.id.editText_Password);

                if(isValidCredentials(etUsername.getText().toString(), etPassword.getText().toString()))
                {
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Valid Account", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Invalid Username / Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public boolean isValidCredentials(String username, String password){
        //soon once db is completed
/*        if()
        {
            return true;
        }*/

        return false;
    }

    private void remembered() {
        Intent intent = new Intent(MainActivity.this, ListActivity.class);
        startActivity(intent);
    }

}
package sg.edu.np.madapplcation;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private String user, pass, repass;
    private EditText regEditUsername, regEditPassword, regEditRePassword;
    private Button signup, signin;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        regEditUsername = (EditText) findViewById(R.id.editText_RegUserName);
        regEditPassword = (EditText) findViewById(R.id.editText_RegPassword);
        regEditRePassword = (EditText) findViewById(R.id.editText_RegRePassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        TextView showPass2 = findViewById(R.id.textView_ShowPass2);
        showPass2.setOnClickListener(v -> {

            if(showPass2.getText().equals("Hide passwords"))
            {
                showPass2.setText("Reveal passwords");
                regEditPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                regEditRePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            else if(showPass2.getText().equals("Reveal passwords"))
            {
                showPass2.setText("Hide passwords");
                regEditPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                regEditRePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });

        signup.setOnClickListener(view -> {
            user = regEditUsername.getText().toString();
            pass = regEditPassword.getText().toString();
            repass = regEditRePassword.getText().toString();

            if(user.equals("")||pass.equals("")||repass.equals(""))
                Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else{
                if(pass.equals(repass)){
                    Boolean checkuser = DB.checkusername(user);
                    if(!checkuser){
                        Boolean insert = DB.insertData(user, pass);
                        if(insert){
                            UserData dbUserData = new UserData();
                            dbUserData.setUsername(regEditUsername.getText().toString());
                            dbUserData.setPassword(regEditPassword.getText().toString());
                            DB.addUser(dbUserData);
                            Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(RegisterActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                }
            } });

        signin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });
    }
}

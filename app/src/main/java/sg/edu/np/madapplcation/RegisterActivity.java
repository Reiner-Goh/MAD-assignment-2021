package sg.edu.np.madapplcation;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
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
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView signup, signin;
    private EditText regEditUsername, regEditEmail, regEditPassword;
    private ProgressBar progressBar;
    //private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        regEditUsername = (EditText) findViewById(R.id.editText_RegUserName);
        regEditEmail = (EditText) findViewById(R.id.editText_RegEmail);
        regEditPassword = (EditText) findViewById(R.id.editText_RegPassword);

        signup = (Button) findViewById(R.id.btnsignup);
        signup.setOnClickListener(this);

        signin = (Button) findViewById(R.id.btnsignin);
        signin.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Show/Hide password
        TextView showPass2 = findViewById(R.id.textView_ShowPass2);
        showPass2.setOnClickListener(v -> {

            if(showPass2.getText().equals("Hide passwords"))
            {
                showPass2.setText("Reveal passwords");
                regEditPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                //regEditRePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            else if(showPass2.getText().equals("Reveal passwords"))
            {
                showPass2.setText("Hide passwords");
                regEditPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                //regEditRePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsignin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.btnsignup:
                registerUser();
                break;

        }

    }

    // Username/Email/Password conditions
    private void registerUser(){
        String email = regEditEmail.getText().toString().trim();
        String username = regEditUsername.getText().toString().trim();
        String password = regEditPassword.getText().toString().trim();

        if (username.isEmpty()){
            regEditUsername.setError("Please input a username!");
            regEditUsername.requestFocus();
            return;
        }

        if (email.isEmpty()){
            regEditEmail.setError("Please input an email!");
            regEditEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            regEditPassword.setError("Please input a password!");
            regEditPassword.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            regEditEmail.setError("Please provide a valid email!");
            regEditEmail.requestFocus();
            return;
        }

        if (password.length() < 6){
            regEditPassword.setError("Min password length should be 6 characters!");
            regEditPassword.requestFocus();
            return;

        }

        // Firebase registering authentication
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {

                    if (task.isSuccessful()){
                        UserData user = new UserData(username, email);

                        FirebaseDatabase.getInstance().getReference("UserData")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(task1 -> {

                                    if (task1.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "User had been registered successfully!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                        finish();
                                    }else{
                                        Toast.makeText(RegisterActivity.this, "Registration Failed! Try again!", Toast.LENGTH_LONG).show();
                                    }
                                    progressBar.setVisibility(View.GONE);
                                }) ;

                    }else{
                        Toast.makeText(RegisterActivity.this, "Registration Failed! Try again!", Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

}

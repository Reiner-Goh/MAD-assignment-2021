package sg.edu.np.madapplcation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;

import java.util.Timer;
import java.util.TimerTask;

public class InfoActivity extends AppCompatActivity {

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        SharedPreferences preferences = getSharedPreferences("skipIntro", Context.MODE_PRIVATE);
        boolean toSkip = preferences.getBoolean("skip", false);

        CheckBox skipSwitch = findViewById(R.id.checkBoxSkip);
        skipSwitch.setChecked(toSkip);
        skipSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit().putBoolean("skip", isChecked).apply();
            goNext();
        });

        if (toSkip) {
            goNext();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                goNext();
            }
        }, 30000);
    }

    private void goNext() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        Intent intent = new Intent(InfoActivity.this, ListActivity.class);
        startActivity(intent);
        finish();
    }
}
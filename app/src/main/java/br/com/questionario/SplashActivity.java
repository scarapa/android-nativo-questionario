package br.com.questionario;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.content.Context;

import Database.DataBaseStructureDBHandler;

public class SplashActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBaseStructureDBHandler db = new DataBaseStructureDBHandler(SplashActivity.this);

        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarMainActivity();
            }
        }, 2500);
    }

    private void mostrarMainActivity() {
        Intent intent = new Intent( SplashActivity.this,MainActivity.class );
        startActivity(intent);
        finish();
    }
}
package br.com.questionario;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import Database.Database;
import android.content.Context;

public class SplashActivity extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Database database = new Database( getBaseContext() , 1 );

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
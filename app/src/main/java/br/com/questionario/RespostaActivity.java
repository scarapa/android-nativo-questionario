package br.com.questionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class RespostaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta);

        SharedPreferences sharedPreferences = getPreferences( Context.MODE_PRIVATE );
        String teste = sharedPreferences.getString("pergunta",null);
        System.out.println(teste);

    }
}
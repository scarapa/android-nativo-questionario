package br.com.questionario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import Model.Pergunta;
import Model.PerguntaDBHandler;
import Model.RespostaDBHandler;

public class RespostaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resposta);

        montarTela();

    }

    public void montarTela(){
        RespostaDBHandler respostaDB = new RespostaDBHandler( RespostaActivity.this );
        List<String> lista = respostaDB.buscarResultado();
        String questoes = lista.get(0);
        String acertos = lista.get(1);
        String nota = lista.get(2);

        TextView textQuestoes = (TextView) findViewById(R.id.textView2);
        textQuestoes.setText( questoes );
        TextView textAcerto = (TextView) findViewById(R.id.textView4);
        textAcerto.setText( acertos );
        TextView textNota = (TextView) findViewById(R.id.textView6);
        textNota.setText( nota );
    }
}
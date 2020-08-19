package br.com.questionario;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import Model.Pergunta;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    String jsonPerguntas ="{'dados':[{'id':1,'pergunta':'A linguagem oficial para desenvolvimento Android Nativo pela Google é a Kotlin','resposta':1},{'id':2,'pergunta':'O processo de publicação do aplicativo na Google Play é gratuito','resposta':0},{'id':3,'pergunta':'O Brasil possui uma população de quase 210 milhões','resposta':1},{'id':4,'pergunta':'Flutter é uma dos frameworks de desenvolvimento mobile; verdadeiro','resposta':1},{'id':5,'pergunta':'A linguagem de programação do Flutter é o Dart','resposta':1},{'id':6,'pergunta':'O Flutter possui interoperabilidade e pode ter projetos em Java e Dart','resposta':0},{'id':7,'pergunta':'React-Native é uma plataforma para desenvolvimento de aplicativos móveis','resposta':1},{'id':8,'pergunta':'O Kotlin possui interoperabilidade oque possibilita implementar projetos em Java e Kotlin','resposta':1},]}";
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int perguntaNumero = 0;
        int editPerguntaNumeroValor;
        List<ArrayList> listaResposta ;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editPerguntaNumero = (EditText) findViewById(R.id.editPerguntaNumero);

        if( (editPerguntaNumero.getText() == null) || (editPerguntaNumero.getText().toString().trim().isEmpty()) ){
            editPerguntaNumeroValor = 0;
        }else{
            editPerguntaNumeroValor = Integer.parseInt( editPerguntaNumero.getText().toString() );
        }
        editPerguntaNumero.setText( Integer.toString( editPerguntaNumeroValor) );
        montarTela( editPerguntaNumeroValor );
    }

    public void montarTela(int perguntaNumero){
        List<Pergunta> lista = buscarPerguntas();
        String bancoPergunta = lista.get(perguntaNumero).getPergunta();
        String bancoResposta = lista.get(perguntaNumero).getResposta();

        EditText editPerguntaPergunta = (EditText) findViewById(R.id.pergunta);
        EditText editPerguntaResposta = (EditText) findViewById(R.id.editRespostaCerta);

        editPerguntaPergunta.setText(bancoPergunta.toString());
        editPerguntaResposta.setText(bancoResposta.toString());

    }

    public void responder(View view){
        radioGroup = (RadioGroup) findViewById(R.id.radioResposta);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);


        //Toast.makeText(MainActivity.this, radioButton.getText(), Toast.LENGTH_SHORT).show();
    }


    public List<Pergunta> buscarPerguntas(){
        List<Pergunta> lista = new ArrayList<Pergunta>();
        Pergunta pergunta1 = new Pergunta(1,"A linguagem oficial para desenvolvimento Android Nativo pela Google é a Kotlin","verdadeiro");
        lista.add(pergunta1);
        Pergunta pergunta2 = new Pergunta(2,"O processo de publicação do aplicativo na Google Play é gratuito","falso");
        lista.add(pergunta2);
        Pergunta pergunta3 = new Pergunta(3,"O Brasil possui uma população de quase 210 milhões","verdadeiro");
        lista.add(pergunta3);
        Pergunta pergunta4 = new Pergunta(4,"Flutter é uma dos frameworks de desenvolvimento mobile","verdadeiro");
        lista.add(pergunta4);
        Pergunta pergunta5 = new Pergunta(5,"A linguagem de programação do Flutter é o Dart","verdadeiro");
        lista.add(pergunta5);
        Pergunta pergunta6 = new Pergunta(6,"O Flutter possui interoperabilidade e pode ter projetos em Java e Dart","falso");
        lista.add(pergunta6);
        Pergunta pergunta7 = new Pergunta(7,"React-Native é uma plataforma para desenvolvimento de aplicativos móveis","verdadeiro");
        lista.add(pergunta7);
        Pergunta pergunta8 = new Pergunta(8,"O Kotlin possui interoperabilidade oque possibilita implementar projetos em Java e Kotlin","verdadeiro");
        lista.add(pergunta8);
        return lista;
    }


}
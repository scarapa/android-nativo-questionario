package br.com.questionario;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Database.Database;
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

        Database database = new Database(this,1);

        List<Pergunta> lista = buscarPerguntas();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editPerguntaNumero = (EditText) findViewById(R.id.editPerguntaNumero);
        EditText editArrayTamanho = (EditText) findViewById(R.id.editArrayTamanho);
        editArrayTamanho.setText( String.valueOf( lista.size() ) );
        editPerguntaNumero.setText("0");
        montarTela( 0 );
    }

    public void montarTela(int perguntaNumero){
        List<Pergunta> lista = buscarPerguntas();
        String bancoId = lista.get(perguntaNumero).getId().toString();
        String bancoPergunta = lista.get(perguntaNumero).getPergunta();
        String bancoResposta = lista.get(perguntaNumero).getResposta();

        EditText editPerguntaNumero = (EditText) findViewById(R.id.editPerguntaNumero);
        EditText editPerguntaPergunta = (EditText) findViewById(R.id.pergunta);
        EditText editPerguntaResposta = (EditText) findViewById(R.id.editRespostaCerta);

        editPerguntaNumero.setText( String.valueOf( perguntaNumero ) );
        editPerguntaPergunta.setText(bancoPergunta.toString());
        editPerguntaResposta.setText(bancoResposta.toString());

    }

    public void responder(View view){
        //TAMANHO MAXIMO DO ARRAY
        EditText editArrayTamanho = (EditText) findViewById(R.id.editArrayTamanho);
        int perguntasTotal = Integer.parseInt( editArrayTamanho.getText().toString() );

        radioGroup = (RadioGroup) findViewById(R.id.radioResposta);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);

        Boolean correcao = verificarQuestao( radioButton.getText().toString().toLowerCase() );

        String mensagem = (correcao)?"Resposta Corretissima !!! ":"ERROU !!!";
        Toast.makeText(MainActivity.this, mensagem, Toast.LENGTH_SHORT).show();

        //GRAVANDO RESPOSTA NA MEMORIA
        gravarResposta(correcao);

        //PEGAR A QUESTAO QUE ESTA NO ID
        EditText editPerguntaNumero = (EditText) findViewById(R.id.editPerguntaNumero);

        // INCREMENTAR
        int perguntaNumero = Integer.parseInt( editPerguntaNumero.getText().toString() ) + 1;

        //montar tela com a proxima questao
        if( perguntaNumero == perguntasTotal ){
            Intent intent = new Intent(this,RespostaActivity.class);
            startActivity(intent);
        }else{
            montarTela(perguntaNumero);
        }

    }

    public void gravarResposta(boolean correcao){
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        EditText editPerguntaNumero = (EditText) findViewById(R.id.editPerguntaNumero);
        String pergunta = "pergunta"+editPerguntaNumero.toString();
        editor.putString( pergunta , String.valueOf(correcao) );
        //editor.putString("pergunta" , "respondido" );
        editor.commit();

    }

    public boolean verificarQuestao(String resposta){
        EditText editPerguntaResposta = (EditText) findViewById(R.id.editRespostaCerta);
        if(resposta.equals(editPerguntaResposta.getText().toString().toLowerCase())){
            return true;
        }
        return false;
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
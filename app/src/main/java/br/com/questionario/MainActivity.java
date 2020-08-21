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
import Model.PerguntaDBHandler;
import Model.Resposta;
import Model.RespostaDBHandler;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int perguntaNumero = 0;
        int editPerguntaNumeroValor;

        //Database database = new Database(this,1);

        //List<Pergunta> lista = buscarPerguntas();
        PerguntaDBHandler perguntaDB = new PerguntaDBHandler( MainActivity.this );
        List<Pergunta> listaPerguntas =  perguntaDB.findListHandler();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editPerguntaNumero = (EditText) findViewById(R.id.editPerguntaNumero);
        EditText editArrayTamanho = (EditText) findViewById(R.id.editArrayTamanho);
        EditText editPerguntaResposta = (EditText) findViewById(R.id.editRespostaCerta);

        editPerguntaNumero.setVisibility( View.INVISIBLE );
        editPerguntaResposta.setVisibility(View.INVISIBLE);
        editArrayTamanho.setVisibility(View.INVISIBLE);
        editArrayTamanho.setText( String.valueOf( listaPerguntas.size() ) );

        editPerguntaNumero.setText("1");
        montarTela( 1);
    }

    public void montarTela(int perguntaNumero){
        PerguntaDBHandler perguntaDB = new PerguntaDBHandler( MainActivity.this );
        Pergunta perguntaBanco = perguntaDB.findHandler(perguntaNumero);

        String bancoId = perguntaBanco.getId().toString();
        String bancoPergunta = perguntaBanco.getPergunta();
        String bancoResposta = perguntaBanco.getResposta();

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
        if( perguntaNumero > perguntasTotal ){
            Intent intent = new Intent(this,RespostaActivity.class);
            startActivity(intent);
        }else{
            montarTela(perguntaNumero);
        }

    }

    public void gravarResposta(boolean correcao){

        EditText editPerguntaNumero = (EditText) findViewById(R.id.editPerguntaNumero);
        Integer questao = Integer.parseInt( editPerguntaNumero.getText().toString() );
        Integer mensagem = (correcao)?1:0;

        Resposta respota = new Resposta(questao , mensagem);

        RespostaDBHandler respostaDB = new RespostaDBHandler( MainActivity.this );
        respostaDB.addHandler(respota);
    }

    public boolean verificarQuestao(String resposta){
        EditText editPerguntaResposta = (EditText) findViewById(R.id.editRespostaCerta);
        if(resposta.equals(editPerguntaResposta.getText().toString().toLowerCase())){
            return true;
        }
        return false;
    }

}
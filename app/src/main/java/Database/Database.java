package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import Model.Pergunta;

import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;

public class Database extends SQLiteOpenHelper {
    private static final String NOME_BD = "questionario";
    private static final int VERSAO_BD = 1;
    private static final String LOG_TAG = "sql";
    private static final String ID = "id";
    private static final String PERGUNTA = "pergunta";
    private static final String RESPOSTA = "resposta";
    /**
     * Mantém rastreamento do contexto que nós podemos carregar SQL
     */
    private final Context contexto;

    public Database(Context context, int i) {
        super(context, NOME_BD, null, VERSAO_BD);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("BANCO - CRIAR");
        db.execSQL("CREATE TABLE IF NOT EXISTS perguntas (id PRIMARY KEY, pergunta TEXT, resposta BOOLEAN);");
        popular();
        System.out.println("BANCO - CONCLUIDO");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int versaoVelha, int versaoNova) {

    }

    public void popular() {
        SQLiteDatabase db = getWritableDatabase();
        try {
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (1,'A linguagem oficial para desenvolvimento Android Nativo pela Google é a Kotlin','true') ;");
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (2,'O processo de publicação do aplicativo na Google Play é gratuito','false') ;");
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (3,'O Brasil possui uma população de quase 210 milhões','true') ;");
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (4,'Flutter é uma dos frameworks de desenvolvimento mobile','true') ;");
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (5,'A linguagem de programação do Flutter é o Dart','true') ;");
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (6,'O Flutter possui interoperabilidade e pode ter projetos em Java e Dart','false') ;");
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (7,'React-Native é uma plataforma para desenvolvimento de aplicativos móveis','true') ;");
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (8,'O Kotlin possui interoperabilidade oque possibilita implementar projetos em Java e Kotlin','true') ;");
        } finally {
            db.close();
        }
    }

    public void fetchById(int id){
        SQLiteDatabase db = getReadableDatabase();
        try{
            db.execSQL("SELECT * FROM perguntas WHERE id ='"+id+"' ");
        }finally {
            db.close();
        }
    }

    public Cursor findAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        try{
            Cursor cursor;
            String[] campos = {ID, PERGUNTA};
            cursor = db.query("perguntas" , campos,null, null, null, null, null, null );
            if(cursor!=null){
                cursor.moveToFirst();
            }
            return cursor;
        }catch ( Exception e){
            Log.d("Error in getting users from DB", e.getMessage());
            return null;
        }finally {
            db.close();
        }
    }

}
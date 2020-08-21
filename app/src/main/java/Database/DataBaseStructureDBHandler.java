package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.concurrent.ExecutionException;

import Model.Pergunta;
import br.com.questionario.Config;

public class DataBaseStructureDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = Config.getDatabaseVersion();
    private static final String DATABASE_NAME = Config.getDatabaseName();

    public DataBaseStructureDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Boolean retorno = this.createTableIfNotExistPergunta(this.getWritableDatabase());
        this.popular(this.getWritableDatabase());
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean createTableIfNotExistPergunta(SQLiteDatabase db){
        boolean returnStatus = true;
        //db.execSQL("DROP TABLE IF EXISTS "+Pergunta.getTableName());
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+ Pergunta.getTableName() +" (" +
                Pergunta.getColumnId() +" INTEGER PRIMARY KEY  ," +
                Pergunta.getColumnPergunta() +" TEXT , " +
                Pergunta.getColumnResposta() +" TEXT );";
        String CREATE_TABLE_RESPOSTAS = "CREATE TABLE IF NOT EXISTS respostas (id INTEGER , resposta INTEGER );";
        try{
            db.execSQL(CREATE_TABLE);
            db.execSQL(CREATE_TABLE_RESPOSTAS);
            db.execSQL(" DELETE FROM respostas ");
            Log.i("DATABASE", "Criada tabela perguntas");
        }catch(SQLiteException e){
            returnStatus = false;
        }

        return returnStatus;
    }

    public boolean popular(SQLiteDatabase db){
        boolean returnStatus = true;
        try {
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (1,'A linguagem oficial para desenvolvimento Android Nativo pela Google é a Kotlin','verdadeiro') ;");
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (2,'O processo de publicação do aplicativo na Google Play é gratuito','falso') ;");
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (3,'O Brasil possui uma população de quase 210 milhões','verdadeiro') ;");
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (4,'Flutter é uma dos frameworks de desenvolvimento mobile','verdadeiro') ;");
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (5,'A linguagem de programação do Flutter é o Dart','verdadeiro') ;");
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (6,'O Flutter possui interoperabilidade e pode ter projetos em Java e Dart','falso') ;");
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (7,'React-Native é uma plataforma para desenvolvimento de aplicativos móveis','verdadeiro') ;");
            db.execSQL("INSERT INTO perguntas (id,pergunta,resposta) VALUES (8,'O Kotlin possui interoperabilidade oque possibilita implementar projetos em Java e Kotlin','verdadeiro') ;");
            Log.i("DATABASE", "POPULADA");
        }catch (SQLiteException e){
            Log.i("DATABASE", "ERRO POPULADA");
            returnStatus = false;
        } finally {
            db.close();
        }
        return returnStatus;
    }

}

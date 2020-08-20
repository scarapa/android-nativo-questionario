package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import Model.Pergunta;
import br.com.questionario.Config;

public class DataBaseStructureDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = Config.getDatabaseVersion();
    private static final String DATABASE_NAME = Config.getDatabaseName();

    public DataBaseStructureDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.createTableIfNotExistPergunta(this.getWritableDatabase());

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
                Pergunta.getColumnId() +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                Pergunta.getColumnPergunta() +" TEXT" +
                Pergunta.getColumnResposta() +" TEXT);";
        try{
            db.execSQL(CREATE_TABLE);
            Log.i("DATABASE", "Criada tabela perguntas");
        }catch(SQLiteException e){
            returnStatus = false;
        }

        return returnStatus;
    }

}

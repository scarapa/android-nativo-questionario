package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.questionario.Config;

public class PerguntaDBHandler extends SQLiteOpenHelper {

    public PerguntaDBHandler(Context context) {
        super(context, Config.getDatabaseName(), null, Config.getDatabaseVersion());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addHandler(Pergunta pergunta){
        long result = 0;

        try{
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String date = sdf.format(new Date());

            ContentValues values = new ContentValues();
            values.put(Pergunta.getColumnPergunta(), pergunta.getPergunta());
            values.put(Pergunta.getColumnResposta(), pergunta.getResposta());

            SQLiteDatabase db = this.getWritableDatabase();
            long dados_id = db.insert(Pergunta.getTableName(), null, values);

            if (dados_id != -1) {
                result = dados_id;
                Log.i("DATABASE", "Pergunta adicionada: "+dados_id);
            }
            db.close();

        }catch(SQLiteException e){
            result = 0;
        }

        return result;

    }

    public List<Pergunta> findListHandler(){
        List<Pergunta> dadosList = new ArrayList<>();
        String query = "SELECT * FROM " + Pergunta.getTableName();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);


        while(cursor.moveToNext()){
            Pergunta pergunta = new Pergunta();
            pergunta.setId(cursor.getInt(0));
            pergunta.setPergunta(cursor.getString(1));
            pergunta.setResposta(cursor.getString(2));
            dadosList.add(pergunta);
        }
        cursor.close();
        db.close();
        return dadosList;
    }

    public Pergunta findHandler(int id){
        String query = "SELECT * FROM " + Pergunta.getTableName() +
                " WHERE " + Pergunta.getColumnId() + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Pergunta pergunta = new Pergunta();

        if(cursor.moveToFirst()){
            pergunta.setId(cursor.getInt(0));
            pergunta.setPergunta(cursor.getString(1));
            pergunta.setResposta(cursor.getString(2));
            cursor.close();
        }else{
            pergunta = null;
        }
        db.close();
        return pergunta;
    }

    public Pergunta update(Pergunta pergunta){
        Pergunta result = null;
        int id = pergunta.getId();

        try{
            ContentValues values = new ContentValues();
            values.put(Pergunta.getColumnPergunta(), pergunta.getPergunta());
            values.put(Pergunta.getColumnResposta(), pergunta.getResposta());

            SQLiteDatabase db = this.getWritableDatabase();
            int rows = db.update(Pergunta.getTableName(), values, Pergunta.getColumnId()+" = ? ", new String[]{Integer.toString(pergunta.getId())});
            Log.i("DATABASE", "rows: "+rows+" - id: "+Integer.toString(id));
            if (rows > 0) {
                pergunta.setId(id);
                result = pergunta;
            }
            db.close();

        }catch(SQLiteException e){
            result = null;
        }

        return result;
    }

    public boolean delete(int id){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(Pergunta.getTableName(), Pergunta.getColumnId() + " = ? ", new String[]{Integer.toString(id)});
            return true;
        }catch (Exception e){

        }
        return false;
    }

}

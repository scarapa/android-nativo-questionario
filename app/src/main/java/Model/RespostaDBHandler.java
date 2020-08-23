package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

import br.com.questionario.Config;

public class RespostaDBHandler extends SQLiteOpenHelper{

    public RespostaDBHandler(Context context) {
        super(context, Config.getDatabaseName(), null, Config.getDatabaseVersion());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {}
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public long addHandler(Resposta resposta){
        long result = 0;

        try{
            ContentValues values = new ContentValues();
            values.put("id", resposta.getId());
            values.put("resposta", resposta.getResposta());
            SQLiteDatabase db = this.getWritableDatabase();
            long dados_id = db.insert(" respostas ", null, values);

            if (dados_id != -1) {
                result = dados_id;
                Log.i("DATABASE", "RESPOSTA adicionada: "+dados_id);
            }
            db.close();

        }catch(SQLiteException e){
            result = 0;
        }
        return result;
    }

    public List<Pergunta> findListHandler(){
        List<Pergunta> dadosList = new ArrayList<>();
        String query = " SELECT * FROM respostas ";

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

    public boolean limparBanco(){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(" perguntas ", null, null);
            return true;
        }catch (Exception e){

        }
        return false;
    }

    public List<String> buscarResultado(){
        String query = " SELECT COUNT(resposta) questoes , SUM(resposta) acertos , printf(\"%.2f\", ( printf(\"%.2f\",100*SUM(resposta)) ) / printf(\"%.2f\",COUNT(resposta) ) ) nota FROM respostas" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        List<String> lista = new ArrayList<>();
        if(cursor.moveToFirst()){
            int questoes = cursor.getInt(0);
            lista.add( Integer.toString( questoes ) );
            int acertos = cursor.getInt(1);
            lista.add( Integer.toString( acertos ) );
            Float nota = cursor.getFloat(2);
            lista.add( Float.toString( nota ) );
            cursor.close();
        }
        db.close();
        return lista;
        //return pergunta;
    }

}

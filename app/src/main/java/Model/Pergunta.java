package Model;

import android.content.Context;
import android.content.Context;
import Database.Database;

public class Pergunta{

    private Integer id;
    private String pergunta;
    private String resposta;

    //Colunas do banco
    private static final String TABLE_NAME = "perguntas";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_PERGUNTA = "pergunta";
    private static final String COLUMN_RESPOSTA = "resposta";

    public Pergunta(int id,String pergunta,String resposta){
        this.setId(id);
        this.setPergunta(pergunta);
        this.setResposta(resposta);
    }
    public Pergunta(){ }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }


    public static String getTableName() { return TABLE_NAME; }
    public static String getColumnId() { return COLUMN_ID; }
    public static String getColumnPergunta() { return COLUMN_PERGUNTA; }
    public static String getColumnResposta() { return COLUMN_RESPOSTA; }
}

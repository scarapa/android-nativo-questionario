package Model;

import android.content.Context;
import android.content.Context;
import Database.Database;

public class Pergunta{

    private Integer id;
    private String pergunta;
    private boolean resposta;

    public Pergunta(int id,String pergunta,boolean resposta){
        this.setId(id);
        this.setPergunta(pergunta);
        this.setResposta(resposta);
    }

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

    public boolean isResposta() {
        return resposta;
    }

    public void setResposta(boolean resposta) {
        this.resposta = resposta;
    }
}

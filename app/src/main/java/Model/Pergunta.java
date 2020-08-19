package Model;

import android.content.Context;
import android.content.Context;
import Database.Database;

public class Pergunta{

    private Integer id;
    private String pergunta;
    private String resposta;

    public Pergunta(int id,String pergunta,String resposta){
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

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}

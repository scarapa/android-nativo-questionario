package Model;

public class Resposta {

    private Integer id;
    private Integer resposta;

    public Resposta(int id , int resposta){
        this.setId(id);
        this.setResposta(resposta);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResposta() {
        return resposta;
    }

    public void setResposta(Integer resposta) {
        this.resposta = resposta;
    }
}

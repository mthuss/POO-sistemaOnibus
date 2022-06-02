//Tava duplicada, mals, eu tirei a cópia

public class Motorista {
    private String CNH;
    private Data dataAdmissao;
    private String nome;

    //setters e getters
    public String getCNH() {
        return CNH;
    }
    public void setCNH(String CNH) {
        this.CNH = CNH;
    }

    public Data getDataAdmissao() {
        return dataAdmissao;
    }
    public void setDataAdmissao(Data dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Motorista(String CNH, Data dataAdmissao, String nome)    {
        this.CNH = CNH;
        this.dataAdmissao = dataAdmissao;
        this.nome = nome;
    }
    
    public Motorista()  {
    }
}

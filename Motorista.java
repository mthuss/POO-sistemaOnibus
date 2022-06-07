//Tava duplicada, mals, eu tirei a cópia

public class Motorista {
    private long CNH;
    private Data dataAdmissao;
    private String nome;
    private Onibus buser; //isso tem que ser atribuido!!!!!! obrigatoriamente!!!!!

    //setters e getters
    public long getCNH() {
        return CNH;
    }
    public void setCNH(long CNH) {
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
    
    public Motorista(long CNH, Data dataAdmissao, String nome)    {
        this.CNH = CNH;
        this.dataAdmissao = dataAdmissao;
        this.nome = nome;
    }
    
    public Motorista()  {
    }
}

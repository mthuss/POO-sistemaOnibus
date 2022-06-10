public class Motorista {
    private long CNH;
    private Data dataAdmissao;
    private String nome;
    private Onibus buser; //isso tem que ser atribuido!!!!!! obrigatoriamente!!!!!
    private boolean estaAtribuido = false;

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

    public boolean estaAtribuido()
    {
        return this.estaAtribuido;
    }
    public void setAtribuicao(boolean tf)
    {
        this.estaAtribuido = tf;
    }
    
    public Onibus getOnibus()   
    {
        return this.buser;
    }

    public void setOnibus(Onibus bus)
    {
        this.buser = bus;
    }

    public void imprimirDados()
    {
		System.out.println("Nome: " + this.nome +  " [CNH: " + this.CNH + "]\nData de admissão: " + this.dataAdmissao.imprimirData());
        String string = estaAtribuido ? "👍" : "👎";  
        System.out.println("Atribuido a onibus: " + string);
    }

    public Motorista(long CNH, Data dataAdmissao, String nome)    {
        this.CNH = CNH;
        this.dataAdmissao = dataAdmissao;
        this.nome = nome;
    }
    
    public Motorista()  {
    }
}

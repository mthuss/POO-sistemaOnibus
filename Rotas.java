import java.util.Scanner;
import java.util.ArrayList;

public class Rotas {
    private String origem;
    private String parada;
    private String destino;
    private Horario saida; //MENOR QUE A CHEGADA
    private Horario chegada; //MAIOR QUE A SAIDA
    private float valor;
    private int numRota;
    private boolean onibusAtribuido = false;
    
//    private ArrayList<Onibus> busos = new ArrayList<>(); //VAI SER UM SÓ

//    private Onibus onibus = new Onibus(); //Nota: PRECISA SER ASSIM!!
    private Onibus onibus; //E NÃO ASSIM!!
    
    public String getOrigem() {
        return origem;
    }
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getParada() {
        return parada;
    }
    public void setParada(String parada) {
        this.parada = parada;
    }

    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Horario getHoraSaida() {
        return saida;
    }
    public void setHoraSaida(Horario saida) {
        this.saida = saida;
    }

    public Horario getHoraChegada() {
        return chegada;
    }
    public void setHoraChegada(Horario chegada) {
        this.chegada = chegada;
    }
    
    public float getValor() {
        return valor;
    }
    public void setValor(float valor)   {
        this.valor = valor;
    }
    public void setOnibus(Onibus bus){
      this.onibus = bus;
    }
    public Onibus getOnibus(){
      return this.onibus;
    }

    public void setIDRota(int linha){
	    this.numRota = linha;
    }
    public int getIDRota(){
    	return this.numRota;
    }

    public void setAtribBus(boolean status)
    {
        this.onibusAtribuido = status;
    }
    public boolean getAtribBus()
    {
        return this.onibusAtribuido;
    }

//    public ArrayList<Onibus> getArrOnibus()
//    {
//        return this.busos;
//    }

  //construtores
    public Rotas(String origem, String parada, String destino, Horario saida, Horario chegada, float valor, int numRota)  {
        this.origem = origem;
        this.parada = parada;
        this.destino = destino;
        this.saida = saida;
        this.chegada = chegada;
        this.valor = valor;
        this.numRota = numRota;
    }
    
    public Rotas()  {
    }  
}

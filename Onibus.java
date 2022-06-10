import java.util.Scanner;
import java.util.ArrayList;

public class Onibus {
    private String modelo;
    private int anoFabri;
    private String marca;
    private int quilometragem;
    private int assentos[][] = new int[10][3];
//    private ArrayList<Rotas> rotasCirculadas = new ArrayList<>(); //rotas que o onibus percorre
 //   private ArrayList<Passageiro> passageiros = new ArrayList<>();
    private Motorista driver;
    private int IDRota;
//    private Rotas rota = new Rotas();
    private String placa;
    boolean temMotorista = false;
    boolean atribuido = false;

    //setters e getters
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnoFabri() {
        return anoFabri;
    }
    public void setAnoFabri(int anoFabri) {
        this.anoFabri = anoFabri;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getQuilometragem() {
        return quilometragem;
    }
    public void setQuilometragem(int quilometragem) {
        this.quilometragem = quilometragem;
    }
    public void setIDRota(int ID){
	    this.IDRota = ID;
    }
    public int getIDRota(){
    	return this.IDRota;
    }

    public void setPlaca(String placa)
    {
      this.placa = placa;
    }
    public String getPlaca()
    {
      return this.placa;
    }
    public void setDriver(Motorista driver)
    {
      this.driver = driver;
    }
    public Motorista getDriver()
    {
      return this.driver;
    }

    public boolean temMotorista()
    {
      return this.temMotorista;
    }
    public void setTemMotorista(boolean tf)
    {
      this.temMotorista = tf;
    }

    public void setAtribuido(boolean tf)
    {
      this.atribuido = tf;
    }

    public boolean atribuidoRota()
    {
      return this.atribuido;
    }

//    public void setRota(Rotas rota)
//    {
//      this.rota = rota;
//    }
//    public Rotas getRota(Rotas rota)
//    {
//      return this.rota;
//    }
//Métodos
//------------------------------------------------------
  public void imprimeAssentos()  {
    for (int i=0; i<10; i++)  {
      for(int j=0; j<3; j++)
        System.out.print(assentos[i][j] + " ");
    System.out.println(); 
      }
    }

  public void reservarAssento(Passageiro pessoa) //recebe pessoa pra que possa registar o local do assento no objeto pessoa
  {
    int i,j;
    System.out.println("Assentos disponiveis: ");
    this.imprimeAssentos();

    //Recebe dados do usuário e valida eles
    do{
      System.out.print("\nEscolha seu assento [linha coluna]: ");
      Scanner sc = new Scanner(System.in);
      i = sc.nextInt();
      j = sc.nextInt();

      //Verifica disponibilidade de assentos
      if(i <= 0 || i > 10 || j <= 0 || j > 3)
        System.out.println("Assento inválido");
      else if(this.assentos[i-1][j-1] == 1)
        System.out.println("Assento ocupado!");      
      }while(i <= 0 || i > 10 || j <= 0 || j > 3 || this.assentos[i-1][j-1] == 1);

      //Marca assento selecionado como ocupado
      pessoa.setAssento(i-1,j-1); //Designa o assento da pessoa
      this.assentos[i-1][j-1] = 1;
  }

  public void cancelarAssento(int i, int j)
  {
    this.assentos[i][j] = 0;
  }
    public void dadosOnibus()
    {
      System.out.println("Realize o cadastro do ônibus: ");
      System.out.print("Marca: ");
      Scanner sc = new Scanner(System.in);
      this.marca = sc.nextLine();
      System.out.print("Modelo: ");
      this.modelo = sc.nextLine();
      System.out.print("Ano de fabricação: ");
      this.anoFabri = sc.nextInt();
      sc.nextLine();
      System.out.print("Quilometragem: ");
      this.quilometragem = sc.nextInt();
      sc.nextLine();
      System.out.print("Placa: ");
      this.placa = sc.nextLine();
    }

    public void imprimirDados()
    {
      System.out.println("  Modelo: " + this.modelo);
      System.out.println("  Marca: " + this.marca);
      System.out.println("  Ano de Fabricação: " + this.anoFabri);
      System.out.println("  Quilometragem: " + this.quilometragem);
      System.out.println("  Placa: " + this.placa);
      if(!atribuido)
        System.out.println("  Rota: N/A");
      else
        System.out.println("  Rota: " + this.IDRota);
    
    }
    //construtores
    public Onibus(String modelo, int anoFabri, String marca,
int quilometragem)    {
        this.modelo = modelo;
        this.anoFabri = anoFabri;
        this.marca = marca;
        this.quilometragem = quilometragem;
    }

    public Onibus() {
    }
}

import java.util.Scanner;
import java.util.ArrayList;

public class Onibus {
    private String modelo;
    private int anoFabri;
    private String marca;
    private int quilometragem;
    private int assentos[][] = new int[10][3];
 //   private ArrayList<Passageiro> passageiros = new ArrayList<>();
    private Motorista driver;

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



//Métodos
//------------------------------------------------------
  public void imprimeAssentos()  {
    for (int i=0; i<10; i++)  {
      for(int j=0; j<3; j++)
        System.out.print(assentos[i][j] + " ");
    System.out.println(); 
      }
    }

  public void reservarAssento()
  {
    int i,j;
    System.out.println("Assentos disponiveis: ");
    this.imprimeAssentos();

    //Recebe dados do usuário e valida eles
    do{
      System.out.print("\nEscolha seu assento: ");
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
      this.assentos[i-1][j-1] = 1;
  }
    //construtores
    public Onibus(String modelo, int anoFabri, String marca,
int quilometragem, int assentos[][])    {
        this.modelo = modelo;
        this.anoFabri = anoFabri;
        this.marca = marca;
        this.quilometragem = quilometragem;
        this.assentos = assentos;
    }

    public Onibus() {
    }
}

import java.util.Scanner;

public class Passageiro {
  private long documento; //é o CPF
  private String nome;
  private Data nascData;
  private String endereco;
  private int assento[] = new int[2];
  private int numLinha;
  private boolean reservado = false;

  //Getters e Setters
  public void setDocumento(long doc)  {
    this.documento = doc;
  }
  public long getDocumento()  {
    return (this.documento);
  }

  public void setNome(String name)  {
    this.nome = name;
  }
  public String getNome()  {
    return (this.nome);
  }

  public void setData(int dia, int mes, int ano)  {
    this.nascData.setDia(dia);
    this.nascData.setMes(mes);
    this.nascData.setAno(ano);
}
  public Data getData()  {
    return (this.nascData);
  }


  public void setEndereco(String end)  {
    this.endereco = end;
  }
  public String getEndereco()  {
    return (this.endereco);
  }
    public void setLinha(int ID){
	    this.numLinha = ID;
    }

    public int getLinha(){
	return this.numLinha;
    }

  public int[] getAssento()
  {
    return this.assento;
  }

  public void setAssento(int i,int j)
  {
    this.assento[0] = i;
    this.assento[1] = j;
  }

  public void setReservado(boolean status)
  {
    this.reservado = status;
  }

  public boolean getReservado()
  {
    return this.reservado;
  }
  //Construtores
  public Passageiro(long documento, String nome, Data data, String endereco)  {
    this.documento = documento;
    this.nome = nome;
    this.nascData = data;
    this.endereco = endereco;
  }

  public Passageiro()   {
  }

  public void imprimirDados()
  {
    System.out.println("Nome: " + this.nome);
    System.out.println("CPF: " + this.documento);
    System.out.println("Endereço: " + this.endereco);
    System.out.println("Data de nascimento: " + this.nascData.getDia() + "/" + this.nascData.getMes() + "/" + this.nascData.getAno());
    System.out.print("Rota reservada: "); 
    if(reservado)
    {
      System.out.println(this.numLinha);
      System.out.println("Local do assento: " + assento[0]+1 + " " + assento[1]+1);
    }
    else System.out.println("N/A");

  }
}

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
    nascData.setDia(dia);
    nascData.setMes(mes);
    nascData.setAno(ano);
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
}

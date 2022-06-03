import java.util.Scanner;

public class Passageiro {
  private long documento; //é o CPF
  private String nome;
  private Data nascData;
  private String endereco;
  private int lugar[] = new int[2];
  private int numLinha;

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
    public void setLinha(int linha){
	this.numLinha = linha;
    }

    public int getLinha(){
	return this.numLinha;
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

  //PASSAR ESSA FUNÇÃO PRO SISTEMA.JAVA DEPOIS
  //ASSIM, PEGA TODOS OS DADOS E DEPOIS ENFIA NO CONSTRUTOR
  //Função que recebe dados do passageiro e cria um novo
  public void dadosPassageiro()
  {
  
  }
}

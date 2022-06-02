public class Assentos 
{
  //  private int numero;
    private int matriz[][] = new int[10][3];

  //setters e getters
  public Assentos()
  {
    this.matriz = matriz;
  }

  public void imprimeMatriz()  {
    this.matriz = matriz;
    for (int i=0; i<10; i++)  {
      for(int j=0; j<3; j++)
        System.out.print(matriz[i][j] + " ");
    System.out.println(); 
      }
    }



}

public class Data {
    private int dia;
    private int mes;
    private int ano;

    //getters e setters

  public int getDia() {
        return dia;
    }
    public void setDia(int dia) {
        if ((dia<1) || (dia>31))
                System.out.println("Dia invalido");
        
        else 
            this.dia = dia;
    }

    public int getMes() {
        return mes;
    }
    public void setMes(int mes) {
        if ((mes<1) || (mes>12))
            System.out.println("Mes invalido");
        
        else
            this.mes = mes;
    }

    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        if (ano>2022)
            System.out.println("Ano invalido");
        
        else
            this.ano = ano;
    }
    
    //construtores
    public Data(int dia, int mes, int ano)  {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }
    public Data()   {
    }

    //Métodos
    //--------------------
    public String imprimirData()
    {
        return String.format("%02d/%02d/%02d",this.dia,this.mes,this.ano);
    }
}

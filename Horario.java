public class Horario {
    private int horas;
    private int minutos;
    
    //getters e setters
    public int getHoras()  {
        return (this.horas);
    }
    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getMinutos() {
        return (this.minutos);
    }
    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    //construtores
    public Horario(int horas, int minutos)  {
        this.horas = horas;
        this.minutos = minutos;
    }
    public Horario()    {
    }

    public String imprimirHorario()
    {
        return String.format("%02d:%02d",this.horas,this.minutos);
    }
}

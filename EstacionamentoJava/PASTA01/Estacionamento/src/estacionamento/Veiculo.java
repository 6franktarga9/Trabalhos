package estacionamento;

public class Veiculo {
    public static int id;
    protected String placa, cor, modelo;

    public Veiculo(String placa, String cor, String modelo)
    {
        this.id++;
        this.placa = placa;
        this.cor = cor;
        this.modelo = modelo;  
    }
    
    public String getPlaca()
    {
        return placa;
    }
    
    public String getCor()
    {
        return cor;
    }
    
    public String getModelo()
    {
        return modelo;
    }
    
    public void setPlaca(String placa)
    {
        this.placa = placa;
    }
    
    public void setCor(String cor)
    {
        this.cor = cor;
    }
        
    public void setModelo(String modelo)
    {
        this.modelo = modelo;
    }
}
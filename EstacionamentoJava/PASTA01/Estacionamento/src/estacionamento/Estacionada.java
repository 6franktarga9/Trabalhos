package estacionamento;
import java.util.Date;
import estacionamento.Carro;
import estacionamento.Moto;

public class Estacionada {
    public static int id;
    private Date entrada, saida;
    private Vaga vaga;
    private Veiculo veiculo;
    private double valor;
    private Patio patio;
    
    public Estacionada(Date entrada, Vaga vaga, Veiculo veiculo, Patio patio){
        this.entrada = entrada;
        this.vaga = vaga;
        this.veiculo = veiculo;
        this.patio = patio;
        id++;
        if (vaga instanceof VagaCarro){
            ((VagaCarro)vaga).ocupar(this);
        }
        else{
            ((VagaMoto)vaga).ocupar(this);
        }
    }
    
    public int getId(){
        return id;
    }
    
    public Date getEntrada(){
        return entrada;
    }
    
    public Date getSaida(){
        return saida;
    }
    
    public Vaga getVaga(){
        return vaga;
    }
    
    public Veiculo getVeiculo(){
        return veiculo;
    }
    
    public double getValor(){
        return valor;
    }
    
    public void setEntrada(Date entrada){
        this.entrada = entrada;
    }
    
    public void setVaga(Vaga vaga){
        this.vaga = vaga;
    }
    
    public void setVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
    }
    
    public void setValor(double valor){
        this.valor = valor;
        
    }
    
    public void setSaida(Date saida){
        this.saida = saida;
        double tempoHoras = Math.ceil((saida.getTime() - entrada.getTime())/(1000.0 * 60 * 60));
        
        if (veiculo instanceof Carro){
            valor = tempoHoras * patio.getHoraCarro();
        }else if (veiculo instanceof Moto){
            valor = tempoHoras * patio.getHoraMoto();        
        }else{
            valor=0;
        }
        
    }
}

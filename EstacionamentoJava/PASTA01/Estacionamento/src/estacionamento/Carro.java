package estacionamento;
import java.util.ArrayList;

public class Carro extends Veiculo{
    private static int qtdEstacionadas;
    private double valorGasto=0;
    private ArrayList<Estacionada> estacionadas = new ArrayList<>();
    
    public Carro(String placa, String cor, String modelo){
        super(placa, cor, modelo);
    }
    
    public ArrayList getEstacionadas(){
        return estacionadas;
    }
    
    @Override        
    public void setPlaca(String placa){
        super.placa = placa;
    }
    
    @Override
    public void setCor(String cor){
        super.cor = cor;
    }
    
    @Override
    public void setModelo(String modelo){
        super.modelo = modelo;
    }
    
    public void adicionarEstacionada(Estacionada estacionada){
        estacionadas.add(estacionada);
        qtdEstacionadas++;
        double valor = estacionada.getValor();
        valorGasto = valorGasto + valor;
    }
    
    public void cancelarEstacionada(Estacionada estacionada){
        estacionadas.remove(estacionada);
        qtdEstacionadas--;
        valorGasto = valorGasto - estacionada.getValor();
    }
}



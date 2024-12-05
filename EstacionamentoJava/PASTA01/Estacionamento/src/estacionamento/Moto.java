package estacionamento;
import java.util.ArrayList;

public class Moto extends Veiculo{
    private static int qtdEstacionadas;
    private double valorGasto=0;
    private ArrayList<Estacionada> estacionadas = new ArrayList<>();
    
    public Moto(String placa, String cor, String modelo){
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
        valorGasto = valorGasto + estacionada.getValor();
    }
    
    public void cancelarEstacionada(Estacionada estacionada){
        estacionadas.remove(estacionada);
        qtdEstacionadas--;
        valorGasto = valorGasto - estacionada.getValor();
    }
}

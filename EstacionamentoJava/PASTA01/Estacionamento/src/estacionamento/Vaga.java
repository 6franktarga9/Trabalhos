package estacionamento;
import java.util.ArrayList;

public class Vaga {
    public int id;
    protected VagaEstado estadoVaga;
    protected ArrayList<Estacionada> estacionadas = new ArrayList<>();

    public Vaga(){
        this.estadoVaga = VagaEstado.DISPONIVEL;
    }

    public int getEstado(){
        if (estadoVaga == VagaEstado.DISPONIVEL)
        {
            return 0; //Retorna 0 se a vaga NÃO está ocupada
        }
        else
        {
            return 1; //Retorna 1 se a vaga está ocupada
        }
    }
    
    public ArrayList<Estacionada> getEstacionadas(){
        return estacionadas;
    }

    public void liberar(Estacionada estacionada){
        estadoVaga = VagaEstado.DISPONIVEL;
    }
}


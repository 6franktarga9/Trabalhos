package estacionamento;

public class VagaCarro extends Vaga{
    private Carro carro;

    public VagaCarro(){
        super();
    }
    
    public int getId(){
        return super.id;
    }
    
    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }    
    
    public void ocupar(Estacionada estacionada){
        super.estadoVaga = VagaEstado.INDISPONIVEL;
        super.estacionadas.add(estacionada);
    }
}



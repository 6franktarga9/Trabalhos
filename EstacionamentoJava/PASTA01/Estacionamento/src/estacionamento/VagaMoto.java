package estacionamento;

public class VagaMoto extends Vaga{
    private Moto moto;

    public VagaMoto(){
        super();
    }
    
    public int getId(){
        return super.id;
    }    
    
    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }    
    
    public void ocupar(Estacionada estacionada){
        super.estadoVaga = VagaEstado.INDISPONIVEL;
        super.estacionadas.add(estacionada);
    }
}
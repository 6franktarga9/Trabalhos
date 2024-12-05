package estacionamento;
import java.util.ArrayList;

public class Patio {
    public static int id;
    public int vagaId=1;
    private String localizacao;
    private double horaCarro, horaMoto;
    private ArrayList<VagaCarro> vagasCarros = new ArrayList<>();
    private ArrayList<VagaMoto> vagasMotos = new ArrayList<>();

    public Patio(String localizacao, double horaCarro, double horaMoto, int qtdVagasCarros, int qtdVagasMotos){
        this.localizacao = localizacao;
        this.horaCarro = horaCarro;
        this.horaMoto = horaMoto;
        id++;
        
        for (int i=0; i<qtdVagasCarros; i++){
            VagaCarro vagaTemp = new VagaCarro();
            vagaTemp.id = vagaId;
            vagaId++;
            vagasCarros.add(vagaTemp);
        }
        
        for (int i=0; i<qtdVagasMotos; i++){
            VagaMoto vagaTemp = new VagaMoto();
            vagasMotos.add(vagaTemp);
            vagaTemp.id = vagaId;
            vagaId++;
        }
    }
    
    public int getID(){
        return id;
    }
    
    public String getLocalizacao(){
        return localizacao;
    }
    
    public double getHoraCarro(){
        return horaCarro;
    }

    public double getHoraMoto(){
        return horaMoto;
    }
    
    public ArrayList<VagaCarro> getVagasCarros(){
        return vagasCarros;
    }
    
    public ArrayList<VagaMoto> getVagasMotos(){
        return vagasMotos;
    }
    
    public void setHoraCarro(double horaCarro){
        this.horaCarro = horaCarro;
    }
    
    public void setHoraMoto(double horaMoto){
        this.horaMoto = horaMoto;
    }
    
    public VagaMoto BuscaVagaMoto(){
        for (VagaMoto vaga: vagasMotos){
            if (vaga.getEstado()==0){
                return vaga;
            }
        }
        return null;
    }
    
    public VagaCarro BuscaVagaCarro(){
        for (VagaCarro vaga: vagasCarros){
            if (vaga.getEstado()==0){
                return vaga;
            }
        }
        return null;
    }
}

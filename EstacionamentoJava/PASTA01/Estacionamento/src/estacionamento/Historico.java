package estacionamento;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Historico {
    private ArrayList<Estacionada> estacionadas = new ArrayList<>();
    private Patio patio;
    
    public void registrarEstacionada(Estacionada estacionada, Patio patio) {
        estacionadas.add(estacionada);
        this.patio = patio;
    }
    
    public void menuRelatorios(Scanner leitura, Patio patio) {
        System.out.println("Caixa e relatórios:");
        System.out.println("1 - Fechar caixa do dia");
        System.out.println("2 - Histórico do dia");
        System.out.println("3 - Histórico por período");
        System.out.println("4 - Histórico total");
        int opcao = leitura.nextInt();
        leitura.nextLine(); // Limpar teclado
        
        switch (opcao) {
            case 1:
                fecharCaixa();
                break;
            case 2:
                imprimirHistoricoDoDia();
                break;
            case 3:
                imprimirHistoricoPorPeriodo(leitura);
                break;
            case 4:
                imprimirHistoricoTotal();
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
    
    private void fecharCaixa() {
        double totalCarro = 0; double totalMoto = 0;
        int qtdCarros = 0;
        int qtdMotos = 0;
        for (Estacionada est : estacionadas) {
            if (est.getSaida() != null) {
                if (est.getVeiculo() instanceof Carro) {
                    totalCarro += est.getValor(); qtdCarros++;
                }
                else if (est.getVeiculo() instanceof Moto) {
                    totalMoto += est.getValor();
                    qtdMotos++;
                }
            }
        }
        System.out.println("Fechamento do caixa do dia:");
        System.out.println("Quantidade de carros: " + qtdCarros);
        System.out.println("Quantidade de motos: " + qtdMotos);
        System.out.println("Valor faturado com carros: " + totalCarro);
        System.out.println("Valor faturado com motos: " + totalMoto);
        System.out.println("Valor total faturado: " + (totalCarro + totalMoto));
        System.out.println("---------------------------");
        imprimirHistoricoDoDia(); }
    
    private void imprimirHistoricoDoDia() {
        Date hoje = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataHoje = sdf.format(hoje);
        System.out.println("Histórico do dia " + dataHoje + ":");
        double valorTotal = 0;
        for (Estacionada est : estacionadas) {
            String dataEntrada = sdf.format(est.getEntrada());
            if (dataHoje.equals(dataEntrada)) {
                imprimirEstacionada(est);
                valorTotal = valorTotal + est.getValor();
            }
        }
        System.out.println("Valor total (pago) do dia: " + valorTotal); }
    
    private void imprimirHistoricoPorPeriodo(Scanner leitura) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        double valorTotal = 0;
        try {
            System.out.println("Digite a data de início (dd/MM/yyyy):");
            String dataInicioStr = leitura.nextLine();
            Date dataInicio = sdf.parse(dataInicioStr);
            
            System.out.println("Digite a data de fim (dd/MM/yyyy):");
            String dataFimStr = leitura.nextLine();
            Date dataFim = sdf.parse(dataFimStr);
            System.out.println("Histórico de " + sdf.format(dataInicio) + " a " + sdf.format(dataFim) + ":");
            for (Estacionada est : estacionadas) {
                if (!est.getEntrada().before(dataInicio) && !est.getEntrada().after(dataFim)) {
                    imprimirEstacionada(est);
                    valorTotal = valorTotal + est.getValor();
                }
            }
            System.out.println("Valor total do período: " + valorTotal);
        } catch (Exception e) {
            System.out.println("Data inválida. Por favor, tente novamente.");
        }
    }
    
    private void imprimirHistoricoTotal() {
        System.out.println("Histórico total:");
        double valorTotal = 0;
        for (Estacionada est : estacionadas) {
            imprimirEstacionada(est);
            valorTotal += est.getValor();
        }
        System.out.println("Valor total do período: " + valorTotal);
    }
    
    private void imprimirEstacionada(Estacionada est) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String entrada = sdf.format(est.getEntrada());
        
        String saida;
        if (est.getSaida() != null) {
            saida = sdf.format(est.getSaida());
        }
        else {
            saida = "Ainda no estacionamento";
        }
        
        double valorAtual;
        if (est.getValor() != 0) {
            valorAtual = est.getValor();
        }
        else {
            if (est.getVeiculo() instanceof Carro){
                valorAtual = patio.getHoraCarro();
            }
            else{
                valorAtual = patio.getHoraMoto();
            }
        }
        
        System.out.println("Placa: " + est.getVeiculo().getPlaca() + ", Cor: " + est.getVeiculo().getCor() + ", Modelo: " + est.getVeiculo().getModelo() + ", Entrada: " + entrada + ", Saída: " + saida + ", Valor: " + valorAtual);
    }
}
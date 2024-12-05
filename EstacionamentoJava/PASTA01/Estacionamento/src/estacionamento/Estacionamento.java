package estacionamento;
import java.util.Scanner;
import java.util.Date;
import java.util.ArrayList;

public class Estacionamento {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        
        System.out.println("Digite a localização do Pátio:");
        String localizacao = leitura.nextLine();
        
        System.out.println("Digite o valor Hora-Moto:");
        double horaMoto = leitura.nextDouble();
        
        System.out.println("Digite o valor Hora-Carro:");
        double horaCarro = leitura.nextDouble();
        
        System.out.println("Digite a quantidade de vagas para Carros:");
        int qtdVagasCarros = leitura.nextInt();
        
        System.out.println("Digite a quantidade de vagas para Motos:");
        int qtdVagasMotos = leitura.nextInt();
        
        Patio patio = new Patio(localizacao, horaCarro, horaMoto, qtdVagasCarros, qtdVagasMotos);
        Historico historico = new Historico();
        
        int opcao;
        while (true) {
            System.out.println("----------------------------------");
            System.out.println("Digite a opção que deseja acessar:");
            System.out.println("1 - Estacionar um veículo");
            System.out.println("2 - Liberar uma vaga");
            System.out.println("3 - Caixa e relatórios");
            System.out.println("4 - Configurações");
            System.out.println("0 - Sair");
            
            opcao = leitura.nextInt();
            leitura.nextLine(); // Limpar teclado
            
            switch (opcao) {
                case 1:
                    estacionarVeiculo(leitura, patio, historico);
                    break;
                case 2:
                    liberarVaga(leitura, patio);
                    break;
                case 3:
                    historico.menuRelatorios(leitura, patio);
                    break;
                case 4:
                    configuracoes(leitura, patio);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    leitura.close();
                    System.exit(0);
                default: System.out.println("Opção inválida.");
            }
        }
    }
    
    private static void estacionarVeiculo(Scanner leitura, Patio patio, Historico historico) {
        String placa, cor, modelo;
        Estacionada estacionada;
        
        System.out.println("Escolha uma das opções:");
        System.out.println("1 - Carro");
        System.out.println("2 - Moto");
        
        int op = leitura.nextInt();
        leitura.nextLine(); // Limpar teclado
        
        switch (op) {
            case 1:
                VagaCarro vagaTemp = patio.BuscaVagaCarro();
                if (vagaTemp == null) {
                    System.out.println("Não há vagas disponíveis!");
                    break;
                }
                
                System.out.println("Digite a placa do carro:");
                placa = leitura.nextLine();
                
                System.out.println("Digite a cor do carro:");
                cor = leitura.nextLine();
                
                System.out.println("Digite o modelo do carro:");
                modelo = leitura.nextLine();
                
                Carro carro = new Carro(placa, cor, modelo);
                estacionada = new Estacionada(new Date(), vagaTemp, carro, patio);
                historico.registrarEstacionada(estacionada, patio);
                
                System.out.println("O carro pode estacionar na vaga " + vagaTemp.getId());
                break;
            
            case 2:
                VagaMoto vagaTemp2 = patio.BuscaVagaMoto();
                if (vagaTemp2 == null) {
                    System.out.println("Não há vagas disponíveis!");
                    break;
                }
                
                System.out.println("Digite a placa da moto:");
                placa = leitura.nextLine();
                
                System.out.println("Digite a cor da moto:");
                cor = leitura.nextLine();
                
                System.out.println("Digite o modelo da moto:");
                modelo = leitura.nextLine();
                
                Moto moto = new Moto(placa, cor, modelo);
                estacionada = new Estacionada(new Date(), vagaTemp2, moto, patio);
                historico.registrarEstacionada(estacionada, patio);
                
                System.out.println("A moto pode estacionar na vaga " + vagaTemp2.getId());
                break;
            
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
    
    private static void liberarVaga(Scanner leitura, Patio patio) {
        System.out.println("Escolha uma das opções para liberar a vaga:");
        System.out.println("1 - Pelo número da vaga");
        System.out.println("2 - Pela placa do veículo");
        System.out.println("3 - Ver lista de veículos estacionados");
        int escolha = leitura.nextInt();
        leitura.nextLine(); // Limpar teclado
        
        switch (escolha) {
            case 1:
                System.out.println("Digite o número da vaga:");
                int idVaga = leitura.nextInt();
                leitura.nextLine(); // Limpar teclado
                liberarVagaPorNumero(patio, idVaga);
                break;
            case 2:
                System.out.println("Digite a placa do veículo:");
                String placa = leitura.nextLine();
                liberarVagaPorPlaca(patio, placa);
                break;
            case 3:
                listarVeiculosEstacionados(patio);
                liberarVaga(leitura, patio);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
    
    private static void liberarVagaPorNumero(Patio patio, int idVaga) {
        Vaga vaga = null;
        for (VagaCarro vc : patio.getVagasCarros()) {
            if (vc.getId() == idVaga) {
                vaga = vc;
                break;
            }
        }
        if (vaga == null) {
            for (VagaMoto vm : patio.getVagasMotos()) {
                if (vm.getId() == idVaga) {
                    vaga = vm;
                    break;
                }
            }
        }
        
        if (vaga == null) {
            System.out.println("Vaga não encontrada.");
            return;
        }
        
        if (vaga.getEstado()==0) {
            System.out.println("A vaga já está disponível.");
            return;
        }
        
        ArrayList<Estacionada> estacionadas = vaga.getEstacionadas();
        Estacionada est = estacionadas.get(estacionadas.size() - 1);
        est.setSaida(new Date());
        vaga.liberar(est);
        System.out.println("Vaga " + idVaga + " liberada. Valor devido: " + est.getValor());
    }
    
    private static void liberarVagaPorPlaca(Patio patio, String placa) {
        for (VagaCarro vc : patio.getVagasCarros()) {
            for (Estacionada est : vc.getEstacionadas()) {
                if (est.getVeiculo().getPlaca().equalsIgnoreCase(placa)) {
                    est.setSaida(new Date());
                    vc.liberar(est);
                    System.out.println("Vaga " + vc.getId() + " liberada. Valor devido: " + est.getValor());
                    return;
                }
            }
        }
        
        for (VagaMoto vm : patio.getVagasMotos()) {
            for (Estacionada est : vm.getEstacionadas()) {
                if (est.getVeiculo().getPlaca().equalsIgnoreCase(placa)) {
                    est.setSaida(new Date());
                    vm.liberar(est);
                    System.out.println("Vaga " + vm.getId() + " liberada. Valor devido: " + est.getValor());
                    return;
                }
            }
        }
        System.out.println("Veículo com a placa " + placa + " não encontrado.");
    }
    
    private static void listarVeiculosEstacionados(Patio patio) {
        System.out.println("Veículos estacionados:");
        for (VagaCarro vc : patio.getVagasCarros()) {
            if (vc.getEstado() == 1) {
                for (Estacionada est : vc.getEstacionadas()) {
                    System.out.println("Vaga " + vc.getId() + ": " + est.getVeiculo().getPlaca());
                }
            }
        }
        for (VagaMoto vm : patio.getVagasMotos()) {
            if (vm.getEstado() == 1) {
                for (Estacionada est : vm.getEstacionadas()) {
                    System.out.println("Vaga " + vm.getId() + ": " + est.getVeiculo().getPlaca());
                }
            }
        }
    }
    
    private static void configuracoes(Scanner leitura, Patio patio) {
        System.out.println("Configurações:");
        System.out.println("1 - Alterar valor da hora para carro");
        System.out.println("2 - Alterar valor da hora para moto");
        int opcao = leitura.nextInt();
        leitura.nextLine(); // Limpar teclado
        
        switch (opcao) {
            case 1:
                System.out.println("Digite o novo valor da hora para carro:");
                double novoValorCarro = leitura.nextDouble();
                patio.setHoraCarro(novoValorCarro);
                System.out.println("Valor da hora para carro atualizado.");
                break;
            case 2:
                System.out.println("Digite o novo valor da hora para moto:");
                double novoValorMoto = leitura.nextDouble();
                patio.setHoraMoto(novoValorMoto);
                System.out.println("Valor da hora para moto atualizado.");
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }
}


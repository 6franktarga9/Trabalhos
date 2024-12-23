import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

class RoundButton extends JButton {
    public RoundButton(String label) {
        super(label);
        setContentAreaFilled(false); // Não preencher a área do botão
        setFocusPainted(false); // Não pintar quando em foco
        setBorderPainted(false); // Não desenhar a borda padrão
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Desenha um botão circular
        g.setColor(getBackground());
        g.fillOval(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(80, 80); // Define o tamanho preferido do botão
    }
}

public class MatrizDeBotoes {
    private static final int MATRIX_SIZE = 10;
    private static final int MAX_CLICKS = 20;
    private static int clickCount = 0; // Contador de cliques
    private static Set<RoundButton> clickedButtons = new HashSet<>(); // Conjunto para armazenar botões clicados
    private static JLabel infoLabel; // Label para informações

    public static void main(String[] args) {
        // Cria a janela principal
        JFrame frame = new JFrame("Lotomania");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setBackground(new Color(240, 240, 240)); // Cor de fundo da janela

        // Cria um painel para as informações na parte superior
        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(200, 200, 200)); // Cor de fundo do painel de informações
        infoLabel = new JLabel("Números selecionados: 0");
        infoLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Estilo da fonte do label
        infoPanel.add(infoLabel);
        
        frame.add(infoPanel, BorderLayout.NORTH); // Adiciona o painel de informações na parte superior

        // Cria um painel para os botões usando GridBagLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espaçamento ao redor do painel

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Preenche o espaço disponível

        // Cria a matriz de botões
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                // Cria um botão redondo com número formatado
                String label = String.format("%02d", (i * MATRIX_SIZE + j + 1) % 100);
                RoundButton button = new RoundButton(label);
                button.setBackground(Color.WHITE); // Cor inicial do botão
                button.setFont(new Font("Arial", Font.BOLD, 20)); // Estilo da fonte do botão
                button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2)); // Borda dos botões
                
                // Adiciona espaçamento interno ao botão
                button.setMargin(new Insets(10, 10, 10, 10)); // Define o espaçamento interno

                // Adiciona uma borda externa ao botão para espaçamento visual
                button.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(5, 5, 5, 5), 
                        BorderFactory.createLineBorder(Color.GRAY)
                ));

                // Configura as restrições do GridBagLayout
                gbc.gridx = j; // Coluna atual
                gbc.gridy = i; // Linha atual
                gbc.weightx = 1.0; // Aumenta o peso horizontalmente
                gbc.weighty = 1.0; // Aumenta o peso verticalmente

                buttonPanel.add(button, gbc); // Adiciona o botão ao painel com as restrições

                // Adiciona o listener para o clique do botão
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Verifica se o botão já foi clicado
                        if (!clickedButtons.contains(button)) {
                            clickedButtons.add(button); // Adiciona o botão ao conjunto de clicados
                            clickCount++; // Incrementa o contador de cliques

                            // Altera a cor do botão com base no número de cliques
                            if (clickCount <= MAX_CLICKS) {
                                button.setBackground(Color.RED); // Primeiros 20 cliques em vermelho
                            } else {
                                button.setBackground(Color.ORANGE); // Depois em laranja
                            }
                        } else {
                            clickedButtons.remove(button); // Remove o botão do conjunto de clicados
                            clickCount--; // Decrementa o contador de cliques

                            // Restaura a cor do botão para branco
                            button.setBackground(Color.WHITE);
                        }

                        // Atualiza a informação na caixa de texto
                        updateInfoLabel();
                    }
                });
            }
        }

        // Adiciona o painel de botões à janela principal
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.pack(); // Ajusta o tamanho da janela para caber todos os componentes
        
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Abre em tela cheia (opcional)
        
        frame.setVisible(true);
    }

    private static void updateInfoLabel() {
        int additionalClicks = Math.max(0, clickCount - MAX_CLICKS);
        infoLabel.setText("Números selecionados: " + additionalClicks);
    }
}
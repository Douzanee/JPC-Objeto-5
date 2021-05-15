package quadtree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener
{
	
	// Tamanho do tabuleiro
	private final int B_WIDTH = 1000;
    private final int B_HEIGHT = 1000;
    
    private final int DELAY = 100; // Frequencia do game loop


    
    // Jogo ainda em andamento?
    private boolean inGame = true;
    
    // Transferência de Posição
    int transferPosX = 0;
    int transferPosY = 0;

    // Temporizador para configurar a velocidade do jogo
    private Timer timer;
    
    public Board() {
        
        initBoard();
    }
    
    /*** 
     * Funcao para inicializar o tabuleiro
     */
    private void initBoard() {
        
        // Seta a cor de fundo da janela
        setBackground(Color.black);
        
        // Coloca o foco nesta janela
        setFocusable(true);

        // Configura a dimensao da janela
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
        // Inicializa o jogo
        initGame();
    }


    private void initGame() {

        
        
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) 
    {
        if (inGame) 
        {
            java.awt.Toolkit.getDefaultToolkit().sync();
        }    
    }


    public void actionPerformed(ActionEvent e) {

        if (inGame) 
        {

        }

        repaint();
    }

}

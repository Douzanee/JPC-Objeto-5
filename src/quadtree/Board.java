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

public class Board extends JPanel implements ActionListener {
	// Tamanho do tabuleiro
	private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    
    private final int DOT_SIZE = 10; // Incremento de movimento 
    private final int ALL_DOTS = 900; // Quantidade maxima de nos da serpente
    private final int RAND_POS = 29;
    private final int DELAY = 140; // Frequencia do game loop

    // Array contendo a posicao de cada elemento do corpo da serpente
    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots; // Armazena a quantidade de elementos no corpo da serpente
    private int apple_x; // Posicao x da maca
    private int apple_y; // Posicao y da maca

    // Direcao da serpente
    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    
    // Jogo ainda em andamento?
    private boolean inGame = true;
    
    // Transfer�ncia de Posi��o
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

    	// Adiciona um objeto de classe responsavel pela leitura do teclado
        addKeyListener(new TAdapter());
        
        // Seta a cor de fundo da janela
        setBackground(Color.black);
        
        // Coloca o foco nesta janela
        setFocusable(true);

        // Configura a dimensao da janela
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
        // Inicializa o jogo
        initGame();
    }


    /*** 
     * Funcao para inicializar o jogo
     */
    private void initGame() {

    	// Numero de elementos iniciais no corpo da serpente
        dots = 5;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        
        locateApple();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    /***
     * Metodo responsavel pelo desenho dos elementos na tela
     * @param g
     */
    private void doDrawing(Graphics g) {
        
        if (inGame) {
        	g.setColor(Color.GREEN);
        	g.fillRect(apple_x, apple_y, 10, 10);


            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                	g.setColor(Color.LIGHT_GRAY);
                	g.fillOval(x[z], y[z], 10, 10);
                } else {
                	g.setColor(Color.green);
                	g.fillOval(x[z], y[z], 10, 10);
                }
            }

            java.awt.Toolkit.getDefaultToolkit().sync();

        } else {

            gameOver(g);
        }        
    }

    /***
     * Metodo para desenhar a funcao de game over
     * @param g
     */
    private void gameOver(Graphics g) {
        
        String msg = "Cats do love to play as snakes."
        +"\n Score: "+ (dots - 5);//work pls
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.green);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    /** 
     * Funcao que verifica se a maca foi comida
     */
    private void checkApple() {

        if(x[0] == apple_x && y[0] == apple_y){
            dots++;
            locateApple();
        }
    }

    /***
     * M�todo responsavel por mover a serpente
     */
    private void move() {

        // TODO
        
        transferPosX = x[0];
        transferPosY = y[0];
        int auxX = 0;
        int auxY = 0;
        
        if(leftDirection){
            x[0] = x[0] - DOT_SIZE;
        }else if(rightDirection){
            x[0] = x[0] + DOT_SIZE;
        }else if(upDirection){
            y[0] = y[0] - DOT_SIZE;
        }else if(downDirection){
            y[0] = y[0] + DOT_SIZE;
        }else{
        	
        }
            
        for (int z = 1; z < dots; z++) {
            auxX = x[z];
            auxY = y[z];
            x[z] = transferPosX;
            y[z] = transferPosY;
            transferPosX = auxX;
            transferPosY = auxY;
        }
        
    }

    
    /***
     * Verificar se houve colisao com a propria cobra ou se a mesma saiu da tela
     */
    private void checkCollision() {

        // TODO
        // com a borda da tela
        if(x[0] < 0 || x[0] > B_WIDTH || y[0] < 0 || y[0] > B_HEIGHT){
            inGame = false;
        }
        
        // com outra parte do corpo
        for(int z = 1; z < dots; z++){
            if(x[0] == x[z] && y[0] == y[z]){
                inGame = false;
            }
        }
    }

    
    /***
     * Metodo responsavel por reposicionar a maca
     */
    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    /*** 
     * Metodo responsavel pelo game loop do jogo
     */
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    /*** 
     * 
     * @author josericardo
     * Classe que verifica se houve mudanca de orientacao
     */
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}

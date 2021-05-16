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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

import javax.swing.JPanel;

public class Board extends JPanel implements ActionListener
{
	
	// Tamanho do tabuleiro
	private final int B_WIDTH = 1000;
    private final int B_HEIGHT = 1000;
    
    private final int DELAY = 100; // Frequencia do game loop
    
    // Jogo ainda em andamento?
    private static boolean inGame = true;    

    // Temporizador para configurar a velocidade do jogo
    private Timer timer;
    
    //Quad Tree Stuff
    private Rectangle rectangle = new Rectangle(B_WIDTH,B_HEIGHT,B_WIDTH,B_HEIGHT);
    private QuadTree quadTree = new QuadTree(rectangle, 8);
    
    Point[] points;
    public static List <Rectangle> rects = new ArrayList<Rectangle>();
	int quantity = 10;
	int rectCount = 0;

	Random rand = new Random();
    
    
    public Board() {
    	
    	points = new Point[quantity];
    	
        for(int i = 0; i < quantity; i++) {
        	Point point = new Point(rand.nextInt(B_WIDTH), rand.nextInt(B_HEIGHT));
        	quadTree.Insert(point);	
        	points[i] = point;
        	points[i].id = i;
        }
        initBoard();
        
    }
    
    /*** 
     * Funcao para inicializar o tabuleiro
     */
    private void initBoard() {
        
        // Seta a cor de fundo da janela
        setBackground(Color.WHITE);
        
        // Coloca o foco nesta janela
        setFocusable(true);

        // Configura a dimensao da janela
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT ));V
        // rectangle = new Rectangle(200,200,200,200);
        
        // Inicializa o jogo
        initGame();
    }


    private void initGame() 
    {       
        timer = new Timer(DELAY, this);
        timer.start();
    }
  
    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    public void doDrawing(Graphics g) 
    {
    	for(int i = 0; i <rects.size(); i++) {
		    	g.drawRect(rects.get(i).x, rects.get(i).y, rects.get(i).w*2, rects.get(i).h*2);
		    	g.setColor(Color.BLUE);
    	}
    	for (int i = 0; i < quantity; i++) 
    	{
    		g.setColor(Color.RED);
        	g.fillRect(points[i].x, points[i].y, 5, 5);
        	java.awt.Toolkit.getDefaultToolkit().sync();  
    	}
    	System.out.println("eae guei "+ rects.size());
    }


    public void actionPerformed(ActionEvent e) {

        if (inGame) 
        {
        	
        }

        repaint();
    }

}

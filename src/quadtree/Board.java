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
	private final int B_WIDTH = 600;
    private final int B_HEIGHT = 600;
    
    private final int DELAY = 100; // Frequencia do game loop
    
    // Jogo ainda em andamento?
    private static boolean inGame = true;    

    // Temporizador para configurar a velocidade do jogo
    private Timer timer;
    
    //Quad Tree Stuff
    private Rectangle rectangle = new Rectangle(1,1,B_WIDTH-3, B_HEIGHT-3);
    private QuadTree quadTree = new QuadTree(rectangle, 4);
    static List<QuadTree> quads = new ArrayList();
    
    Point[] points;
	int quantity = 100;

	Random rand = new Random();
    
    
    public Board() {
    	
    	points = new Point[quantity];
    	
        for(int i = 0; i < quantity; i++) {
        	Point point = new Point(rand.nextInt(rectangle.w - 2) + 2, rand.nextInt(rectangle.h - 2) + 2);
        	points[i] = point;
        	points[i].id = i;
        	quadTree.Insert(point);	
        	System.out.println("inserted");
        }
        initBoard();
    }
    
    /*** 
     * Funcao para inicializar o tabuleiro
     */
    private void initBoard() {
        
        // Seta a cor de fundo da janela
        setBackground(Color.white);
        
        // Coloca o foco nesta janela
        setFocusable(true);

        // Configura a dimensao da janela
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        
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

    	g.drawRect(1,1,B_WIDTH-3, B_HEIGHT-3);
    	g.setColor(Color.black);
    	    	
    	for (int i = 0; i < quads.size(); i++) 
    	{
    		g.drawRect(quads.get(i).rectangle.x, quads.get(i).rectangle.y, quads.get(i).rectangle.w, quads.get(i).rectangle.h);
    	}
    	
    	for (int i = 0; i < quantity; i++) 
    	{
    		g.setColor(Color.red);
        	g.fillRect(points[i].x, points[i].y, 5, 5);
    	}
    	
    	java.awt.Toolkit.getDefaultToolkit().sync();  
    	  
    }


    public void actionPerformed(ActionEvent e) {

        if (inGame) 
        {
        	
        }

        repaint();
    }

}

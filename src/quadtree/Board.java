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
<<<<<<< HEAD
	private final int B_WIDTH = 600;
    private final int B_HEIGHT = 600;
=======
	private final int B_WIDTH = 400;
    private final int B_HEIGHT = 400;
>>>>>>> origin/main
    
    private final int DELAY = 100; // Frequencia do game loop
    
    // Jogo ainda em andamento?
    private static boolean inGame = true;    

    // Temporizador para configurar a velocidade do jogo
    private Timer timer;
    
    //Quad Tree Stuff
<<<<<<< HEAD
    private Rectangle rectangle = new Rectangle(1,1,B_WIDTH-3, B_HEIGHT-3);
=======
    private Rectangle rectangle = new Rectangle(getWidth()/2 ,getHeight()/2, 400, 400);
>>>>>>> origin/main
    private QuadTree quadTree = new QuadTree(rectangle, 4);
    static List<QuadTree> quads = new ArrayList();
    
    Point[] points;
<<<<<<< HEAD
	int quantity = 100;
=======
    public static List <Rectangle> rects = new ArrayList<Rectangle>();
	int quantity = 15;
	int rectCount = 0;
	static int pointCountTest = 0;
>>>>>>> origin/main

	Random rand = new Random();
    
    
    public Board() {
    	
    	rects.add(rectangle);
    	points = new Point[quantity];
    	
        for(int i = 0; i < quantity; i++) {
<<<<<<< HEAD
        	Point point = new Point(rand.nextInt(rectangle.w - 2) + 2, rand.nextInt(rectangle.h - 2) + 2);
        	points[i] = point;
        	points[i].id = i;
        	quadTree.Insert(point);	
        	System.out.println("inserted");
=======
        	Point point = new Point(rand.nextInt(B_WIDTH), rand.nextInt(B_HEIGHT));
        	points[i] = point;
        	points[i].id = i;
        	quadTree.Insert(point);	
>>>>>>> origin/main
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
<<<<<<< HEAD

    	g.drawRect(1,1,B_WIDTH-3, B_HEIGHT-3);
    	g.setColor(Color.black);
    	    	
    	for (int i = 0; i < quads.size(); i++) 
    	{
    		g.drawRect(quads.get(i).rectangle.x, quads.get(i).rectangle.y, quads.get(i).rectangle.w, quads.get(i).rectangle.h);
    	}
    	
=======
    	for(int i = 0; i < rects.size(); i++) {
		    	g.drawRect(rects.get(i).x, rects.get(i).y, rects.get(i).w, rects.get(i).h);
		    	g.setColor(Color.BLUE);
    	}
>>>>>>> origin/main
    	for (int i = 0; i < quantity; i++) 
    	{
    		g.setColor(Color.RED);
        	g.fillRect(points[i].x, points[i].y, 5, 5);
    	}
<<<<<<< HEAD
    	
    	java.awt.Toolkit.getDefaultToolkit().sync();  
    	  
=======
>>>>>>> origin/main
    }


    public void actionPerformed(ActionEvent e) {

        if (inGame) 
        {
        	
        }

        repaint();
    }

}

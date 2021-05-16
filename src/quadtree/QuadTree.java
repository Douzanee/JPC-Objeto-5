package quadtree;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
	private List <Point> points = new ArrayList<Point>();
	int capacity;
	public Rectangle rectangle;
	boolean divided = false;
	static int dividedCount = 0;
	public QuadTree northwest;
	public QuadTree northeast;
	public QuadTree southeast;
	public QuadTree southwest;
	String quadrante;
	
	//rectangles
	public QuadTree(Rectangle rectangle, int capacity) 
	{
		if (quadrante == null) {
			quadrante = "quadrado primordial";
		}
		this.rectangle = rectangle;
		this.capacity = capacity;
		Board.quads.add(this);
	}

	public void Insert(Point point) 
	{
		if(!this.rectangle.Contains(point))
		{
			return;
		}
<<<<<<< HEAD
			
			
		if(points.size() < capacity && !points.contains(point)) 
		{
			points.add(point);
			System.out.println("Point X : " + point.x);
			System.out.println("Point Y : " + point.y);
			System.out.println("Pontos encontrados:  " + points.size() + " em " + " " + quadrante);

		}
		else 
		{			
			if(!divided) 
=======
		
		if(points.size() < capacity) 
		{	
			Board.pointCountTest++;
			System.out.println("Ponto Adicionado " + Board.pointCountTest + " Size: " + points.size() + " Com o ID : " + point.id);
			points.add(point);
		}
		else 
		{
			if(!this.divided) 
>>>>>>> origin/main
			{			
				Subdivide();
				
				for (int i = 0; i < points.size(); i++) 
				{
					this.northwest.Insert(points.get(i));
					this.northeast.Insert(points.get(i));
					this.southeast.Insert(points.get(i));
					this.southwest.Insert(points.get(i));		
				}
			}
						

			this.northwest.Insert(point);
			this.northeast.Insert(point);
			this.southeast.Insert(point);
			this.southwest.Insert(point);
		}

	}
	public void Subdivide() {
		for (int i = 0; i < points.size(); i++)
		{
			System.out.println(" Estava incluso o ID:  " + points.get(i).id);
		}

<<<<<<< HEAD
		Rectangle rectangleNW = new Rectangle(rectangle.x, 
				rectangle.y,
				rectangle.w/2,rectangle.h/2);
		this.northwest = new QuadTree(rectangleNW, capacity);
		this.northwest.quadrante = "northwest";
				
		Rectangle rectangleNE = new Rectangle(rectangle.x + rectangle.w / 2 , 
				rectangle.y,
				rectangle.w/2,rectangle.h/2);
		this.northeast = new QuadTree(rectangleNE, capacity);
		this.northeast.quadrante = "northeast";
		
		Rectangle rectangleSW = new Rectangle(rectangle.x, 
				rectangle.y + rectangle.h/2,
				rectangle.w/2,rectangle.h/2);
		this.southwest = new QuadTree(rectangleSW, capacity);
		this.southwest.quadrante = "southwest";
		
		Rectangle rectangleSE = new Rectangle(rectangle.x + rectangle.w / 2 , 
				rectangle.y + rectangle.h/2,
				rectangle.w/2,rectangle.h/2);
		this.southeast = new QuadTree(rectangleSE, capacity);
		this.southeast.quadrante = "southeast";
		
		divided = true;				
	}
}
=======
        int w = rectangle.w/2;
        int h = rectangle.h/2;

        
        Rectangle rectangleNE = new Rectangle(this.rectangle.x + w, 
                this.rectangle.y + 1,
                w , h);
        this.northeast = new QuadTree(rectangleNE, capacity);

        Rectangle rectangleNW = new Rectangle(this.rectangle.x + 1, 
                this.rectangle.y + 1,
                w,h);
        this.northwest = new QuadTree(rectangleNW, capacity);

        Rectangle rectangleSE = new Rectangle(this.rectangle.x + w, 
                this.rectangle.y + h,
                w,h);
        this.southeast = new QuadTree(rectangleSE, capacity);

        Rectangle rectangleSW = new Rectangle(this.rectangle.x + 1, 
                this.rectangle.y + h,
                w,h);
        this.southwest = new QuadTree(rectangleSW, capacity);

        Board.rects.add(rectangleNE);
        Board.rects.add(rectangleNW);
        Board.rects.add(rectangleSE);
        Board.rects.add(rectangleSW);

        this.divided = true;

>>>>>>> origin/main

    }
}
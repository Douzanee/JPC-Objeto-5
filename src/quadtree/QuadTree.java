package quadtree;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
	List <Point> points = new ArrayList<Point>();
	int capacity;
	public Rectangle rectangle;
	boolean divided = false;
	static int dividedCount = 0;
	public QuadTree northwest;
	public QuadTree northeast;
	public QuadTree southeast;
	public QuadTree southwest;
	
	//rectangles
	public QuadTree(Rectangle rectangle, int capacity) 
	{
		this.rectangle = rectangle;
		this.capacity = capacity;
	}

	public void Insert(Point point) 
	{
		if(!this.rectangle.Contains(point)){
			return;
		}
		
		if(points.size() < capacity) 
		{	
			Board.pointCountTest++;
			System.out.println("Ponto Adicionado " + Board.pointCountTest + " Size: " + points.size() + " Com o ID : " + point.id);
			points.add(point);
		}
		else 
		{
			if(!this.divided) 
			{			
				Subdivide();
			}

			this.northwest.Insert(point);
			this.northeast.Insert(point);
			this.southeast.Insert(point);
			this.southwest.Insert(point);
		}

	}
	public void Subdivide() {

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


    }
}
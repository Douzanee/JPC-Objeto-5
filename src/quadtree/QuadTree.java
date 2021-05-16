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
	
	//rectangles
	public QuadTree(Rectangle rectangle, int capacity) 
	{
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
			
			
		if(points.size() < capacity && !points.contains(point)) 
		{
			points.add(point);
			System.out.println("Point X : " + point.x);
			System.out.println("Point Y : " + point.y);
		}
		else 
		{
			
			if(!divided) 
			{			
				System.out.println(" " + rectangle.x + " " + rectangle.y + " " + rectangle.w + " " + rectangle.h);
				Subdivide();
			}

			this.northwest.Insert(point);
			this.northeast.Insert(point);
			this.southeast.Insert(point);
			this.southwest.Insert(point);
		}
		
	}
	public void Subdivide() {
		System.out.println(points + " Points");

		Rectangle rectangleNW = new Rectangle(rectangle.x + rectangle.w / 2 , 
				rectangle.y,
				rectangle.w/2,rectangle.h/2);
		this.northwest = new QuadTree(rectangleNW, capacity);
		
		Rectangle rectangleNE = new Rectangle(rectangle.x, 
				rectangle.y,
				rectangle.w/2,rectangle.h/2);
		this.northeast = new QuadTree(rectangleNE, capacity);
		
		Rectangle rectangleSW = new Rectangle(rectangle.x + rectangle.w / 2 , 
				rectangle.y + rectangle.h/2,
				rectangle.w/2,rectangle.h/2);
		this.southwest = new QuadTree(rectangleSW, capacity);
		
		Rectangle rectangleSE = new Rectangle(rectangle.x, 
				rectangle.y + rectangle.h/2,
				rectangle.w/2,rectangle.h/2);
		this.southeast = new QuadTree(rectangleSE, capacity);
		divided = true;
		
		System.out.println(" " + rectangleNW.x + " " + rectangleNW.y + " " + rectangleNW.w + " " + rectangleNW.h);
		
	}
}


package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.Table;
import processing.data.TableRow;

public class CafeRubis extends PApplet
{	
	public ArrayList<Product> products = new ArrayList<Product>();
	public ArrayList<Product> bill = new ArrayList<Product>();

	private float sideBorder;
	private float topBorder;

	private void loadData()
	{
		Table t = loadTable("data/cafe.csv", "header");
		for(TableRow row: t.rows())
		{
			Product p = new Product(row);
			products.add(p);
		}
	}


	private void printProducts()
	{
		for(Product p:products)
		{
			println(p);			
		}
	}


	public void displayProducts()
	{
		// line down middle and title
		stroke(0);    // black
        fill(0);
        textAlign(CENTER, CENTER);
		text("Cafe Rubis Till System", width/2, topBorder/2);
		line(width/2, topBorder, width/2, height-topBorder);

		// bill box
		fill(255);
		// Draws a rectangle tlx (top left X), tly (top left Y), width, height
		rect((width/2) + sideBorder, topBorder, width/2 - (sideBorder * 2), height - (topBorder * 2));
		fill(0);
		textAlign(CENTER, CENTER);
		text("My Bill", width-200, topBorder+20);

		// product boxes
		fill(255);
		int numProducts = products.size();
		float spacing = 2.0f;
		float productSpace = height - (topBorder * 2) - (spacing * numProducts);
		float h = productSpace / (float)numProducts;

		for(int i = 0 ; i < products.size(); i++)
        {
			float y = map(i, 0, products.size(), topBorder, height - topBorder);
			rect(sideBorder, y, 200, h-spacing);
		}

		// product text
		for(int i = 0 ; i < products.size(); i++)
        {
			float y = map(i, 0, products.size(), topBorder, height - topBorder);
			fill(0);
			textAlign(LEFT);
			Product p = products.get(i);
			text(p.getName(), sideBorder+4, y+h/2);
			textAlign(RIGHT);
			text(p.getPrice(), sideBorder+200, y+h/2);
		}
	}


	public void mousePressed()
	{
		int numProducts = products.size();
		float spacing = 2.0f;
		float productSpace = height - (topBorder * 2) - (spacing * numProducts);
		float h = productSpace / (float)numProducts;

		for(int i = 0 ; i < products.size() ; i ++)
		{
            float y = map(i, 0, products.size(), topBorder, height - topBorder);
            float y1 = y;  // top of task bar
            float y2 = y + h;  // bottom of task bar
			float x1 = sideBorder;
			float x2 = sideBorder+200;
			
            // has the mouse clicked on a product box
			if (mouseX >= x1 && mouseX <= x2 && mouseY >= y1 && mouseY <= y2)
			{
				Product p = products.get(i);
				stroke(0);   //black
        		noFill();    // just outline
        		ellipse(mouseX, mouseY, 100,100);    // x, y, size
				println("success");
				Product b = new Product(p.getName(),p.getPrice());
				bill.add(b);
				return; // exit method before whichTask is reset
			}
		}		
	}


	public void displayBill()
	{
		//int numProducts = bill.size();
		float spacing = 0.05f;
		//float productSpace = height - (topBorder * 2) - (spacing * numProducts);
		//float h = productSpace / (float)numProducts;
		float total = 0;

		// bill text
		for(int i = 0 ; i < bill.size(); i++)
        {
			float y = map(i, 0, bill.size(), topBorder+50, height - topBorder);
			fill(0);
			textAlign(LEFT);
			Product p = bill.get(i);
			text(p.getName(), (width/2)+sideBorder+4, y+spacing);
			textAlign(RIGHT);
			text(p.getPrice(), width-sideBorder, y+spacing);
			total = total + p.getPrice();
			if(i==bill.size()-1)
			{
				textAlign(LEFT);
				text("Total:", (width/2)+sideBorder+4, y+30);
				textAlign(RIGHT);
				text(total, width-sideBorder, y+30);
			}
		}
		
	}


	public void settings()
	{
		size(800, 600);
	}

	public void setup() 
	{
		loadData();
		printProducts();
		topBorder = width * 0.1f;     // 10% border
		sideBorder = width * 0.05f;     // 5% border
        colorMode(RGB);
	}
	

	public void draw()
	{			
		background(168,170,172);
		displayProducts();
		displayBill();
	}
}

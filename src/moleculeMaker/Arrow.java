package moleculeMaker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
/**
 * To heck with arclines!
 * @author Ian Graham
 *
 */
public class Arrow extends MoleculeConnectorComponent
{
	
	/**
	 * This is the order in which this arrow occurs in the problem. 1 Denotes this is the
	 * first arrow that needs to be drawn, 2 denotes this is the second, 3 denotes this is
	 * the third. Supposedly valid input is 2-3 but I don't get that yet so 1 is a default
	 * value.
	 */
	private int problemOrder;

	public Arrow(MoleculeComponent c1, MoleculeComponent c2)
	{
		super();

		System.out.println("Bond being created using: " + c1 + " and " + c2);

		if (c1 == null || c2 == null) {
			System.out.println("Bonder or bondee is null");
			return;
		}
		dragColor = Color.BLUE; // set the drag color for arrow lines
		setConnectionAttributes(c1, c2);
		recalculateMiddleXY();
		problemOrder = 1; //Default to 1. This can be a problem if the default constructor
						  //is used too much.
	}
	
	public Arrow(MoleculeComponent c1, MoleculeComponent c2, int problemOrder)
	{
		super();

		System.out.println("Bond being created using: " + c1 + " and " + c2);

		if (c1 == null || c2 == null) {
			System.out.println("Bonder or bondee is null");
			return;
		}

		dragColor = Color.BLUE; // set the drag color for arrow lines
		setConnectionAttributes(c1, c2);
		recalculateMiddleXY();
		setProblemOrder(problemOrder);
	}
	
	public void draw(Graphics g, int offset, int offset_y)
	{
		// Draw the arrow and the mid-point selection circle
		super.draw(g, offset, offset_y);
		
		// Now draw the arrow's head
		Arrow.drawBarbs(g, connector, connectee);
	}

	public static void drawBarbs(Graphics g2, MoleculeComponent tip, MoleculeComponent tail)
	{
		double barbLength = 10;
		double angle = Math.toRadians(35);
		double dy = tip.y - tail.y;
		double dx = tip.x - tail.x;
//		System.out.println("Change in Y: "+dy);
//		System.out.println("Change in X: "+dx);
		double theta = Math.atan2(dy, dx);
//		System.out.println("Angle of Theta: "+theta);
		double x, y, rho = theta + angle;
		
		for(int i = 0; i < 2; i++)
		{
//			System.out.println("Value of angle Rho: "+Math.toDegrees(rho));
			x = tip.x - barbLength * Math.cos(rho);
			y = tip.y - barbLength * Math.sin(rho);
			((Graphics2D) g2).draw(new Line2D.Double(tip.x, tip.y, x, y));
			rho = theta - angle;
		}
	}

	public int getProblemOrder() {
		return problemOrder;
	}

	public void setProblemOrder(int problemOrder) {
		if(problemOrder >= 2 && problemOrder <= 3)
		{
			this.problemOrder = problemOrder;
		}
		else
		{
			this.problemOrder = 1; //Default value just like Bond, for now.
		}
	}
}

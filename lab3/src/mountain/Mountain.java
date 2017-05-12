package mountain;

import java.util.Iterator;
import java.util.LinkedList;

import fractal.*;

public class Mountain extends Fractal {
	private Point a;
	private Point b;
	private Point c;
	private LinkedList<Side> list;
	private double dev;

	public Mountain(Point a, Point b, Point c, double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
		list = new LinkedList<Side>();

	}

	public String getTitle() {
		return "Bergfraktal";
	}

	public void draw(TurtleGraphics turtle) {
		fractalLine(turtle, order, a, b, c, dev);
	}

	/*
	 * Recursive method: Draws a recursive line of the triangle.
	 */
	private Point getMid(Point one, Point two, double dev) {
		Iterator<Side> itr = list.iterator();
		while (itr.hasNext()) {
			Side temp = itr.next();
			if ((temp.getP1().equals(one) && temp.getP2().equals(two))
					|| (temp.getP1().equals(two) && temp.getP2().equals(one))) {
				itr.remove();
				return temp.getM();
			}

		}
		Side ny = new Side(one, two, dev);
		list.add(ny);
		return ny.getM();

	}

	private void fractalLine(TurtleGraphics turtle, int order, Point a, Point b, Point c, double dev) {
		turtle.penDown();
		double dev1 = RandomUtilities.randFunc(dev);
		double dev2 = RandomUtilities.randFunc(dev);
		double dev3 = RandomUtilities.randFunc(dev);

		Point ab = getMid(a, b, dev1);
		Point ac = getMid(a, c, dev2);
		Point bc = getMid(b, c, dev3);

		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());

		} else {
			fractalLine(turtle, order - 1, a, ab, ac, dev / 2);
			fractalLine(turtle, order - 1, ab, b, bc, dev / 2);
			fractalLine(turtle, order - 1, ac, bc, c, dev / 2);
			fractalLine(turtle, order - 1, ab, ac, bc, dev / 2);

		}
	}
}

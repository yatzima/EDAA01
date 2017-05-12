package Triangle;

import fractal.*;
import mountain.Point;

public class Triangle extends Fractal {
	private Point a;
	private Point b;
	private Point c;

	/**
	 * Creates an object that handles Koch's fractal.
	 * 
	 * @param length
	 *            the length of the triangle side
	 */
	public Triangle(Point a, Point b, Point c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * Returns the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return "Sierpinski's triangel";
	}

	/**
	 * Draws the fractal.
	 * 
	 * @param turtle
	 *            the turtle graphic object
	 */
	public void draw(TurtleGraphics turtle) {
		fractalLine(turtle, order, a, b, c);

	}

	private Point getMid(Point one, Point two) {
		return new Point((one.getX() + two.getX()) / 2, (one.getY() + two.getY()) / 2);
	}

	/*
	 * Recursive method: Draws a recursive line of the triangle.
	 */
	private void fractalLine(TurtleGraphics turtle, int order, Point a, Point b, Point c) {
		turtle.penDown();

		Point ab = getMid(a, b);
		Point ac = getMid(a, c);
		Point bc = getMid(b, c);
		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} else {
			fractalLine(turtle, order - 1, a, ab, ac);
			fractalLine(turtle, order - 1, ab, b, bc);
			fractalLine(turtle, order - 1, ac, bc, c);
			fractalLine(turtle, order - 1, ab, ac, bc);
		}

	}
}

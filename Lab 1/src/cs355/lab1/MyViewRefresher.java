package cs355.lab1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;

import cs355.ViewRefresher;

public class MyViewRefresher implements ViewRefresher {

	MyModel model;
	MyController controller;

	public MyViewRefresher(MyModel model, MyController controller) {
		this.model = model;
		this.controller = controller;
	}

	private void drawLine(Graphics2D g2d, Line line) {

		g2d.drawLine((int) Math.round(line.getX1()),
				(int) Math.round(line.getY1()), (int) Math.round(line.getX2()),
				(int) Math.round(line.getY2()));
	}

	private void drawSquare(Graphics2D g2d, Square square) {

		g2d.fillRect((int) Math.round(-square.getSize() / 2),
				(int) Math.round(-square.getSize() / 2),
				(int) Math.round(square.getSize()),
				(int) Math.round(square.getSize()));
	}

	private void drawRectangle(Graphics2D g2d, Rectangle rectangle) {

		g2d.fillRect((int) Math.round(-rectangle.getWidth() / 2),
				(int) Math.round(-rectangle.getHeight() / 2),
				(int) Math.round(rectangle.getWidth()),
				(int) Math.round(rectangle.getHeight()));
	}

	private void drawCircle(Graphics2D g2d, Circle circle) {

		g2d.fillOval((int) Math.round(-circle.getRadius()),
				(int) Math.round(-circle.getRadius()),
				(int) Math.round(circle.getRadius() * 2),
				(int) Math.round(circle.getRadius() * 2));
	}

	private void drawEllipse(Graphics2D g2d, Ellipse ellipse) {

		g2d.fillOval((int) Math.round(-ellipse.getWidth() / 2),
				(int) Math.round(-ellipse.getHeight() / 2),
				(int) Math.round(ellipse.getWidth()),
				(int) Math.round(ellipse.getHeight()));
	}

	private void drawTriangle(Graphics2D g2d, Triangle triangle) {

		g2d.fillPolygon(
				new int[] { (int) Math.round(triangle.getX1()),
						(int) Math.round(triangle.getX2()),
						(int) Math.round(triangle.getX3()) },
				new int[] { (int) Math.round(triangle.getY1()),
						(int) Math.round(triangle.getY2()),
						(int) Math.round(triangle.getY3()) }, 3);
	}

	private void drawSelectedOutline(Graphics2D g2d) {

		if (controller.getSelected() == null) {
			return;
		}

		if (controller.getSelected() instanceof Line) {

			Line line = (Line) controller.getSelected();

			g2d.setColor(line.getColor());
			drawLine(g2d, line);

			// Point 1

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(line.getX1()) - 3,
					(int) Math.round(line.getY1() - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(line.getX1()) - 3,
					(int) Math.round(line.getY1()) - 3, 6, 6);

			// Point 2

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(line.getX2()) - 3,
					(int) Math.round(line.getY2() - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(line.getX2()) - 3,
					(int) Math.round(line.getY2()) - 3, 6, 6);
		}

		Color color = controller.getSelected().getColor();
		g2d.setColor(new Color(255 - color.getRed(), 255 - color.getGreen(),
				255 - color.getBlue()));

		AffineTransform transform = g2d.getTransform();

		g2d.translate((int) controller.getSelected().getX(), (int) controller
				.getSelected().getY());

		if (controller.getSelected().getRotation() != 0) {
			g2d.rotate(controller.getSelected().getRotation());
		}

		if (controller.getSelected() instanceof Square) {

			Square square = (Square) controller.getSelected();

			g2d.drawRect((int) Math.round(-square.getSize() / 2),
					(int) Math.round(-square.getSize() / 2),
					(int) Math.round(square.getSize()),
					(int) Math.round(square.getSize()));

			// Upper left

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(-square.getSize() / 2) - 3,
					(int) Math.round(-square.getSize() / 2 - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(-square.getSize() / 2) - 3,
					(int) Math.round(-square.getSize() / 2 - 3), 6, 6);

			// Upper right

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(square.getSize() / 2) - 3,
					(int) Math.round(-square.getSize() / 2 - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(square.getSize() / 2) - 3,
					(int) Math.round(-square.getSize() / 2 - 3), 6, 6);

			// Lower left

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(-square.getSize() / 2) - 3,
					(int) Math.round(square.getSize() / 2 - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(-square.getSize() / 2) - 3,
					(int) Math.round(square.getSize() / 2 - 3), 6, 6);

			// Lower right

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(square.getSize() / 2) - 3,
					(int) Math.round(square.getSize() / 2 - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(square.getSize() / 2) - 3,
					(int) Math.round(square.getSize() / 2 - 3), 6, 6);

			// Rotation

			g2d.setColor(Color.GREEN);

			g2d.fillOval(-3, (int) Math.round(-square.getSize() * 3 / 4 - 3),
					6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval(-3, (int) Math.round(-square.getSize() * 3 / 4 - 3),
					6, 6);

		} else if (controller.getSelected() instanceof Rectangle) {

			Rectangle rectangle = (Rectangle) controller.getSelected();

			g2d.drawRect((int) Math.round(-rectangle.getWidth() / 2),
					(int) Math.round(-rectangle.getHeight() / 2),
					(int) Math.round(rectangle.getWidth()),
					(int) Math.round(rectangle.getHeight()));

			// Upper left

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(-rectangle.getWidth() / 2) - 3,
					(int) Math.round(-rectangle.getHeight() / 2 - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(-rectangle.getWidth() / 2) - 3,
					(int) Math.round(-rectangle.getHeight() / 2 - 3), 6, 6);

			// Upper right

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(rectangle.getWidth() / 2) - 3,
					(int) Math.round(-rectangle.getHeight() / 2 - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(rectangle.getWidth() / 2) - 3,
					(int) Math.round(-rectangle.getHeight() / 2 - 3), 6, 6);

			// Lower left

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(-rectangle.getWidth() / 2) - 3,
					(int) Math.round(rectangle.getHeight() / 2 - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(-rectangle.getWidth() / 2) - 3,
					(int) Math.round(rectangle.getHeight() / 2 - 3), 6, 6);

			// Lower right

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(rectangle.getWidth() / 2) - 3,
					(int) Math.round(rectangle.getHeight() / 2 - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(rectangle.getWidth() / 2) - 3,
					(int) Math.round(rectangle.getHeight() / 2 - 3), 6, 6);

			// Rotation

			g2d.setColor(Color.GREEN);

			g2d.fillOval(-3,
					(int) Math.round(-rectangle.getHeight() * 3 / 4 - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval(-3,
					(int) Math.round(-rectangle.getHeight() * 3 / 4 - 3), 6, 6);

		} else if (controller.getSelected() instanceof Circle) {

			Circle circle = (Circle) controller.getSelected();

			g2d.drawOval((int) Math.round(-circle.getRadius()),
					(int) Math.round(-circle.getRadius()),
					(int) Math.round(circle.getRadius() * 2),
					(int) Math.round(circle.getRadius() * 2));

			// Upper left

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(-circle.getRadius()) - 3,
					(int) Math.round(-circle.getRadius() - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(-circle.getRadius()) - 3,
					(int) Math.round(-circle.getRadius() - 3), 6, 6);

			// Upper right

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(circle.getRadius()) - 3,
					(int) Math.round(-circle.getRadius() - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(circle.getRadius()) - 3,
					(int) Math.round(-circle.getRadius() - 3), 6, 6);

			// Lower left

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(-circle.getRadius()) - 3,
					(int) Math.round(circle.getRadius() - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(-circle.getRadius()) - 3,
					(int) Math.round(circle.getRadius() - 3), 6, 6);

			// Lower right

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(circle.getRadius()) - 3,
					(int) Math.round(circle.getRadius() - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(circle.getRadius()) - 3,
					(int) Math.round(circle.getRadius() - 3), 6, 6);

		} else if (controller.getSelected() instanceof Ellipse) {

			Ellipse ellipse = (Ellipse) controller.getSelected();

			g2d.drawOval((int) Math.round(-ellipse.getWidth() / 2),
					(int) Math.round(-ellipse.getHeight() / 2),
					(int) Math.round(ellipse.getWidth()),
					(int) Math.round(ellipse.getHeight()));

			// Upper left

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(-ellipse.getWidth() / 2) - 3,
					(int) Math.round(-ellipse.getHeight() / 2 - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(-ellipse.getWidth() / 2) - 3,
					(int) Math.round(-ellipse.getHeight() / 2 - 3), 6, 6);

			// Upper right

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(ellipse.getWidth() / 2) - 3,
					(int) Math.round(-ellipse.getHeight() / 2 - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(ellipse.getWidth() / 2) - 3,
					(int) Math.round(-ellipse.getHeight() / 2 - 3), 6, 6);

			// Lower left

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(-ellipse.getWidth() / 2) - 3,
					(int) Math.round(ellipse.getHeight() / 2 - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(-ellipse.getWidth() / 2) - 3,
					(int) Math.round(ellipse.getHeight() / 2 - 3), 6, 6);

			// Lower right

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(ellipse.getWidth() / 2) - 3,
					(int) Math.round(ellipse.getHeight() / 2 - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(ellipse.getWidth() / 2) - 3,
					(int) Math.round(ellipse.getHeight() / 2 - 3), 6, 6);

			// Rotation

			g2d.setColor(Color.GREEN);

			g2d.fillOval(-3,
					(int) Math.round(-ellipse.getHeight() * 3 / 4 - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval(-3,
					(int) Math.round(-ellipse.getHeight() * 3 / 4 - 3), 6, 6);

		} else if (controller.getSelected() instanceof Triangle) {

			Triangle triangle = (Triangle) controller.getSelected();

			g2d.drawPolygon(
					new int[] { (int) Math.round(triangle.getX1()),
							(int) Math.round(triangle.getX2()),
							(int) Math.round(triangle.getX3()) },
					new int[] { (int) Math.round(triangle.getY1()),
							(int) Math.round(triangle.getY2()),
							(int) Math.round(triangle.getY3()) }, 3);

			// Corner 1

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(triangle.getX1()) - 3,
					(int) Math.round(triangle.getY1() - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(triangle.getX1()) - 3,
					(int) Math.round(triangle.getY1()) - 3, 6, 6);

			// Corner 2

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(triangle.getX2()) - 3,
					(int) Math.round(triangle.getY2() - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(triangle.getX2()) - 3,
					(int) Math.round(triangle.getY2()) - 3, 6, 6);

			// Corner 3

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(triangle.getX3()) - 3,
					(int) Math.round(triangle.getY3() - 3), 6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(triangle.getX3()) - 3,
					(int) Math.round(triangle.getY3()) - 3, 6, 6);

			// Rotation

			g2d.setColor(Color.GREEN);

			g2d.fillOval(-3, (int) Math.round(Math.min(triangle.getY1(),
					Math.min(triangle.getY2(), triangle.getY3())) * 3 / 2 - 3),
					6, 6);

			g2d.setColor(Color.BLACK);

			g2d.drawOval(-3, (int) Math.round(Math.min(triangle.getY1(),
					Math.min(triangle.getY2(), triangle.getY3())) * 3 / 2 - 3),
					6, 6);

		}

		g2d.setTransform(transform);
	}

	@Override
	public void refreshView(Graphics2D g2d) {

		List<Shape> shapes = model.getShapes();

		for (Shape s : shapes) {

			g2d.setColor(s.getColor());

			if (s instanceof Line) {

				Line line = (Line) s;
				drawLine(g2d, line);
				continue;
			}

			AffineTransform transform = g2d.getTransform();

			g2d.translate((int) s.getX(), (int) s.getY());

			if (s.getRotation() != 0) {
				g2d.rotate(s.getRotation());
			}

			if (s instanceof Square) {

				Square square = (Square) s;
				drawSquare(g2d, square);
			}

			else if (s instanceof Rectangle) {

				Rectangle rectangle = (Rectangle) s;
				drawRectangle(g2d, rectangle);
			}

			else if (s instanceof Circle) {

				Circle circle = (Circle) s;
				drawCircle(g2d, circle);
			}

			else if (s instanceof Ellipse) {

				Ellipse ellipse = (Ellipse) s;
				drawEllipse(g2d, ellipse);
			}

			else if (s instanceof Triangle) {

				Triangle triangle = (Triangle) s;
				drawTriangle(g2d, triangle);
			}

			g2d.setTransform(transform);
		}

		drawSelectedOutline(g2d);
	}
}

package cs355.lab2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;

import cs355.ViewRefresher;
import cs355.lab5.Render3D;
import cs355.lab6.ImageModel;

public class MyViewRefresher implements ViewRefresher {

	private MyModel model;
	private MyController controller;
	private Viewport viewport;
	private Render3D render3D;
	private ImageModel imageModel;

	public MyViewRefresher(MyModel model, MyController controller) {
		this.model = model;
		this.controller = controller;
		viewport = controller.getViewport();
		render3D = new Render3D(controller.getCamera());
		imageModel = controller.getImageModel();
	}

	private void drawLine(Graphics2D g2d, Line line) {

		g2d.setStroke(new BasicStroke((float) (1 / viewport.getScale())));

		g2d.drawLine((int) Math.round(line.getX1()),
				(int) Math.round(line.getY1()), (int) Math.round(line.getX2()),
				(int) Math.round(line.getY2()));

		g2d.setStroke(new BasicStroke());
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

		AffineTransform transform = g2d.getTransform();

		transform.concatenate(viewport.getWorldToView());

		g2d.setStroke(new BasicStroke((float) (1 / viewport.getScale())));

		if (controller.getSelected() instanceof Line) {

			Line line = (Line) controller.getSelected();

			g2d.setColor(line.getColor());
			g2d.setTransform(transform);
			drawLine(g2d, line);

			// Point 1

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(line.getX1() - 3 / viewport.getScale()),
					(int) Math.round(line.getY1() - 3 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(line.getX1() - 3 / viewport.getScale()),
					(int) Math.round(line.getY1() - 3 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Point 2

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(line.getX2() - 3 / viewport.getScale()),
					(int) Math.round(line.getY2() - 3 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(line.getX2() - 3 / viewport.getScale()),
					(int) Math.round(line.getY2() - 3 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));
		}

		Color color = controller.getSelected().getColor();
		g2d.setColor(new Color(255 - color.getRed(), 255 - color.getGreen(),
				255 - color.getBlue()));

		AffineTransform original = g2d.getTransform();

		transform.concatenate(new AffineTransform(new double[] { 1, 0, 0, 1,
				controller.getSelected().getX(),
				controller.getSelected().getY() }));

		transform.concatenate(new AffineTransform(new double[] {
				Math.cos(controller.getSelected().getRotation()),
				Math.sin(controller.getSelected().getRotation()),
				-Math.sin(controller.getSelected().getRotation()),
				Math.cos(controller.getSelected().getRotation()) }));

		g2d.setTransform(transform);

		if (controller.getSelected() instanceof Square) {

			Square square = (Square) controller.getSelected();

			g2d.drawRect((int) Math.round(-square.getSize() / 2),
					(int) Math.round(-square.getSize() / 2),
					(int) Math.round(square.getSize()),
					(int) Math.round(square.getSize()));

			// Upper left

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(-square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(-square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(-square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(-square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Upper right

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(-square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(-square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Lower left

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(-square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(-square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Lower right

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(square.getSize() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Rotation

			g2d.setColor(Color.GREEN);

			g2d.fillOval(
					(int) Math.round(-3 / viewport.getScale()),
					(int) Math.round(-square.getSize() * 3 / 4 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(-3 / viewport.getScale()),
					(int) Math.round(-square.getSize() * 3 / 4 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

		} else if (controller.getSelected() instanceof Rectangle) {

			Rectangle rectangle = (Rectangle) controller.getSelected();

			g2d.drawRect((int) Math.round(-rectangle.getWidth() / 2),
					(int) Math.round(-rectangle.getHeight() / 2),
					(int) Math.round(rectangle.getWidth()),
					(int) Math.round(rectangle.getHeight()));

			// Upper left

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(-rectangle.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(-rectangle.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(-rectangle.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(-rectangle.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Upper right

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(rectangle.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(-rectangle.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(rectangle.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(-rectangle.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Lower left

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(-rectangle.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(rectangle.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(-rectangle.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(rectangle.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Lower right

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(rectangle.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(rectangle.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(rectangle.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(rectangle.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Rotation

			g2d.setColor(Color.GREEN);

			g2d.fillOval(
					-3,
					(int) Math.round(-rectangle.getHeight() * 3 / 4 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

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

			g2d.fillOval(
					(int) Math.round(-circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(-circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(-circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(-circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Upper right

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(-circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(-circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Lower left

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(-circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(-circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Lower right

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(circle.getRadius() - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

		} else if (controller.getSelected() instanceof Ellipse) {

			Ellipse ellipse = (Ellipse) controller.getSelected();

			g2d.drawOval((int) Math.round(-ellipse.getWidth() / 2),
					(int) Math.round(-ellipse.getHeight() / 2),
					(int) Math.round(ellipse.getWidth()),
					(int) Math.round(ellipse.getHeight()));

			// Upper left

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(-ellipse.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(-ellipse.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(-ellipse.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(-ellipse.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Upper right

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(ellipse.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(-ellipse.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(ellipse.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(-ellipse.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Lower left

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(-ellipse.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(ellipse.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(-ellipse.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(ellipse.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Lower right

			g2d.setColor(Color.WHITE);

			g2d.fillOval(
					(int) Math.round(ellipse.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(ellipse.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(ellipse.getWidth() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(ellipse.getHeight() / 2 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			// Rotation

			g2d.setColor(Color.GREEN);

			g2d.fillOval(
					(int) Math.round(-3 / viewport.getScale()),
					(int) Math.round(-ellipse.getHeight() * 3 / 4 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(-3 / viewport.getScale()),
					(int) Math.round(-ellipse.getHeight() * 3 / 4 - 3
							/ viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

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

			g2d.fillOval((int) Math.round(triangle.getX1() - 3
					/ viewport.getScale()), (int) Math.round(triangle.getY1()
					- 3 / viewport.getScale()), (int) Math.round(6 / viewport
					.getScale()), (int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(triangle.getX1() - 3
					/ viewport.getScale()), (int) Math.round(triangle.getY1()
					- 3 / viewport.getScale()), (int) Math.round(6 / viewport
					.getScale()), (int) Math.round(6 / viewport.getScale()));

			// Corner 2

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(triangle.getX2() - 3
					/ viewport.getScale()), (int) Math.round(triangle.getY2()
					- 3 / viewport.getScale()), (int) Math.round(6 / viewport
					.getScale()), (int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(triangle.getX2() - 3
					/ viewport.getScale()), (int) Math.round(triangle.getY2()
					- 3 / viewport.getScale()), (int) Math.round(6 / viewport
					.getScale()), (int) Math.round(6 / viewport.getScale()));

			// Corner 3

			g2d.setColor(Color.WHITE);

			g2d.fillOval((int) Math.round(triangle.getX3() - 3
					/ viewport.getScale()), (int) Math.round(triangle.getY3()
					- 3 / viewport.getScale()), (int) Math.round(6 / viewport
					.getScale()), (int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval((int) Math.round(triangle.getX3() - 3
					/ viewport.getScale()), (int) Math.round(triangle.getY3()
					- 3 / viewport.getScale()), (int) Math.round(6 / viewport
					.getScale()), (int) Math.round(6 / viewport.getScale()));

			// Rotation

			g2d.setColor(Color.GREEN);

			g2d.fillOval(
					(int) Math.round(-3 / viewport.getScale()),
					(int) Math.round(Math.min(triangle.getY1(),
							Math.min(triangle.getY2(), triangle.getY3()))
							* 3 / 2 - 3 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

			g2d.setColor(Color.BLACK);

			g2d.drawOval(
					(int) Math.round(-3 / viewport.getScale()),
					(int) Math.round(Math.min(triangle.getY1(),
							Math.min(triangle.getY2(), triangle.getY3()))
							* 3 / 2 - 3 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()),
					(int) Math.round(6 / viewport.getScale()));

		}

		g2d.setTransform(original);
	}

	@Override
	public void refreshView(Graphics2D g2d) {

		if (controller.getDisplayImage() && imageModel.getPixels() != null) {
			AffineTransform original = g2d.getTransform();
			g2d.setTransform(viewport.getWorldToView());

			BufferedImage image = new BufferedImage(imageModel.getWidth(),
					imageModel.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

			for (int i = 0; i < image.getHeight(); i++) {
				for (int j = 0; j < image.getWidth(); j++) {
					image.getRaster().setPixel(j, i,
							new int[] { imageModel.getPixels()[i][j] });
				}
			}

			g2d.drawImage(image, 1024 - image.getWidth() / 2,
					1024 - image.getHeight() / 2, image.getWidth(),
					image.getHeight(), null);

			g2d.setTransform(original);
		}

		List<Shape> shapes = model.getShapes();

		for (Shape s : shapes) {

			g2d.setColor(s.getColor());

			if (s instanceof Line) {

				AffineTransform original = g2d.getTransform();

				AffineTransform transform = new AffineTransform();

				transform.concatenate(viewport.getWorldToView());

				g2d.setTransform(transform);

				Line line = (Line) s;
				drawLine(g2d, line);
				g2d.setTransform(original);

				continue;
			}

			AffineTransform original = g2d.getTransform();

			AffineTransform transform = new AffineTransform();

			transform.concatenate(viewport.getWorldToView());

			transform.concatenate(new AffineTransform(new double[] { 1, 0, 0,
					1, s.getX(), s.getY() }));

			transform.concatenate(new AffineTransform(new double[] {
					Math.cos(s.getRotation()), Math.sin(s.getRotation()),
					-Math.sin(s.getRotation()), Math.cos(s.getRotation()) }));

			g2d.setTransform(transform);

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

			g2d.setTransform(original);
		}

		drawSelectedOutline(g2d);

		if (controller.getDisplay3D()) {
			g2d.setStroke(new BasicStroke((float) (1 / viewport.getScale())));
			g2d.setTransform(viewport.getWorldToView());
			render3D.draw(g2d);
		}
	}
}

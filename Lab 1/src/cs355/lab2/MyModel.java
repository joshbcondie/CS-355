package cs355.lab2;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MyModel {

	private List<Shape> shapes;

	public MyModel() {
		shapes = new ArrayList<>();
	}

	public List<Shape> getShapes() {
		return shapes;
	}

	public void addShape(Shape shape) {
		shapes.add(shape);
	}

	public void removeShape(Shape shape) {
		shapes.remove(shape);
	}

	public Point2D testRotationHandle(double x, double y, Shape selected,
			double scale) {

		Point2D point = Transformation.worldToObject(selected, x, y);
		x = point.getX();
		y = point.getY();

		if (selected instanceof Square) {

			Square square = (Square) selected;

			if ((x) * (x) + (y + square.getSize() * 3 / 4)
					* (y + square.getSize() * 3 / 4) <= (3 / scale)
					* (3 / scale)) {
				return new Point2D.Double(selected.getX(), selected.getY()
						- square.getSize() * 3 / 4);
			}
		} else if (selected instanceof Rectangle) {

			Rectangle rectangle = (Rectangle) selected;

			if ((x) * (x) + (y + rectangle.getHeight() * 3 / 4)
					* (y + rectangle.getHeight() * 3 / 4) <= (3 / scale)
					* (3 / scale)) {
				return new Point2D.Double(selected.getX(), selected.getY()
						- rectangle.getHeight() * 3 / 4);
			}
		} else if (selected instanceof Ellipse) {

			Ellipse ellipse = (Ellipse) selected;

			if ((x) * (x) + (y + ellipse.getHeight() * 3 / 4)
					* (y + ellipse.getHeight() * 3 / 4) <= (3 / scale)
					* (3 / scale)) {
				return new Point2D.Double(selected.getX(), selected.getY()
						- ellipse.getHeight() * 3 / 4);
			}
		} else if (selected instanceof Triangle) {

			Triangle triangle = (Triangle) selected;

			if ((x)
					* (x)
					+ (y - Math.min(triangle.getY1(),
							Math.min(triangle.getY2(), triangle.getY3())) * 3 / 2)
					* (y - Math.min(triangle.getY1(),
							Math.min(triangle.getY2(), triangle.getY3())) * 3 / 2) <= (3 / scale)
					* (3 / scale)) {
				return new Point2D.Double(selected.getX()
						+ Math.min(triangle.getY1(),
								Math.min(triangle.getY2(), triangle.getY3())),
						selected.getY()
								+ Math.min(
										triangle.getY1(),
										Math.min(triangle.getY2(),
												triangle.getY3())));
			}
		}

		return null;
	}

	public Point2D testHandles(double x, double y, Shape selected, double scale) {

		if (selected instanceof Line) {

			Line line = (Line) selected;

			if ((x - line.getX1()) * (x - line.getX1()) + (y - line.getY1())
					* (y - line.getY1()) <= (3 / scale) * (3 / scale)) {

				double x1 = line.getX1();
				double y1 = line.getY1();
				line.setX1(line.getX2());
				line.setY1(line.getY2());
				line.setX2(x1);
				line.setY2(y1);

				return new Point2D.Double(line.getX2(), line.getY2());
			} else if ((x - line.getX2()) * (x - line.getX2())
					+ (y - line.getY2()) * (y - line.getY2()) <= (3 / scale)
					* (3 / scale)) {
				return new Point2D.Double(line.getX2(), line.getY2());
			}
		}

		Point2D point = Transformation.worldToObject(selected, x, y);
		x = point.getX();
		y = point.getY();

		if (selected instanceof Square) {

			Square square = (Square) selected;

			if ((x + square.getSize() / 2) * (x + square.getSize() / 2)
					+ (y + square.getSize() / 2) * (y + square.getSize() / 2) <= (3 / scale)
					* (3 / scale)) {
				Point2D world = Transformation.objectToWorld(square,
						square.getSize() / 2, square.getSize() / 2);
				return new Point2D.Double(world.getX(), world.getY());
			} else if ((x - square.getSize() / 2) * (x - square.getSize() / 2)
					+ (y + square.getSize() / 2) * (y + square.getSize() / 2) <= (3 / scale)
					* (3 / scale)) {
				Point2D world = Transformation.objectToWorld(square,
						-square.getSize() / 2, square.getSize() / 2);
				return new Point2D.Double(world.getX(), world.getY());
			} else if ((x + square.getSize() / 2) * (x + square.getSize() / 2)
					+ (y - square.getSize() / 2) * (y - square.getSize() / 2) <= (3 / scale)
					* (3 / scale)) {
				Point2D world = Transformation.objectToWorld(square,
						square.getSize() / 2, -square.getSize() / 2);
				return new Point2D.Double(world.getX(), world.getY());
			} else if ((x - square.getSize() / 2) * (x - square.getSize() / 2)
					+ (y - square.getSize() / 2) * (y - square.getSize() / 2) <= (3 / scale)
					* (3 / scale)) {
				Point2D world = Transformation.objectToWorld(square,
						-square.getSize() / 2, -square.getSize() / 2);
				return new Point2D.Double(world.getX(), world.getY());
			}
		} else if (selected instanceof Rectangle) {

			Rectangle rectangle = (Rectangle) selected;

			if ((x + rectangle.getWidth() / 2) * (x + rectangle.getWidth() / 2)
					+ (y + rectangle.getHeight() / 2)
					* (y + rectangle.getHeight() / 2) <= (3 / scale)
					* (3 / scale)) {

				Point2D world = Transformation.objectToWorld(rectangle,
						rectangle.getWidth() / 2, rectangle.getHeight() / 2);
				return new Point2D.Double(world.getX(), world.getY());
			} else if ((x - rectangle.getWidth() / 2)
					* (x - rectangle.getWidth() / 2)
					+ (y + rectangle.getHeight() / 2)
					* (y + rectangle.getHeight() / 2) <= (3 / scale)
					* (3 / scale)) {
				Point2D world = Transformation.objectToWorld(rectangle,
						-rectangle.getWidth() / 2, rectangle.getHeight() / 2);
				return new Point2D.Double(world.getX(), world.getY());
			} else if ((x + rectangle.getWidth() / 2)
					* (x + rectangle.getWidth() / 2)
					+ (y - rectangle.getHeight() / 2)
					* (y - rectangle.getHeight() / 2) <= (3 / scale)
					* (3 / scale)) {
				Point2D world = Transformation.objectToWorld(rectangle,
						rectangle.getWidth() / 2, -rectangle.getHeight() / 2);
				return new Point2D.Double(world.getX(), world.getY());
			} else if ((x - rectangle.getWidth() / 2)
					* (x - rectangle.getWidth() / 2)
					+ (y - rectangle.getHeight() / 2)
					* (y - rectangle.getHeight() / 2) <= (3 / scale)
					* (3 / scale)) {
				Point2D world = Transformation.objectToWorld(rectangle,
						-rectangle.getWidth() / 2, -rectangle.getHeight() / 2);
				return new Point2D.Double(world.getX(), world.getY());
			}
		} else if (selected instanceof Circle) {

			Circle circle = (Circle) selected;

			if ((x + circle.getRadius()) * (x + circle.getRadius())
					+ (y + circle.getRadius()) * (y + circle.getRadius()) <= (3 / scale)
					* (3 / scale)) {
				return new Point2D.Double(selected.getX() + circle.getRadius(),
						selected.getY() + circle.getRadius());
			} else if ((x - circle.getRadius()) * (x - circle.getRadius())
					+ (y + circle.getRadius()) * (y + circle.getRadius()) <= (3 / scale)
					* (3 / scale)) {
				return new Point2D.Double(selected.getX() - circle.getRadius(),
						selected.getY() + circle.getRadius());
			} else if ((x + circle.getRadius()) * (x + circle.getRadius())
					+ (y - circle.getRadius()) * (y - circle.getRadius()) <= (3 / scale)
					* (3 / scale)) {
				return new Point2D.Double(selected.getX() + circle.getRadius(),
						selected.getY() - circle.getRadius());
			} else if ((x - circle.getRadius()) * (x - circle.getRadius())
					+ (y - circle.getRadius()) * (y - circle.getRadius()) <= (3 / scale)
					* (3 / scale)) {
				return new Point2D.Double(selected.getX() - circle.getRadius(),
						selected.getY() - circle.getRadius());
			}
		} else if (selected instanceof Ellipse) {

			Ellipse ellipse = (Ellipse) selected;

			if ((x + ellipse.getWidth() / 2) * (x + ellipse.getWidth() / 2)
					+ (y + ellipse.getHeight() / 2)
					* (y + ellipse.getHeight() / 2) <= (3 / scale)
					* (3 / scale)) {
				Point2D world = Transformation.objectToWorld(ellipse,
						ellipse.getWidth() / 2, ellipse.getHeight() / 2);
				return new Point2D.Double(world.getX(), world.getY());
			} else if ((x - ellipse.getWidth() / 2)
					* (x - ellipse.getWidth() / 2)
					+ (y + ellipse.getHeight() / 2)
					* (y + ellipse.getHeight() / 2) <= (3 / scale)
					* (3 / scale)) {
				Point2D world = Transformation.objectToWorld(ellipse,
						-ellipse.getWidth() / 2, ellipse.getHeight() / 2);
				return new Point2D.Double(world.getX(), world.getY());
			} else if ((x + ellipse.getWidth() / 2)
					* (x + ellipse.getWidth() / 2)
					+ (y - ellipse.getHeight() / 2)
					* (y - ellipse.getHeight() / 2) <= (3 / scale)
					* (3 / scale)) {
				Point2D world = Transformation.objectToWorld(ellipse,
						ellipse.getWidth() / 2, -ellipse.getHeight() / 2);
				return new Point2D.Double(world.getX(), world.getY());
			} else if ((x - ellipse.getWidth() / 2)
					* (x - ellipse.getWidth() / 2)
					+ (y - ellipse.getHeight() / 2)
					* (y - ellipse.getHeight() / 2) <= (3 / scale)
					* (3 / scale)) {
				Point2D world = Transformation.objectToWorld(ellipse,
						-ellipse.getWidth() / 2, -ellipse.getHeight() / 2);
				return new Point2D.Double(world.getX(), world.getY());
			}
		} else if (selected instanceof Triangle) {

			Triangle triangle = (Triangle) selected;

			if ((x - triangle.getX1()) * (x - triangle.getX1())
					+ (y - triangle.getY1()) * (y - triangle.getY1()) <= (3 / scale)
					* (3 / scale)) {
				triangle.setCornerSelected(0);
				return new Point2D.Double(triangle.getX1(), triangle.getY1());
			} else if ((x - triangle.getX2()) * (x - triangle.getX2())
					+ (y - triangle.getY2()) * (y - triangle.getY2()) <= (3 / scale)
					* (3 / scale)) {
				triangle.setCornerSelected(1);
				return new Point2D.Double(triangle.getX2(), triangle.getY2());
			} else if ((x - triangle.getX3()) * (x - triangle.getX3())
					+ (y - triangle.getY3()) * (y - triangle.getY3()) <= (3 / scale)
					* (3 / scale)) {
				triangle.setCornerSelected(2);
				return new Point2D.Double(triangle.getX3(), triangle.getY3());
			}
		}

		return null;
	}

	public Shape getSelectedShape(double x, double y, int tolerance,
			double scale) {

		for (int i = shapes.size() - 1; i >= 0; i--) {

			if (shapes.get(i) instanceof Line) {

				Line line = (Line) shapes.get(i);

				double length = Math.sqrt((line.getX2() - line.getX1())
						* (line.getX2() - line.getX1())
						+ (line.getY2() - line.getY1())
						* (line.getY2() - line.getY1()));
				double dx = (line.getX2() - line.getX1()) / length;
				double dy = (line.getY2() - line.getY1()) / length;

				double dotProduct = (x - line.getX1()) * dx
						+ (y - line.getY1()) * dy;

				if (0 <= dotProduct && dotProduct <= length) {

					double projectionX = line.getX1() + dotProduct * dx;
					double projectionY = line.getY1() + dotProduct * dy;

					if ((x - projectionX) * (x - projectionX)
							+ (y - projectionY) * (y - projectionY) <= (tolerance / scale)
							* (tolerance / scale)) {
						return line;
					}
				}

				continue;
			}

			double originalX = x;
			double originalY = y;

			Point2D point = Transformation.worldToObject(shapes.get(i), x, y);
			x = point.getX();
			y = point.getY();

			if (shapes.get(i) instanceof Square) {

				Square square = (Square) shapes.get(i);

				if (Math.abs(x) <= square.getSize() / 2
						&& Math.abs(y) <= square.getSize() / 2) {
					return square;
				}
			} else if (shapes.get(i) instanceof Rectangle) {

				Rectangle rectangle = (Rectangle) shapes.get(i);

				if (Math.abs(x) <= rectangle.getWidth() / 2
						&& Math.abs(y) <= rectangle.getHeight() / 2) {
					return rectangle;

				}

			} else if (shapes.get(i) instanceof Circle) {

				Circle circle = (Circle) shapes.get(i);

				if ((x) * (x) + (y) * (y) <= circle.getRadius()
						* circle.getRadius()) {
					return circle;
				}

			} else if (shapes.get(i) instanceof Ellipse) {

				Ellipse ellipse = (Ellipse) shapes.get(i);

				if ((x)
						* (x)
						/ ((ellipse.getWidth() / 2) * (ellipse.getWidth() / 2))
						+ (y)
						* (y)
						/ ((ellipse.getHeight() / 2) * (ellipse.getHeight() / 2)) <= 1) {
					return ellipse;
				}

			} else if (shapes.get(i) instanceof Triangle) {

				Triangle triangle = (Triangle) shapes.get(i);

				double normalX = -(triangle.getY2() - triangle.getY1());
				double normalY = triangle.getX2() - triangle.getX1();
				double dotProduct1 = normalX * (x - triangle.getX1()) + normalY
						* (y - triangle.getY1());

				normalX = -(triangle.getY3() - triangle.getY2());
				normalY = triangle.getX3() - triangle.getX2();
				double dotProduct2 = normalX * (x - triangle.getX2()) + normalY
						* (y - triangle.getY2());

				normalX = -(triangle.getY1() - triangle.getY3());
				normalY = triangle.getX1() - triangle.getX3();
				double dotProduct3 = normalX * (x - triangle.getX3()) + normalY
						* (y - triangle.getY3());

				if (dotProduct1 >= 0 && dotProduct2 >= 0 && dotProduct3 >= 0) {
					return triangle;
				} else if (dotProduct1 <= 0 && dotProduct2 <= 0
						&& dotProduct3 <= 0) {
					return triangle;
				}
			}

			x = originalX;
			y = originalY;
		}

		return null;
	}
}

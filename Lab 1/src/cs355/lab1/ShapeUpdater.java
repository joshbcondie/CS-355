package cs355.lab1;

public class ShapeUpdater {

	private double x1;
	private double y1;

	public void setX1(double x1) {
		this.x1 = x1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public void updateSquare(Shape shape, double x2, double y2) {

		Square square = (Square) shape;

		if (Math.abs(x2 - x1) < Math.abs(y2 - y1)) {
			square.setSize(Math.abs(x2 - x1));
			square.setX(Math.min(x2, x1) + square.getSize() / 2);
			if (y2 < y1) {
				square.setY(y1 - square.getSize() / 2);
			} else {
				square.setY(y1 + square.getSize() / 2);
			}
		} else {
			square.setSize(Math.abs(y2 - y1));
			square.setY(Math.min(y2, y1) + square.getSize() / 2);
			if (x2 < x1) {
				square.setX(x1 - square.getSize() / 2);
			} else {
				square.setX(x1 + square.getSize() / 2);
			}
		}
	}

	public void updateRectangle(Shape shape, double x2, double y2) {

		Rectangle rectangle = (Rectangle) shape;

		if (x2 >= x1) {
			rectangle.setWidth(x2 - x1);
			rectangle.setX(x1 + rectangle.getWidth() / 2);
		} else {
			rectangle.setWidth(x1 - x2);
			rectangle.setX(x2 + rectangle.getWidth() / 2);
		}
		if (y2 >= y1) {
			rectangle.setHeight(y2 - y1);
			rectangle.setY(y1 + rectangle.getHeight() / 2);
		} else {
			rectangle.setHeight(y1 - y2);
			rectangle.setY(y2 + rectangle.getHeight() / 2);
		}
	}

	public void updateCircle(Shape shape, double x2, double y2) {

		Circle circle = (Circle) shape;

		if (Math.abs(x2 - x1) < Math.abs(y2 - y1)) {
			circle.setRadius(Math.abs(x2 - x1) / 2);
			circle.setX((Math.min(x2, x1) + circle.getRadius()));
			if (y2 < y1) {
				circle.setY(y1 - circle.getRadius());
			} else {
				circle.setY(y1 + circle.getRadius());
			}
		} else {
			circle.setRadius(Math.abs(y2 - y1) / 2);
			circle.setY((Math.min(y2, y1) + circle.getRadius()));
			if (x2 < x1) {
				circle.setX(x1 - circle.getRadius());
			} else {
				circle.setX(x1 + circle.getRadius());
			}
		}
	}

	public void updateEllipse(Shape shape, double x2, double y2) {

		Ellipse ellipse = (Ellipse) shape;

		if (x2 >= x1) {
			ellipse.setWidth(x2 - x1);
			ellipse.setX(x1 + ellipse.getWidth() / 2);
		} else {
			ellipse.setWidth(x1 - x2);
			ellipse.setX(x2 + ellipse.getWidth() / 2);
		}
		if (y2 >= y1) {
			ellipse.setHeight(y2 - y1);
			ellipse.setY(y1 + ellipse.getHeight() / 2);
		} else {
			ellipse.setHeight(y1 - y2);
			ellipse.setY(y2 + ellipse.getHeight() / 2);
		}
	}

	public void updateLine(Shape shape, double x2, double y2) {

		Line line = (Line) shape;
		line.setX2(x2);
		line.setY2(y2);
	}

	public void updateTriangle(Shape shape, double x2, double y2) {

		Triangle triangle = (Triangle) shape;

		switch (triangle.getCornerSelected()) {

		case 0:
			triangle.setX1(x2 - triangle.getX());
			triangle.setY1(y2 - triangle.getY());
			break;

		case 1:
			triangle.setX2(x2 - triangle.getX());
			triangle.setY2(y2 - triangle.getY());
			break;
		case 2:
			triangle.setX3(x2 - triangle.getX());
			triangle.setY3(y2 - triangle.getY());
			break;
		}

		int centerX = (int) Math.round(triangle.getX1() + triangle.getX2()
				+ triangle.getX3()) / 3;
		int centerY = (int) Math.round(triangle.getY1() + triangle.getY2()
				+ triangle.getY3()) / 3;

		triangle.setX1(triangle.getX1() - centerX);
		triangle.setY1(triangle.getY1() - centerY);
		triangle.setX2(triangle.getX2() - centerX);
		triangle.setY2(triangle.getY2() - centerY);
		triangle.setX3(triangle.getX3() - centerX);
		triangle.setY3(triangle.getY3() - centerY);

		triangle.setX(triangle.getX() + centerX);
		triangle.setY(triangle.getY() + centerY);

	}

	public void rotateShape(Shape shape, double x2, double y2) {
		shape.setRotation(-Math.atan2(-(x2 - shape.getX()),
				-(y2 - shape.getY())));
	}
}

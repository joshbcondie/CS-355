package cs355.lab2;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import cs355.CS355Controller;
import cs355.GUIFunctions;

public class MyController extends MouseAdapter implements CS355Controller {

	private enum Mode {
		NOTHING, TRIANGLE, SQUARE, RECTANGLE, CIRCLE, ELLIPSE, LINE, SELECT
	};

	private enum Transform {
		TRANSLATE, ROTATE, RESHAPE
	};

	private MyModel model;
	private Mode mode;
	private Transform transform;
	private Color color;
	private Shape shape;
	private double x1, y1;
	private double x2, y2;
	private int clicks;
	private ShapeUpdater updater;
	private boolean notLeftButton;
	private Viewport viewport;

	public MyController(MyModel model) {
		this.model = model;
		mode = Mode.NOTHING;
		transform = Transform.TRANSLATE;
		color = Color.WHITE;
		shape = null;
		x1 = 0;
		y1 = 0;
		clicks = 0;
		updater = new ShapeUpdater();
		notLeftButton = true;
		setViewport(new Viewport());
	}

	@Override
	public void colorButtonHit(Color c) {

		color = c;
		GUIFunctions.changeSelectedColor(c);

		if (mode == Mode.SELECT && shape != null) {
			shape.setColor(c);
			GUIFunctions.refresh();
		}
	}

	@Override
	public void triangleButtonHit() {
		if (mode != Mode.TRIANGLE) {
			shape = null;
			GUIFunctions.refresh();
			mode = Mode.TRIANGLE;
			clicks = 0;
		}
	}

	@Override
	public void squareButtonHit() {
		if (mode != Mode.SQUARE) {
			shape = null;
			GUIFunctions.refresh();
			mode = Mode.SQUARE;
		}
	}

	@Override
	public void rectangleButtonHit() {
		if (mode != Mode.RECTANGLE) {
			shape = null;
			GUIFunctions.refresh();
			mode = Mode.RECTANGLE;
		}
	}

	@Override
	public void circleButtonHit() {
		if (mode != Mode.CIRCLE) {
			shape = null;
			GUIFunctions.refresh();
			mode = Mode.CIRCLE;
		}
	}

	@Override
	public void ellipseButtonHit() {
		if (mode != Mode.ELLIPSE) {
			shape = null;
			GUIFunctions.refresh();
			mode = Mode.ELLIPSE;
		}
	}

	@Override
	public void lineButtonHit() {
		if (mode != Mode.LINE) {
			shape = null;
			GUIFunctions.refresh();
			mode = Mode.LINE;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (e.getButton() != MouseEvent.BUTTON1) {
			return;
		}

		notLeftButton = false;

		updater.setX1(e.getX());
		updater.setY1(e.getY());

		switch (mode) {

		case SQUARE:
			shape = new Square(color, e.getX(), e.getY(), 0);
			model.addShape(shape);
			break;

		case RECTANGLE:
			shape = new Rectangle(color, e.getX(), e.getY(), 0, 0);
			model.addShape(shape);
			break;

		case CIRCLE:
			shape = new Circle(color, e.getX(), e.getY(), 0);
			model.addShape(shape);
			break;

		case ELLIPSE:
			shape = new Ellipse(color, e.getX(), e.getY(), 0, 0);
			model.addShape(shape);
			break;

		case LINE:
			shape = new Line(color, e.getX(), e.getY(), e.getX(), e.getY());
			model.addShape(shape);
			break;

		case SELECT:
			x1 = e.getX();
			y1 = e.getY();
			if (shape != null) {

				Point2D fixedPoint = model.testRotationHandle(x1, y1, shape);

				if (fixedPoint != null) {
					transform = Transform.ROTATE;
					updater.setX1(fixedPoint.getX());
					updater.setY1(fixedPoint.getY());
					break;
				}

				fixedPoint = model.testHandles(x1, y1, shape);

				if (fixedPoint != null) {
					transform = Transform.RESHAPE;
					updater.setX1(fixedPoint.getX());
					updater.setY1(fixedPoint.getY());
					break;
				}
			}
			shape = model.getSelectedShape(x1, y1, 4);
			if (shape != null) {
				transform = Transform.TRANSLATE;
			}

		default:
			break;
		}

		GUIFunctions.refresh();
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if (notLeftButton)
			return;

		switch (mode) {

		case SQUARE:
			updater.updateSquare(shape, e.getX(), e.getY());
			break;

		case RECTANGLE:
			updater.updateRectangle(shape, e.getX(), e.getY());
			break;

		case CIRCLE:
			updater.updateCircle(shape, e.getX(), e.getY());
			break;

		case ELLIPSE:
			updater.updateEllipse(shape, e.getX(), e.getY());
			break;

		case LINE:
			updater.updateLine(shape, e.getX(), e.getY());
			break;

		case SELECT:
			if (shape != null) {
				switch (transform) {

				case TRANSLATE:
					if (shape instanceof Line) {
						Line line = (Line) shape;
						line.setX1(line.getX1() + e.getX() - x1);
						line.setY1(line.getY1() + e.getY() - y1);
						line.setX2(line.getX2() + e.getX() - x1);
						line.setY2(line.getY2() + e.getY() - y1);
					} else {
						shape.setX(shape.getX() + e.getX() - x1);
						shape.setY(shape.getY() + e.getY() - y1);
					}
					x1 = e.getX();
					y1 = e.getY();
					break;

				case RESHAPE:

					if (shape instanceof Square) {
						updater.updateSquare(shape, e.getX(), e.getY());
					} else if (shape instanceof Rectangle) {
						updater.updateRectangle(shape, e.getX(), e.getY());
					} else if (shape instanceof Circle) {
						updater.updateCircle(shape, e.getX(), e.getY());
					} else if (shape instanceof Ellipse) {
						updater.updateEllipse(shape, e.getX(), e.getY());
					} else if (shape instanceof Line) {
						updater.updateLine(shape, e.getX(), e.getY());
					} else if (shape instanceof Triangle) {
						updater.updateTriangle(shape, e.getX() - shape.getX(),
								e.getY() - shape.getY());
					}

					break;
				case ROTATE:
					updater.rotateShape(shape, e.getX(), e.getY());
					break;
				default:
					break;
				}
			}

		default:
			break;
		}

		GUIFunctions.refresh();
	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (e.getButton() != MouseEvent.BUTTON1)
			return;

		notLeftButton = true;

		if (mode != Mode.SELECT) {
			shape = null;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getButton() != MouseEvent.BUTTON1)
			return;

		if (mode == Mode.TRIANGLE) {
			if (clicks == 0) {
				x1 = e.getX();
				y1 = e.getY();
				clicks = 1;
			} else if (clicks == 1) {
				x2 = e.getX();
				y2 = e.getY();
				clicks = 2;
			} else if (clicks == 2) {
				double centerX = (x1 + x2 + e.getX()) / 3;
				double centerY = (y1 + y2 + e.getY()) / 3;
				shape = new Triangle(color, centerX, centerY, x1 - centerX, y1
						- centerY, x2 - centerX, y2 - centerY, e.getX()
						- centerX, e.getY() - centerY);
				model.addShape(shape);
				shape = null;
				clicks = 0;
				GUIFunctions.refresh();
			}
		}
	}

	@Override
	public void selectButtonHit() {
		if (mode != Mode.SELECT) {
			shape = null;
			mode = Mode.SELECT;
		}
	}

	@Override
	public void zoomInButtonHit() {
		viewport.zoomIn();
		GUIFunctions.refresh();
	}

	@Override
	public void zoomOutButtonHit() {
		viewport.zoomOut();
		GUIFunctions.refresh();
	}

	@Override
	public void hScrollbarChanged(int value) {
		// TODO Auto-generated method stub
	}

	@Override
	public void vScrollbarChanged(int value) {
		// TODO Auto-generated method stub
	}

	public Shape getSelected() {

		if (mode == Mode.SELECT) {
			return shape;
		}
		return null;
	}

	public Viewport getViewport() {
		return viewport;
	}

	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}

}

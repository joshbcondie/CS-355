package cs355.lab5;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.List;

public class Render3D {

	private Camera camera;
	private HouseModel house;

	public Render3D(Camera camera) {
		this.camera = camera;
		house = new HouseModel();
	}

	private Line3D objectToCamera(Line3D line) {
		return new Line3D(new Point3D(0, 0, 0), new Point3D(0, 0, 0));
	}

	private Line3D cameraToClip(Line3D line) {
		return new Line3D(new Point3D(0, 0, 0), new Point3D(0, 0, 0));
	}

	private Line2D clipToCanonical(Line3D line) {
		return new Line2D.Double();
	}

	private Line2D canonicalToScreen(Line2D line) {
		return new Line2D.Double();
	}

	public void draw(Graphics2D g2d) {

		for (Line3D line3D : house.lines) {
			Line2D line2D = canonicalToScreen(clipToCanonical(cameraToClip(objectToCamera(line3D))));
			g2d.drawLine((int) line2D.getX1(), (int) line2D.getY1(),
					(int) line2D.getX2(), (int) line2D.getY2());
		}
	}
}

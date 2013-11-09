package cs355.lab5;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.List;

public class Render3D {

	private Camera camera;
	private HouseModel house;
	private double zoomX;
	private double zoomY;
	private double near;
	private double far;

	public Render3D(Camera camera) {
		this.camera = camera;
		house = new HouseModel();
		zoomX = 1 / Math.tan(35 * Math.PI / 180);
		zoomY = zoomX * 4 / 3;
		near = 0;
		far = 500;
	}

	private static double[][] multiply(double[][] A, double[][] B) {

		double[][] result = new double[A.length][B[0].length];

		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < B[0].length; j++) {
				for (int k = 0; k < B.length; k++) {
					result[i][j] += A[i][k] * B[k][j];
				}
			}
		}

		return result;
	}

	private static double[][] point3DToHomogenous(Point3D point) {

		double[][] result = new double[4][1];

		result[0][0] = point.x;
		result[1][0] = point.y;
		result[2][0] = point.z;
		result[3][0] = 1;

		return result;
	}

	private Line3D objectToCamera(Line3D line) {

		double[][] translation = new double[4][4];

		for (int i = 0; i < 4; i++) {
			translation[i][i] = 1;
		}

		translation[0][3] = -camera.getX();
		translation[1][3] = -camera.getY();
		translation[2][3] = -camera.getZ();

		double[][] rotation = new double[4][4];

		rotation[0][0] = Math.cos(camera.getRotation() * Math.PI / 180);
		rotation[0][1] = 0;
		rotation[0][2] = -Math.sin(camera.getRotation() * Math.PI / 180);

		rotation[1][0] = 0;
		rotation[1][1] = 1;
		rotation[1][2] = 0;

		rotation[2][0] = -Math.sin(camera.getRotation() * Math.PI / 180);
		rotation[2][1] = 0;
		rotation[2][2] = -Math.cos(camera.getRotation() * Math.PI / 180);

		rotation[3][3] = 1;

		double[][] combined = multiply(rotation, translation);

		double[][] start = multiply(combined, point3DToHomogenous(line.start));
		System.out.println("Original: "
				+ Arrays.deepToString(point3DToHomogenous(line.start)));
		System.out.println("Camera: " + Arrays.deepToString(start));
		double[][] end = multiply(combined, point3DToHomogenous(line.end));

		return new Line3D(new Point3D(start[0][0], start[1][0], start[2][0]),
				new Point3D(end[0][0], end[1][0], end[2][0]));
	}

	private double[][][] cameraToClip(Line3D line) {

		double[][] clip = new double[4][4];

		clip[0][0] = zoomX;
		clip[1][1] = zoomY;
		clip[2][2] = (far + near) / (far - near);
		clip[3][2] = (2 * near * far) / (far - near);
		clip[3][3] = 1;

		double[][] start = multiply(clip, point3DToHomogenous(line.start));
		System.out.println("Camera: "
				+ Arrays.deepToString(point3DToHomogenous(line.start)));
		System.out.println("Clip: " + Arrays.deepToString(start));
		double[][] end = multiply(clip, point3DToHomogenous(line.end));

		return new double[][][] { start, end };
		// return new Line3D(new Point3D(start[0][0], start[1][0], start[2][0]),
		// new Point3D(end[0][0], end[1][0], end[2][0]));
	}

	private Line2D clipToCanonical(double[][][] line) {

		return new Line2D.Double(new Point2D.Double(line[0][0][0]
				/ line[0][3][0] / line[0][2][0], line[0][1][0] / line[0][3][0]
				/ line[0][2][0]), new Point2D.Double(line[1][0][0]
				/ line[1][3][0] / line[1][2][0], line[1][1][0] / line[1][3][0]
				/ line[1][2][0]));
	}

	private Line2D canonicalToScreen(Line2D line) {

		System.out.println("Canonical: " + line.getX1() + " " + line.getY1());

		double[][] translate = new double[3][3];
		translate[0][0] = 1;
		translate[1][1] = 1;
		translate[2][2] = 1;
		translate[0][2] = 1;
		translate[1][1] = 1;

		double[][] scale = new double[3][3];
		scale[0][0] = 256;
		scale[1][1] = 256;
		scale[2][2] = 1;

		double[][] combined = multiply(scale, translate);

		double[][] start = multiply(combined, new double[][] {
				{ line.getX1() }, { line.getY1() }, { 1 } });

		System.out.println("Screen: " + Arrays.deepToString(start));

		double[][] end = multiply(combined, new double[][] { { line.getX2() },
				{ line.getY2() }, { 1 } });

		return new Line2D.Double(new Point2D.Double(start[0][0], start[1][0]),
				new Point2D.Double(end[0][0], end[1][0]));
	}

	private boolean isInFrustum(double[][][] line) {

//		if (line[0][0][0] < -line[0][3][0] && line[1][0][0] < -line[1][3][0])
//			return false;
//		if (line[0][0][0] > line[0][3][0] && line[1][0][0] > line[1][3][0])
//			return false;
//		if (line[0][1][0] < -line[0][3][0] && line[1][1][0] < -line[1][3][0])
//			return false;
//		if (line[0][1][0] > line[0][3][0] && line[1][1][0] > line[1][3][0])
//			return false;
//		if (line[0][2][0] < -line[0][3][0] && line[1][2][0] < -line[1][3][0])
//			return false;
//		if (line[0][2][0] > line[0][3][0] && line[1][2][0] > line[1][3][0])
//			return false;

		return true;
	}

	public void draw(Graphics2D g2d) {

		for (Line3D line3D : house.lines) {
			double[][][] clipLine = cameraToClip(objectToCamera(line3D));
			if (!isInFrustum(clipLine))
				continue;
			Line2D line2D = canonicalToScreen(clipToCanonical(clipLine));
			g2d.drawLine((int) line2D.getX1(), (int) line2D.getY1(),
					(int) line2D.getX2(), (int) line2D.getY2());
		}
	}
}

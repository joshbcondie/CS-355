package cs355.lab5;

public class Camera {

	private float x;
	private float y;
	private float z;
	private float rotation;

	public Camera() {
		x = 0;
		y = 0;
		z = 30;
		rotation = 0;
	}

	public float getX() {
		return x;
	}

	public void moveLeft() {
		x -= Math.cos(rotation * Math.PI / 180);
		z += Math.sin(rotation * Math.PI / 180);
	}

	public void moveRight() {
		x += Math.cos(rotation * Math.PI / 180);
		z -= Math.sin(rotation * Math.PI / 180);
	}

	public float getY() {
		return y;
	}

	public void moveDown() {
		y--;
	}

	public void moveUp() {
		y++;
	}

	public float getZ() {
		return z;
	}

	public void moveBackward() {
		z += Math.cos(rotation * Math.PI / 180);
		x += Math.sin(rotation * Math.PI / 180);
	}

	public void moveForward() {
		z -= Math.cos(rotation * Math.PI / 180);
		x -= Math.sin(rotation * Math.PI / 180);
	}

	public float getRotation() {
		return rotation;
	}

	public void turnLeft() {
		rotation++;
	}

	public void turnRight() {
		rotation--;
	}
}

package cs355.lab6;

import java.awt.image.BufferedImage;

public class ImageModel {

	private int width;
	private int height;
	private int[][] pixels;
	private int[][] temp;

	public void doEdgeDetection() {
		// TODO Auto-generated method stub
	}

	public void doSharpen() {
		applyKernel(new int[][] { { 0, -1, 0 }, { -1, 6, -1 }, { 0, -1, 0 } },
				2);
	}

	public void doMedianBlur() {
		// TODO Auto-generated method stub
	}

	public void doUniformBlur() {
		applyKernel(new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } }, 9);
	}

	public void doChangeContrast(int contrastAmountNum) {

		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {

				pixels[i][j] = (int) Math
						.round(((contrastAmountNum + 100.0) / 100.0)
								* ((contrastAmountNum + 100.0) / 100.0)
								* ((contrastAmountNum + 100.0) / 100.0)
								* ((contrastAmountNum + 100.0) / 100.0)
								* (pixels[i][j] - 128) + 128);

				if (pixels[i][j] < 0)
					pixels[i][j] = 0;
				else if (pixels[i][j] > 255)
					pixels[i][j] = 255;
			}
		}
	}

	public void doChangeBrightness(int brightnessAmountNum) {

		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {

				pixels[i][j] = pixels[i][j] + brightnessAmountNum;

				if (pixels[i][j] < 0)
					pixels[i][j] = 0;
				else if (pixels[i][j] > 255)
					pixels[i][j] = 255;
			}
		}
	}

	public void doLoadImage(BufferedImage openImage) {

		width = openImage.getWidth();
		height = openImage.getHeight();
		pixels = new int[height][width];
		temp = new int[height][width];

		for (int i = 0; i < openImage.getHeight(); i++) {
			for (int j = 0; j < openImage.getWidth(); j++) {
				pixels[i][j] = openImage.getRGB(j, i) & 255;
			}
		}
	}

	public void applyKernel(int[][] kernel, double dividend) {

		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {

				int average = 0;

				for (int k = -kernel.length / 2; k <= kernel.length / 2; k++) {
					for (int l = -kernel[0].length / 2; l <= kernel[0].length / 2; l++) {

						if (i + k < 0 || i + k >= pixels.length)
							continue;
						if (j + l < 0 || j + l >= pixels[i].length)
							continue;

						average += pixels[i + k][j + l]
								* kernel[k + kernel.length / 2][l
										+ kernel[0].length / 2];
					}
				}

				temp[i][j] = (int) Math.round(average / dividend);
			}
		}

		for (int i = 0; i < pixels.length; i++) {
			for (int j = 0; j < pixels[i].length; j++) {
				pixels[i][j] = temp[i][j];
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[][] getPixels() {
		return pixels;
	}
}

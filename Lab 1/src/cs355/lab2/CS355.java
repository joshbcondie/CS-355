/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cs355.lab2;

import cs355.GUIFunctions;
import java.awt.Color;

/**
 * 
 * @author <Put your name here>
 */
public class CS355 {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {

		MyModel model = new MyModel();
		MyController controller = new MyController(model);
		GUIFunctions.createCS355Frame(controller, new MyViewRefresher(model,
				controller), controller, controller);
		GUIFunctions.refresh();
		GUIFunctions.changeSelectedColor(Color.WHITE);
		GUIFunctions.setHScrollBarMin(0);
		GUIFunctions.setVScrollBarMin(0);
		GUIFunctions.setHScrollBarMax(2048);
		GUIFunctions.setVScrollBarMax(2048);
		GUIFunctions.setHScrollBarKnob(512);
		GUIFunctions.setVScrollBarKnob(512);
	}
}
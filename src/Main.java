
import javax.swing.JApplet;

import controller.Controller;

/**
 * <h2>Main</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		22/04/2018
 * @version 1.0.0
 */
@SuppressWarnings("deprecation")
public class Main extends JApplet {
  
  
  
  /* (non-Javadoc)
   * @see java.applet.Applet#start()
   */
  @Override
  public void start() {
    // TODO Auto-generated method stub
    super.start();
    new Controller(50);
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    if (args.length == 1) {
      new Controller(Integer.parseInt(args[0]));      
    } else {
      System.err.println("The argument of the progrma must be the number of points");
    }
  }
}

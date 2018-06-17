import controller.Controller;

/**
 * <h2>Main</h2>
 * 
 * @author	Cristian Abrante Dorta
 * @company	University Of La Laguna
 * @date 		22/04/2018
 * @version 1.0.0
 */
public class Main {

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

/**
 * A main class to start the application
 *
 * @author Marina Ionel
 * @version 1.0.0
 */
public class Main {
    /**
     * The main method for starting the program
     *
     * @param args supplied command-line arguments
     */
    public static void main(String[] args) {
        //initialize a lorawan client
        new LoRaClient();
        //an infinite loop to keep the application running
        for (; ; ) ;
    }
}

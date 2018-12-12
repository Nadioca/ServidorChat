
package servidorchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorChat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        final int PORT = 40080;
        
        try {
            
            ServerSocket sk = new ServerSocket(PORT);
            
            while(true){
                Socket socket = sk.accept();
                System.out.println("Algioen se conectó");
                Hilo hilo = new Hilo(socket);
                hilo.start();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorChat.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

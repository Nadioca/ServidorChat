
package servidorchat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Hilo extends Thread {
    
    Socket sk;

    public Hilo( Socket sk ) {
        
        this.sk = sk;
        
    }

    @Override
    public void run() {
        
        InputStream is = null;
        OutputStream os = null;
        try {
            is = sk.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            
            os = sk.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            
            Inet4Address ip = (Inet4Address) sk.getInetAddress();
            String tuIP = ip.getHostAddress();
            String linea;
            while(true){
                linea = br.readLine();
                System.out.println( tuIP + ": " + linea);
                
                bw.write("Ok. Esperando");
                bw.newLine();
                bw.flush();
            }     
            
        } catch (IOException ex) {
            Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            if(is != null) try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
    }
    
    
    
}

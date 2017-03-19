package ejemplo.servidor.ssl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author Manu
 */
public class EjemploServidorSSL {

    public static void main(String[] args) throws IOException {
        
        System.setProperty("javax.net.ssl.keyStore", "src/keychat");
        System.setProperty("javax.net.ssl.keyStorePassword", "manuel");
        System.setProperty("javax.net.ssl.trustStore", "src/keychat");
        System.setProperty("javax.net.ssl.trustStorePassword", "manuel");
        SSLServerSocketFactory serverFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        SSLServerSocket server = (SSLServerSocket)serverFactory.createServerSocket();
        InetSocketAddress addr = new InetSocketAddress("localhost", 5556);
        server.bind(addr);
        SSLSocket socket = (SSLSocket)server.accept();
        
        InputStream recepcion = socket.getInputStream();
        OutputStream envio = socket.getOutputStream();
        
        int tamano = recepcion.read();
        byte [] mensaje = new byte[tamano];
        recepcion.read(mensaje);
        String mensajeRecibido = new String (mensaje);
        System.out.println("Mensaje recibido: " + mensajeRecibido);
        recepcion.close();
        
    }
    
}

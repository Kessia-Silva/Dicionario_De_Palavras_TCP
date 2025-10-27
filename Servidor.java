import java.net.ServerSocket;
import java.net.Socket;

public class Servidor{
    private static final int PORT = 12345;
    public static void main(String[] args) {

        try {
            ServerSocket servidor = new ServerSocket(PORT);
            System.out.println("[Servidor] ouvindo na porta " + PORT + " ....");
            
            while(true){
                Socket socket = servidor.accept();
                System.out.println("Cleinte conectado: " +socket.getInetAddress().getHostAddress());
                new Thread(new Dicionario(socket)).start();
            }

        } catch (Exception e) {
        }
    }
}



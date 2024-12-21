package SingleThreadServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public void run() throws IOException{
        int port = 8010;
        try {
            ServerSocket Serversocket = new ServerSocket(port);
            Serversocket.setSoTimeout(15000);
            while(true){
                System.err.println("server is listenign on port : "+port);
                Socket acceptedConnection = Serversocket.accept();
                System.err.println("connnection accepted from client :"+ acceptedConnection.getRemoteSocketAddress());
                PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));

                toClient.println("hello from the server  :)");
                toClient.close();;
                fromClient.close();;
                acceptedConnection.close();

            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {

          Server server = new Server();
          try{
            server.run();
          }catch(Exception e){
            e.printStackTrace();
          }
    }
}
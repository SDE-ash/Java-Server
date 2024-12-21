package MultiThreadServer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class MThreadServer {
    public Consumer<Socket> getCounser(){
        return (clientSocket)->{
            try{
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                toClient.println("hello from server....");
                toClient.close();
                clientSocket.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        };
    }   

    public static void main(String[] args) {
        final int port = 8010;
        MThreadServer server = new MThreadServer();
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(20000);
            System.err.println("server is listening at port : "+port);
            while(true){
                Socket acceptedSocket = serverSocket.accept();
                Thread thread = new Thread(()->server.getCounser().accept(acceptedSocket));
                thread.start();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

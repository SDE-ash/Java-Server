package MultiThreadServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MultiClient {
    public Runnable getRunnable(){
        return new Runnable() {
            @Override
            public void run() {
                int port = 8010;
                try{
                    InetAddress address = InetAddress.getByName("localhost");
                    Socket socket =  new Socket(address,port);
                    try{
                        PrintWriter toSocket = new PrintWriter(socket.getOutputStream());
                        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        toSocket.println("hii from client");

                        String line = fromSocket.readLine();

                        System.err.println("Response from socket : "+line);

                        toSocket.close();
                        fromSocket.close();
                        socket.close();
                    }catch(IOException i){
                        i.printStackTrace();
                    }
                }catch(IOException io){
                    io.printStackTrace();
                }
            }
        };
    }
    public static void main(String[] args) {
        MultiClient client = new MultiClient();
        for(int i=0;i<100;i++){
            try{
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}

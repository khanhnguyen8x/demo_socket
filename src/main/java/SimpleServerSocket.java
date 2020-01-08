import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServerSocket {

    public void startServer(int port) {
        ServerSocket listener = null;
        Socket socketOfServer = null;

        try {
            // create ServerSocket at port
            listener = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        System.out.println("Server is running....");

        try {
            // programing loops run
            while (true) {
                System.out.println("Server is waiting client....");
                //Listens for a connection to be made to this socket.
                // The method blocks until a connection is made.
                socketOfServer = listener.accept();
                System.out.println("Accept a client request >>>>>>>>>>>");

                // create inputStream from socket
                BufferedReader is = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));

                // Because we create a new thread to process each connection then
                // we can handle multiple request from clients
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // read data request to server from client
                        String line = null;
                        try {
                            while ((line = is.readLine()) != null) {
                                System.out.println("request data: " + line);
                            }
                            is.close();
                            System.out.println("Close a client request >>>>>>>>>>>");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.println("Server stopped!");
    }
}

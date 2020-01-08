public class DemoServerSocket {


    public static void main(String[] args) {
        final int port = 3000;

        SimpleServerSocket serverSocket = new SimpleServerSocket();
        serverSocket.startServer(port);
    }
}

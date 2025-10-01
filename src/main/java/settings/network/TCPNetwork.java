package settings.network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPNetwork {
    int port;
    ServerSocket serverSocket;
    Socket client;

    BufferedReader in;
    BufferedWriter out;

    public TCPNetwork(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(this.port);
    }

    public String getRequest() throws IOException {
        client = serverSocket.accept();
        System.out.println("accept from: " + client.getRemoteSocketAddress());

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = in.readLine()) != null) {
            if (line.equals("end"))
                break;
            sb.append(line).append('\n');
        }

        System.out.println("request:");
        System.out.println(sb);
        return sb.toString();
    }

    public void sendResponse(String res) throws IOException {
        out.write(res);
        out.flush();

        System.out.println("response:");
        System.out.println(res);
        System.out.println("--------------------");
        client.close();
    }
}

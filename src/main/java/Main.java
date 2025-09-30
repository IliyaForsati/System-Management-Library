import com.fasterxml.jackson.databind.ObjectMapper;
import controller.MainController;
import model.ResultDTO;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main{
	public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);

        System.out.println("java listen on port: " + port);

        ObjectMapper mapper = new ObjectMapper();
        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("accept from: " + client.getRemoteSocketAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = in.readLine()) != null)
                sb.append(line).append('\n');

            ResultDTO res = MainController.run(sb.toString());
            String jsonResponse = mapper.writeValueAsString(res);
            out.write(jsonResponse);
            out.flush();

            System.out.println(sb);
            System.out.println("----------"+res.isSuccessful()+"----------");
            client.close();
        }
	}
}

package API;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class APIservice {

    public static String sendLoginRequest(String username, String password){

        try{

            Socket connection = new Socket("127.0.0.1",9999);

            String data ="API:LOGIN:" + username + "," + password;

            return Request(connection, data);

        }catch (Exception e){
            System.out.println("Exception happened during making auth request");
            return "Error: " + e.getMessage();
        }

    }

    public static String sendRegisterRequest(String name, String last_name, String username, String password, String phone_number){

        try {
            Socket connection = new Socket("127.0.0.1", 9999);

            String data = "API:REGISTER:" + "%s,%s,%s,%s,%s".formatted(name, last_name, username, password, phone_number);

            return Request(connection, data);

        }catch(IOException e){
            System.out.println("Error while registrering: " + e.getMessage());
            return "Error: " + e.getMessage();
        }

    }

    private static String Request(Socket connection, String data) throws IOException {
        DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
        writer.writeUTF(data);
        writer.flush();

        DataInputStream reader = new DataInputStream(connection.getInputStream());
        String response = reader.readUTF();

        reader.close();
        writer.close();
        connection.close();

        return response;
    }


}

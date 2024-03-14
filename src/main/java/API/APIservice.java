package API;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;

public class APIservice {

    private static final int PORT = 9999;
    private static final String EXTERNAL_SERVER_IP = "178.134.118.65";
    private static final String INTERNAL_SERVER_IP = "192.168.0.100";

    private static Socket connection;

    private static DataOutputStream writer = null;
    private static DataInputStream reader = null;

    public static Socket getConnection(){
        return connection;
    }

    public static void retryConnection(){
        if(connection == null){
            Connect();
        }
    }

    public static void Connect(){

        try {

            URL url = new URL("https://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String externalIp = in.readLine();
            String internalIp = InetAddress.getLocalHost().getHostAddress();
            System.out.println("external: " + externalIp);
            System.out.println("internal: " + internalIp);

            in.close();

            String serverIp;

            if(!externalIp.equals(EXTERNAL_SERVER_IP)){
                serverIp = EXTERNAL_SERVER_IP;
            }
            else if(!internalIp.equals(INTERNAL_SERVER_IP)){
                serverIp = INTERNAL_SERVER_IP;
            }
            else{
                serverIp = "127.0.0.1";
            }

            connection = new Socket(serverIp,PORT);

            System.out.println("Connected with IP: " + connection.getLocalAddress().getHostAddress());

            writer = new DataOutputStream(connection.getOutputStream());
            reader = new DataInputStream(connection.getInputStream());
            System.out.println("conn" + connection.toString() + " DOS " + writer.toString() + " DIS " + reader.toString());

        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String sendLoginRequest(String username, String password){
            retryConnection();
        try{
            System.out.println("conn" + connection.toString() + " DOS " + writer.toString() + " DIS " + reader.toString());
            String data ="API:LOGIN:" + username + "," + password;

            return Request(data);

        }catch (Exception e){
            System.out.println("Exception happened during making auth request");
            return "Error: " + e.getMessage();
        }

    }

    public static String sendRegisterRequest(String name, String last_name, String username, String password, String phone_number){

        try {

            retryConnection();

            String data = "API:REGISTER:" + "%s,%s,%s,%s,%s".formatted(name, last_name, username, password, phone_number);

            return Request(data);

        }catch(IOException e){
            System.out.println("Error while registering: " + e.getMessage());
            return "Error: " + e.getMessage();
        }

    }

    private static String Request(String data) throws IOException {

        if(connection == null){
            retryConnection();
        }

        System.out.println("before sending");
        writer.writeUTF(data);
        writer.flush();
        System.out.println("sent");


        return reader.readUTF();
    }

    public static void closeSocket(){
        try {
            // writer.close();
            // reader.close();
            if(!(connection == null || connection.isClosed()))
                connection.close();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


}

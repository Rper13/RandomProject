package API;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.Properties;

public class APIservice {

    private static final int PORT = 9999;

    private static final String KEY = "brr";

    private static Socket connection;

    private static DataOutputStream writer = null;
    private static DataInputStream reader = null;

    public static Socket getConnection() {
        return connection;
    }

    public static void retryConnection() {
        if (connection == null) {
            Connect();
        }
    }

    public static void Connect() {

        Properties properties = new Properties();

        try (InputStream input = APIservice.class.getResourceAsStream("/config.properties")){

            properties.load(input);

            String EXTERNAL_SERVER_IP = encrypt(KEY, properties.getProperty("external_server_ip"), false);

            String INTERNAL_SERVER_IP = encrypt(KEY, properties.getProperty("internal_server_ip"), false);

            URL url = new URL("https://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String externalIp = in.readLine();
            String internalIp = InetAddress.getLocalHost().getHostAddress();

            in.close();

            String serverIp;

            if (!externalIp.equals(EXTERNAL_SERVER_IP)) {
                serverIp = EXTERNAL_SERVER_IP;
            } else if (!internalIp.equals(INTERNAL_SERVER_IP)) {
                serverIp = INTERNAL_SERVER_IP;
            } else {
                serverIp = "127.0.0.1";
            }

            connection = new Socket(serverIp, PORT);

            System.out.println("Connected with IP: " + connection.getLocalAddress().getHostAddress());

            writer = new DataOutputStream(connection.getOutputStream());
            reader = new DataInputStream(connection.getInputStream());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String sendLoginRequest(String username, String password) {
        retryConnection();
        try {
            String data = "API:LOGIN:" + username + "," + password;

            return Request(data);

        } catch (Exception e) {
            System.out.println("Exception happened during making auth request");
            return "Error: " + e.getMessage();
        }

    }

    public static String sendRegisterRequest(String name, String last_name, String username, String password, String phone_number) {

        try {

            retryConnection();

            String data = "API:REGISTER:" + "%s,%s,%s,%s,%s".formatted(name, last_name, username, password, phone_number);

            return Request(data);

        } catch (IOException e) {
            System.out.println("Error while registering: " + e.getMessage());
            return "Error: " + e.getMessage();
        }

    }

    private static String Request(String data) throws IOException {

        if (connection == null) {
            retryConnection();
        }

        writer.writeUTF(data);
        writer.flush();

        return reader.readUTF();
    }

    public static void closeSocket() {
        try {

            if (!(connection == null || connection.isClosed()))
                connection.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String encrypt(String key, String data, boolean b) {

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(key);
        if (b) {
            return encryptor.encrypt(data);
        }
        return encryptor.decrypt(data);

    }
}

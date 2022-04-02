import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPServer {

    public static void main(String argv[]) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nPlease Enter a Webserver address: www.");
        String website = scan.next();
        System.out.println("Waiting for client to connect...");
        
        try (ServerSocket serverSocket = new ServerSocket(6789);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));) {
            String fromServer;
            long T = 0;
            long N = 0;
            long W = 0;
            out.println("www." + website);
            while ((fromServer = in.readLine()) != null) {
                System.out.println(fromServer);
                if (fromServer.startsWith("T:")) {
                    T = Integer.parseInt(fromServer.substring(3));
                    break;
                }
                if (fromServer.startsWith("N:")) {
                    N = Integer.parseInt(fromServer.substring(3));
                }
            

            }
            scan.close();
             
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

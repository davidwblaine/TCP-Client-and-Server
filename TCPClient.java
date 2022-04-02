import java.io.*;
import java.net.*;
import java.util.Calendar;

class TCPClient {

        public static void main(String argv[]) throws Exception {
                try (Socket socket = new Socket("localhost", 6789);
                                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                                BufferedReader in = new BufferedReader(
                                                new InputStreamReader(socket.getInputStream()));) {
                        String website = in.readLine();
                        System.out.println("Web server: " + website);
                        website = "https://" + website;
                        URL obj = new URL(website);
                        InetAddress ip = InetAddress.getByName(obj.getHost());
                        System.out.println("IP Address: " + ip);
                        Calendar startTime = Calendar.getInstance();
                        String inputLine;
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                        con.setRequestMethod("GET");
                        BufferedReader inpage = new BufferedReader(new InputStreamReader(con.getInputStream()));
                        Calendar endTime = Calendar.getInstance();
                        out.println("Response code: " + con.getResponseCode());
                        out.println(con.getResponseMessage() + "\n");
                        long nBytes = 0;
                        while ((inputLine = inpage.readLine()) != null) {
                                out.println(inputLine);
                                nBytes += inputLine.getBytes().length;
                        }
                        out.println("N:" + nBytes);
                        out.println("T:" + Long.toString(endTime.getTimeInMillis() - startTime.getTimeInMillis()));
                } catch (Exception e) {
                        System.out.println(e.toString());
                }
        }
}
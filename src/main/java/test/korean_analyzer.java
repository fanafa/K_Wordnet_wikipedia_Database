package main.java.test;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class korean_analyzer {
    private Socket soc;

    public String getResult(String input) throws Exception {
        StringBuffer sb = new StringBuffer();

        InetAddress ia = null;

        String serverIp = "143.248.135.20";

        try {
            ia = InetAddress.getByName(serverIp);
            soc = new Socket(ia, 44417);

            OutputStream os = soc.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);

            bos.write((input).getBytes());
            bos.flush();
            soc.shutdownOutput();

            InputStream is = soc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String line = null;
            while (true) {
                line = br.readLine();
                if (line == null)
                    break;
                line = line.trim();
                if (line.equals(""))
                    continue;

                sb.append(line);
//				sb.append("\n");
            }
            bos.close();
            br.close();

        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println(input.length());
            throw e;
        }

        return sb.toString();
    }
    public static void main(String[] args) throws Exception {

        Text_Parser tp = new Text_Parser();
        tp.getText("/Users/Sung9/Documents/Korean_Wordnet/etri_input", "/Users/Sung9/Documents/Korean_Wordnet/etri_input_WSD_moduler/etri_input");

    }

}
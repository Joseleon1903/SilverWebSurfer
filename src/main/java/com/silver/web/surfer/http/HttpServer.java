package com.silver.web.surfer.http;

import com.silver.web.surfer.http.config.Configuration;
import com.silver.web.surfer.http.config.ConfigurationManager;
import com.silver.web.surfer.http.util.LoadingFileUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {

    public static void main(String[] args){

        System.out.println("-------- Starting server ----------");

        System.out.println("-------- loading configuration ----------");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");

        Configuration config = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("port: "+ config.getPort());
        System.out.println("webroot: "+ config.getWebroot());

        System.out.println("-- enable listening TCP connection ");

        try {


            ServerSocket svrSocket = new ServerSocket(config.getPort());
            Socket socket = svrSocket.accept();

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            String html = LoadingFileUtils.loadHtml("src/main/resources/tmp/webPages/index.html");

            final String CRLF = "\n\r";  //  13 10

            String response = "HTTP/1.1 200 OK" + CRLF // status line : HTTP_VERSION response code, response message
                    + "Content-Length: " + html.getBytes().length+ CRLF // HEADER
                    + CRLF + html + CRLF
                    + CRLF;
            // todo read


            // todo writing
            outputStream.write(response.getBytes());


            inputStream.close();
            outputStream.close();
            socket.close();
            svrSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}

package com.silver.web.surfer.http;

import com.silver.web.surfer.http.config.Configuration;
import com.silver.web.surfer.http.config.ConfigurationManager;

public class HttpServer {

    public static void main(String[] args){

        System.out.println("-------- Starting server ----------");

        System.out.println("-------- loading configuration ----------");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");

        Configuration config = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("port: "+ config.getPort());
        System.out.println("webroot: "+ config.getWebroot());

    }


}

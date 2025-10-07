package com.silver.web.surfer.http.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.silver.web.surfer.http.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigurationManager {

    private static ConfigurationManager configurationManager;

    public static Configuration currentConfiguration;

    private  ConfigurationManager(){

    }

    public static ConfigurationManager getInstance(){
        if(configurationManager == null){
            configurationManager = new ConfigurationManager();
        }
        return configurationManager;
    }

    public void loadConfigurationFile(String filePath) {
        System.out.println("Entering loadConfigurationFile...");
        FileReader flr = null;
        try {
            flr = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer strBuffer = new StringBuffer();
        int i;
        while(true) {
            try {
                if ((i = flr.read()) == -1) break;
            } catch (IOException e) {
                throw new HttpConfigurationException(e);
            }
            strBuffer.append((char) i);
        }
        JsonNode config = null;
        try {
            config = Json.parse(strBuffer.toString());
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing de configuration file",e);
        }
        try {
            currentConfiguration = (Configuration) Json.fromJson(config, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new HttpConfigurationException("Error parsing de configuration file internal", e);
        }
    }


    public Configuration getCurrentConfiguration(){
        if(currentConfiguration == null){
            throw new HttpConfigurationException("Not current configuration set.");
        }
        return currentConfiguration;
    }

}

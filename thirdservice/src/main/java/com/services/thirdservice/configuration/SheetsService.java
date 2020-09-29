package com.services.thirdservice.configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;

@Configuration
public class SheetsService {

    private static final String APPLICATION_NAME = "ThirdService";
    
    @Bean
    public Sheets sheets(GoogleCredential googleCredential) throws IOException, GeneralSecurityException {
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), googleCredential).setApplicationName(APPLICATION_NAME).build();
    }
}

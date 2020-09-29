package com.services.secondservice.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.SheetsScopes;

@Deprecated
@Configuration
public class GoogleAuth {
    
    @Bean
    public NetHttpTransport netHttpTransport() throws GeneralSecurityException, IOException {
        return GoogleNetHttpTransport.newTrustedTransport();
    }
    
    @Bean
    public JacksonFactory jacksonFactory() {
        return JacksonFactory.getDefaultInstance();
    }
    
    @Bean
    public Set<String> googleOAuth2Scopes() {
        Set<String> googleOAuth2Scopes = new HashSet<>();
        googleOAuth2Scopes.add(SheetsScopes.SPREADSHEETS);
        return Collections.unmodifiableSet(googleOAuth2Scopes);
    }
    
    @Bean
    public GoogleCredential googleCredential() throws IOException {
        InputStream in = new ClassPathResource("services-289312-b5f335960dbe.json").getInputStream();
        return GoogleCredential.fromStream(in)
                .createScoped(googleOAuth2Scopes());
    }
}

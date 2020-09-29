package com.services.thirdservice.db.repositories.user;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.services.thirdservice.db.entity.user.User;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class UserRepository {
    
    @Value("${google.spreadsheet.id}")
    private String spreadsheetId;
    
    @Autowired
    private Sheets sheets;
    
    public String create(User user) throws IOException {
        String range = "test!A1:C1";
        ValueRange body = new ValueRange()
                .setValues(Arrays.asList(
                        Arrays.asList(user.getFirstName())));
        AppendValuesResponse response = sheets.spreadsheets().values().append(spreadsheetId, range, body)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("OVERWRITE")
                .setIncludeValuesInResponse(true)
                .execute();
//////////////////DO NOT TOUCH, magic//////////////////////////////
        String ans = response.getTableRange();
        String newans;
        log.info("Before: {}", ans);
        if (ans == null) {
            newans = "0";
        } else if (ans == "test!A1") {
            newans = "1";
        } else {
            newans = ans.substring(9);
        }
        log.info("Ans: {}", newans);
        return newans;
    }
}

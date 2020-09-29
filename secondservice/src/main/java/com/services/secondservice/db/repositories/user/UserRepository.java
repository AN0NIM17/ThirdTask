package com.services.secondservice.db.repositories.user;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.services.secondservice.db.entity.user.User;

@Repository
public class UserRepository {

    @Value("${google.spreadsheet.id}")
    private String spreadsheetId;

    @Autowired
    private Sheets sheets;

    public void create(User user) throws IOException {
        String range = "test!B" + user.getId();
        ValueRange body = new ValueRange()
                .setValues(Arrays.asList(
                        Arrays.asList(user.getMiddleName())));
        AppendValuesResponse response = sheets.spreadsheets().values().append(spreadsheetId, range, body)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("OVERWRITE")
                .setIncludeValuesInResponse(true)
                .execute();
    }
}

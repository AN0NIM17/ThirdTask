package com.services.firstservice.db.repositories.user;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.ClearValuesResponse;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.services.firstservice.db.entity.user.User;

@Repository
public class UserRepository {

    @Value("${google.spreadsheet.id}")
    private String spreadsheetId;

    @Autowired
    private Sheets sheets;

    public ValueRange get(Integer id) throws IOException {
        String range = "A" + id + ":C" + id;
        ValueRange result = sheets.spreadsheets().values().get(spreadsheetId, range)
                .setValueRenderOption("FORMATTED_VALUE").setMajorDimension("ROWS").execute();
        return result;
    }

    public void create(User user) throws IOException {
        String range = "test!C" + user.getId();
        ValueRange body = new ValueRange()
                .setValues(Arrays.asList(
                        Arrays.asList(user.getLastName())));
        AppendValuesResponse response = sheets.spreadsheets().values().append(spreadsheetId, range, body)
                .setValueInputOption("USER_ENTERED")
                .setInsertDataOption("OVERWRITE")
                .setIncludeValuesInResponse(true)
                .execute();
    }

    public void update(User user) throws IOException, GeneralSecurityException {
        String range = "A" + user.getId() + ":C" + user.getId();
        ValueRange body = new ValueRange()
                .setValues(Arrays.asList(
                        Arrays.asList(user)));
        UpdateValuesResponse result = sheets.spreadsheets().values()
                .update(spreadsheetId, range, body)
                .setValueInputOption("RAW")
                .execute();
    }

    public void delete(Integer id) throws IOException {
        String range = "A" + id + ":C" + id;
        ClearValuesResponse response = sheets.spreadsheets().values().clear(spreadsheetId, range, null).execute();
    }
    
    public void createNewSpreadsheet() throws IOException {
        Spreadsheet requestBody = new Spreadsheet().setProperties(new SpreadsheetProperties().setTitle("New Sheet"));
        Spreadsheet responce = sheets.spreadsheets().create(requestBody).execute();
        System.out.println(responce);
        System.out.println(requestBody.getProperties());
    }
}

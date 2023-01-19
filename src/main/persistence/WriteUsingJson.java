package persistence;


import model.UserInfo;

import org.json.JSONObject;

import java.io.*;

// REFERENCE:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Writes data to myJSON.json file and saves data from the program into the file
public class WriteUsingJson {
    private static final int WHITE_SPACE = 4;  // whitespace
    private PrintWriter printWriter;           // a PrintWriter object
    private String location;                   // location

    // EFFECTS: constructs writer to write to destination file
    public WriteUsingJson(String destination) {
        this.location = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        printWriter = new PrintWriter(new File(location));
    }


    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(UserInfo userInfo) {
        JSONObject json = userInfo.toJson();
        saveToFile(json.toString(WHITE_SPACE));
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        printWriter.print(json);
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        printWriter.close();
    }
}
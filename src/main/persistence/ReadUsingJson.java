package persistence;


import model.User;
import model.UserInfo;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// REFERENCE:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Reads the data present in myJSON.json file and loads it into the program
public class ReadUsingJson {

    private User user;    // a User object

    private String sourceFile;  // the source file


    // EFFECTS: initializes the source in order to read from the file
    public ReadUsingJson(String source) {
        this.sourceFile = source;
    }


    // EFFECTS: sequentially reading from the JSON object in order to return it
    private UserInfo iterateFile(JSONObject jsonObject) {
        UserInfo userInfo = new UserInfo();
        for (int i = 0; i < jsonObject.keySet().size(); i++) {
            String name = jsonObject.getString("" + i);
            userInfo.getUserInfo().add(name);
            userInfo.loadIt(name);
        }
        return userInfo;
    }

    // EFFECTS: reads the source (destination file) and returns it
    private String readingTheFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: reads the values from the destination (source) file and
    // throws IOException if an error occurs during reading
    public UserInfo read() throws IOException {
        String readFile = readingTheFile(sourceFile);
        JSONObject jsonObject = new JSONObject(readFile);
        return iterateFile(jsonObject);
    }

}


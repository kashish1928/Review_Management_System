package persistence;


import model.exceptions.CommentTooLongException;
import model.User;
import model.UserInfo;
import model.exceptions.InsufficientInformationException;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// REFERENCE:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class WritingFileTest {

    @Test
    void invalidFileWriterTest() {
        try {
            WriteUsingJson writer = new WriteUsingJson("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // Pass
        }
    }

    @Test
    void emptyFileWriterTest() {
        try {
            UserInfo userInfo = new UserInfo();
            WriteUsingJson writer = new WriteUsingJson("./data/writeEmptyFile.json");
            writer.open();
            writer.write(userInfo);
            writer.close();

            ReadUsingJson reader = new ReadUsingJson("./data/writeEmptyFile.json");
            userInfo = reader.read();
            assertEquals(0, userInfo.getUserInfo().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void normalFileWriterTest() {
        try {
            UserInfo userInfo = new UserInfo();
            try {
                userInfo.addUser(new User("Sam","TV","Nice TV!!",2));
            } catch (InsufficientInformationException e) {
                System.out.println("Insufficient Information Provided");
            } catch (CommentTooLongException e) {
                System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
            }
            WriteUsingJson writer = new WriteUsingJson("./data/writeNormalFile.json");
            writer.open();
            writer.write(userInfo);
            writer.close();

            ReadUsingJson reader = new ReadUsingJson("./data/writeNormalFile.json");
            userInfo = reader.read();
            assertEquals(1,userInfo.getUserInfo().size());
            ArrayList<String> user = userInfo.getUserInfo();

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}

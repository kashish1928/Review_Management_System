package persistence;

import model.UserInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// REFERENCE:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class ReadingFileTest {


    @Test
    void invalidFileReaderTest() {
        ReadUsingJson readFile = new ReadUsingJson("./data/randomFile.json");
        try {
            UserInfo userInfo = readFile.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void emptyFileReaderTest() {
        ReadUsingJson reader = new ReadUsingJson("./data/readEmptyFile.json");
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            UserInfo userInfo = reader.read();
            assertEquals(arrayList,userInfo.getUserInfo());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void normalFileReaderTest() {
        ReadUsingJson reader = new ReadUsingJson("./data/readNormalFile.json");
        HashSet<String> hashSet = new HashSet<>();
            hashSet.add("0");
            hashSet.add("1");
            hashSet.add("2");
        try {
            UserInfo userInfo = reader.read();
            assertEquals(hashSet.size(),userInfo.getUserInfo().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

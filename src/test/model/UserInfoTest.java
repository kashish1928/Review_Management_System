package model;


import model.exceptions.CommentTooLongException;
import model.exceptions.InsufficientInformationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UserInfoTest {

    UserInfo userInfo;
    User u1 = new User("Sam","Radio","Amazing",9);
    User u2 = new User("Will","TV","It's broken",3);

    @BeforeEach
    public void runBeforeEach() {
        userInfo = new UserInfo();
    }

    @Test
    void addInvalidUsersTest() {

        // TEST CASE : Name is empty
        try {
            userInfo.addUser(new User("","TV","It's broken",3));
            fail("This is not supposed to run!!!");
        } catch (InsufficientInformationException e) {
            // EXPECTED
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }

        // TEST CASE : ItemName is empty
        try {
            userInfo.addUser(new User("Sam","","It's broken",3));
            fail("This is not supposed to run!!!");
        } catch (InsufficientInformationException e) {
            // EXPECTED
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }

        // TEST CASE : Name and ItemName is empty
        try {
            userInfo.addUser(new User("","","It's broken",3));
            fail("This is not supposed to run!!!");
        } catch (InsufficientInformationException e) {
            // EXPECTED
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }

        // TEST CASE : Comment is greater than 500 characters
        try {
            String c = "";
            for (int i = 0; i < 501; i++) {
                c = c + i;
            }
            userInfo.addUser(new User("Sam","Remote",c,3));
            fail("This is not supposed to run!!!");
        } catch (InsufficientInformationException e) {
            System.out.println("Insufficient Information Provided");
            fail();
        } catch (CommentTooLongException e) {
            // EXPECTED
        }

        // TEST CASE : Comment is empty
        try {
            userInfo.addUser(new User("Sam","TV","",3));
            fail("This is not supposed to run!!!");
        } catch (InsufficientInformationException e) {
            // EXPECTED
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }
    }

    @Test
    void addValidUsers() {

        try {
            userInfo.addUser(u1);
            assertEquals(1,userInfo.getUserInfo().size());
            assertEquals(1,userInfo.getUserData().size());
        } catch (InsufficientInformationException e) {
            System.out.println("Insufficient Information Provided");
            fail("Oops this is not supposed to fail...");
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
            fail("Oops this is not supposed to fail...");
        }


        try {
            userInfo.addUser(u2);
            assertEquals(2,userInfo.getUserInfo().size());
            assertEquals(2,userInfo.getUserData().size());
        } catch (InsufficientInformationException e) {
            System.out.println("Insufficient Information Provided");
            fail("Oops this is not supposed to fail...");
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }

    }

    @Test
    void deleteUserTest() {
        try {
            userInfo.addUser(u1);
            userInfo.addUser(u2);
        } catch (InsufficientInformationException e) {
            System.out.println("Insufficient Information Provided");
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }


        try {
            userInfo.deleteUser(u1);
            assertEquals(1,userInfo.getUserInfo().size());
            assertEquals(1,userInfo.getUserData().size());
        } catch (InsufficientInformationException e) {
            System.out.println("Insufficient Information Provided");
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }


        try {
            userInfo.deleteUser(u2);
            assertEquals(0,userInfo.getUserInfo().size());
            assertEquals(0,userInfo.getUserData().size());
        } catch (InsufficientInformationException e) {
            System.out.println("Insufficient Information Provided");
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }

    }
}

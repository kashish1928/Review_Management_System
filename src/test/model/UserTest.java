package model;


import model.exceptions.CommentTooLongException;
import model.exceptions.InsufficientInformationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user;
    User u1 = new User("Bill","Pen","",-1);
    User u2 = new User("Billy","Pencil","It was a nice pencil",13);
    @BeforeEach
    void runBeforeEach(){
        user = new User("Sam","TV","Looks amazing however the remote has a scratch",7);
    }

    @Test
    void constructorTest(){
        assertEquals("Sam",user.getName());
        assertEquals("TV",user.getItemName());
        assertFalse(user.getComment().equals("empty"));
        assertEquals("Looks amazing however the remote has a scratch",user.getComment());
        assertTrue(user.getRating() <= 10 && user.getRating() >= 0);
        assertEquals(7,user.getRating());

        assertEquals("",u1.getComment());
        assertEquals(-1,u1.getRating());

        assertEquals(13,u2.getRating());
    }

    @Test
    void updateNameTest(){
        assertEquals("[Willy, TV, Looks amazing however the remote has a scratch, 7]",user.updateName("Willy"));
        assertEquals("Willy",user.getName());
    }

    @Test
    void updateItemNameTest(){
        assertEquals("[Sam, Radio, Looks amazing however the remote has a scratch, 7]",user.updateItemName("Radio"));
        assertEquals("Radio",user.getItemName());
    }

    @Test
    void updateCommentTest(){
        assertEquals("[Sam, TV, , 7]",user.updateComment(""));
        assertEquals("",user.getComment());

        assertEquals("[Sam, TV, It was okay, 7]",user.updateComment("It was okay"));
        assertEquals("It was okay",user.getComment());
    }

    @Test
    void updateRatingTest(){
        assertEquals("[Sam, TV, Looks amazing however the remote has a scratch, 0]",user.updateRating(0));
        assertEquals(0,user.getRating());

        assertEquals("[Sam, TV, Looks amazing however the remote has a scratch, 0]",user.updateRating(11));
        assertEquals(0,user.getRating());

        assertEquals("[Sam, TV, Looks amazing however the remote has a scratch, 0]",user.updateRating(-1));
        assertEquals(0,user.getRating());
    }

    @Test
    void invalidDetailsTest() {

        User u3;

        try {
            u3 = new User("","Pencil","It was a nice pencil",13);
            u3.details();
            assertEquals("[, TV, Looks amazing however the remote has a scratch, 7]",user.details());
            fail("This is not supposed to run");
        } catch (InsufficientInformationException e) {
            System.out.println("Insufficient Information Provided");
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }

        try {
            u3 = new User("Sam","","It was a nice pencil",13);
            u3.details();
            assertEquals("[Sam, , Looks amazing however the remote has a scratch, 7]",user.details());
            fail("This is not supposed to run");
        } catch (InsufficientInformationException e) {
            System.out.println("Insufficient Information Provided");
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }

        try {
            u3 = new User("","","It was a nice pencil",13);
            u3.details();
            assertEquals("[, , Looks amazing however the remote has a scratch, 7]",user.details());
            fail("This is not supposed to run");
        } catch (InsufficientInformationException e) {
            System.out.println("Insufficient Information Provided");
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }

        try {
            String maxString = "";
            for (int i = 0; i < 501; i++) {
                maxString = maxString + i;
            }
            u3 = new User("Sam","Remote",maxString,13);
            u3.details();
            assertEquals("[Sam,Remote," + maxString + ", 7]",user.details());
            fail("This is not supposed to run");
        } catch (InsufficientInformationException e) {
            System.out.println("Insufficient Information Provided");
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }

        try {
            u3 = new User("Max","Pencil","nice",-1);
            u3.details();
        } catch (InsufficientInformationException e) {
            System.out.println("Insufficient Information Provided");
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }

        try {
            u3 = new User("Max","Pencil","nice",11);
            u3.details();
        } catch (InsufficientInformationException e) {
            System.out.println("Insufficient Information Provided");
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }

    }

    @Test
    void validDetailsTest() {
        try {
            user.details();
            assertEquals("[Sam, TV, Looks amazing however the remote has a scratch, 7]",user.details());
        } catch (InsufficientInformationException e) {
            System.out.println("Insufficient Information Provided");
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }
    }


}
package model;


import model.exceptions.CommentTooLongException;
import model.exceptions.InsufficientInformationException;

import javax.swing.*;

// Represents a single user having : Name, Item Name, Comment, Rating
public class User extends JPanel {
    private String name;                   // the user's name
    private String itemName;               // the name of the item
    private String comment;                // the user's comment on the item
    private int rating;                    // the user's rating on the item
    private static int UPPER_BOUND = 10;   // highest possible rating of an item
    private static int LOWER_BOUND = 0;    // lowest possible rating of an item
    private static int CHARACTER_LIMIT = 500;  // maximum number of characters possible in comment

    // REQUIRE: comment must not exceed 500 characters;
    // EFFECTS: if comment == empty, produce "empty"; the rating is >= 1 and <=10
    public User(String name, String itemName, String comment, int rating) {
        this.name = name;
        this.itemName = itemName;
        this.comment = comment;
        this.rating = rating;
    }

    // Getter method to return name
    public String getName() {
        return name;
    }

    // Getter method to return item name
    public String getItemName() {
        return itemName;
    }

    // Getter method to return comment
    public String getComment() {
        return comment;
    }

    // Getter method to return rating
    public int getRating() {
        return rating;
    }

    // REQUIRE: non-empty name,itemName
    // MODIFIES: this
    // EFFECTS: concatenates a string to produce a string of the name + itemName +
    //          comment + rating
    public String details() throws InsufficientInformationException, CommentTooLongException {
        if (name.isEmpty() || itemName.isEmpty() || rating < 0 || rating > 10) {
            throw new InsufficientInformationException();
        }
        if (comment.length() > CHARACTER_LIMIT) {
            throw new CommentTooLongException();
        }
        String value = "[" + name + ", " + itemName + ", " + comment + ", " + rating + "]";
        return value;
    }

    // MODIFIES:this
    // EFFECTS: changes the name of the user
    public String updateName(String newName) {
        this.name = newName;
        String newValue = "[" + name + ", " + itemName + ", " + comment + ", " + rating + "]";
        return newValue;
    }

    // MODIFIES:this
    // EFFECTS: changes the item name of the user
    public String updateItemName(String newItemName) {
        this.itemName = newItemName;
        String newValue = "[" + name + ", " + itemName + ", " + comment + ", " + rating + "]";
        return newValue;
    }

    // MODIFIES:this
    // EFFECTS: changes the comment of the user if non-empty, else changes it to empty
    public String updateComment(String newComment) {
        this.comment = newComment;
        String newValue = "[" + name + ", " + itemName + ", " + comment + ", " + rating + "]";
        return newValue;
    }

    // MODIFIES:this
    // EFFECTS: changes the rating of the user if >= 1 and <= 10 else changes it to 0
    public String updateRating(int newRating) {
        this.rating = 0;
        String newValue = "[" + name + ", " + itemName + ", " + comment + ", " + rating + "]";
        return newValue;
    }


}


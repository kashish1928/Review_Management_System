package ui;


import model.User;
import model.UserInfo;

import model.exceptions.CommentTooLongException;
import model.exceptions.InsufficientInformationException;

import persistence.ReadUsingJson;
import persistence.WriteUsingJson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Review system menu class
public class Menu {

    private Scanner input;                      // the input
    private User user;                          // a user
    private UserInfo userInfo = new UserInfo(); // userinfo is instantiated
    private WriteUsingJson jsonWriter;          // a JSON object is instantiated for writing
    private ReadUsingJson jsonReader;           // a JSON object is instantiated for reading
    private static String LOCATION = "./data/myJSON.json";  // location of the JSON file to read/write from

    // EFFECTS: prompts the user to exit or not, exits if yes else continues
    public Menu() {
        jsonWriter = new WriteUsingJson(LOCATION);
        jsonReader = new ReadUsingJson(LOCATION);
        boolean continuePrgm = true;
        while (continuePrgm) {
            mainDisplay();
            input = new Scanner(System.in);
            System.out.println("Do you want to exit ?");
            System.out.println("---------------------");
            System.out.println("PRESS y - YES");
            System.out.println("PRESS n - NO");
            String quit = input.nextLine();
            if (quit.equals("y")) {
                System.out.println("\nTHANK YOU FOR USING THIS SERVICE!!\n");
                System.out.println("\t@ABOUT US");
                System.out.println("\tDeveloper - KASHISH JOSHIPURA");
                System.out.println("\tInstitution - UBC");
                System.out.println("\tFaculty - SCIENCE");
                continuePrgm = false;
            }
        }
    }

    // EFFECTS: prompts the user to select a particular task to perform
    public void mainDisplay() {
        System.out.println("~~~~~~~~~");
        System.out.println("WELCOME!!");
        System.out.println("~~~~~~~~~");
        System.out.println("±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±");
        System.out.println("THIS IS AN ONLINE REVIEW SYSTEM AIMED TO HELP THE USERS IN DAY TO DAY LIVES");
        System.out.println("±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±±");
        System.out.println("Please select the task you wish to perform :");
        System.out.println("--------------------------------------------");
        System.out.println("1. Add a review");
        System.out.println("2. Display all the reviews");
        System.out.println("3. Update your review");
        System.out.println("4. Delete your review");
        System.out.println("5. Save your review");
        System.out.println("6. Load your review");
        System.out.println("7. EXIT");
        conditionsForDisplay();

    }

    // REQUIRE: an int type input between 1-4
    // EFFECTS: performs the respective tasks as per user prompt
    public void conditionsForDisplay() {
        input = new Scanner(System.in);
        int number = input.nextInt();

        if (number == 1) {
            addReviews();
        } else if (number == 2) {
            readAllReviews();
        } else if (number == 3) {
            updateYourReviews();
        } else if (number == 4) {
            deleteReviews();
        } else if (number == 5) {
            saveWorkRoom();
        } else if (number == 6) {
            loadWorkRoom();
        } else if (number < 1 || number > 7) {
            System.out.println("\nInvalid Input...\n");
        }
    }

    // MODIFIES: this
    // EFFECTS : adds review to the list; prompts the user to enter their name, item name, comments and rating
    public void addReviews() {

        input = new Scanner(System.in);
        System.out.println("Enter your Name");
        String name = input.nextLine();

        input = new Scanner(System.in);
        System.out.println("Enter the name of the item you wish to review");
        String itemName = input.nextLine();

        input = new Scanner(System.in);
        System.out.println("Enter comments");
        String comment = input.nextLine();

        input = new Scanner(System.in);
        System.out.println("Rate the item from 1-10");
        int rate = input.nextInt();

        user = new User(name, itemName, comment, rate);
        try {
            userInfo.addUser(user);
        } catch (InsufficientInformationException e) {
            System.out.println("\nINSUFFICIENT INFORMATION PROVIDED!!!\n");
        } catch (CommentTooLongException e) {
            System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
        }

    }

    // MODIFIES: this
    // EFFECTS: deletes the record from the list
    public void deleteReviews() {
        input = new Scanner(System.in);
        System.out.println("Enter the name of the person whose review you wish to delete");
        String delName = input.nextLine();
        for (User details : userInfo.getUserData()) {
            if (details.getName().equals(delName)) {
                try {
                    userInfo.deleteUser(details);
                } catch (InsufficientInformationException e) {
                    System.out.println("Insufficient Information Provided");
                } catch (CommentTooLongException e) {
                    System.out.println("\nCOMMENT IS LONGER THAN 500 CHARACTERS\n");
                }
                userInfo.getUserInfo().remove(delName);
                System.out.println("\nSUCCESSFULLY DELETED!!\n");
            }
        }

    }

    // EFFECTS: prompts the user to choose the category
    public void updateYourReviews() {
        input = new Scanner(System.in);
        System.out.println("What do you want to update?");
        System.out.println("---------------------------");
        System.out.println("1. Name");
        System.out.println("2. Item Name");
        System.out.println("3. Comment");
        System.out.println("4. Rating");
        int updateOptions = input.nextInt();
        conditionsForUpdate(updateOptions);
    }


    // EFFECTS: reads all the records from the list
    public void readAllReviews() {
        for (String el : userInfo.getUserInfo()) {
            System.out.println(el);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the records as per selected category
    public void conditionsForUpdate(int input) {

        Scanner newInput = new Scanner(System.in);

        if (input == 1) {
            System.out.println("Enter your name");
            String newName = newInput.nextLine();
            System.out.println(user.updateName(newName));
        } else if (input == 2) {
            System.out.println("Enter your item name");
            String newItemName = newInput.nextLine();
            System.out.println(user.updateItemName(newItemName));
        } else if (input == 3) {
            System.out.println("Comment below");
            String newComment = newInput.nextLine();
            System.out.println(user.updateComment(newComment));
        } else if (input == 4) {
            System.out.println("Rate this from 1-10");
            int newRate = newInput.nextInt();
            System.out.println(user.updateRating(newRate));
        } else {
            System.out.println("Invalid selection....");
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(userInfo);
            jsonWriter.close();
            System.out.println("Saved " + userInfo.getUserInfo() + " to " + LOCATION);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + LOCATION);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            userInfo = jsonReader.read();
            System.out.println("Loaded " + userInfo.getUserInfo() + " from " + LOCATION);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + LOCATION);
        }
    }


}

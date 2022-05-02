package model;


import model.exceptions.CommentTooLongException;
import model.exceptions.InsufficientInformationException;

import java.util.ArrayList;
import java.util.HashSet;

import model.exceptions.LogException;
import org.json.JSONObject;
import persistence.ObjectJson;
import ui.LogPrinter;


// represents a list of users
public class UserInfo implements ObjectJson {
    private User user;                        // a user
    private ArrayList<String> infoOfUser;     // an array list containing string (the details of the user)
    private HashSet<User> listOfUsers;      // an array list containing users
    private int index = 0;                    // index of ArrayList initialization
    private HashSet<String> guiList;
    private ArrayList<Integer> listOfRating;
    private ArrayList<String> listOfNames;

    // REQUIRE: Two empty ArrayList<> of type String and User
    // EFFECTS: Both the list are instantiated
    public UserInfo() {
        infoOfUser = new ArrayList<>();
        listOfUsers = new HashSet<>();
        guiList = new HashSet<>();
        listOfRating = new ArrayList<>();
        listOfNames = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: Adds the user and user details to the list
    public void addUser(User user) throws InsufficientInformationException, CommentTooLongException,
            NullPointerException {
        this.user = user;
        if (user.getName().equals("") || user.getItemName().equals("") || user.getComment().equals("")) {
            throw new InsufficientInformationException();
        }
        infoOfUser.add(user.details());
        listOfUsers.add(user);

    }


    // MODIFIES: this
    // EFFECTS: Deletes the user and user details from the list
    public void deleteUser(User user) throws InsufficientInformationException, CommentTooLongException {
        this.user = user;
        infoOfUser.remove(user.details());
        listOfUsers.remove(user);
    }

    // Getter method to return a list of string containing user information
    public ArrayList<String> getUserInfo() {
        return infoOfUser;
    }

    // Getter method to return a list of User containing users
    public HashSet<User> getUserData() {
        return listOfUsers;
    }

    // Getter method to return the index (of an element in an Arraylist)
    public int getIndex() {
        return index;
    }

    // MODIFIES: this
    // EFFECTS: adds the rating to the list
    public void addRating(int rating) {
        listOfRating.add(rating);
        EventLog.getInstance().logEvent(new Event("Rated item : " + rating));
    }

    // MODIFIES: this
    // EFFECTS: adds the name to the list
    public void addGuiList(String guiName) {
        listOfNames.add(guiName);
        EventLog.getInstance().logEvent(new Event(guiName + " entered their information"));
    }

    // EFFECTS: calling logEvent instance for editing
    public void logEdit(String edit) {
        EventLog.getInstance().logEvent(new Event("Edited the " + edit));
    }

    // EFFECTS: calling logEvent instance for printing
    public void logPrintIt(LogPrinter lp) throws LogException {
        lp.printLog(EventLog.getInstance());
    }

    // EFFECTS: calling EventLog instance
    public EventLog getInstance() {
        return EventLog.getInstance();
    }

    // EFFECTS: loads the name
    public void loadIt(String name) {
        EventLog.getInstance().logEvent(new Event("Loaded : " + name));
    }

    // MODIFIES: myJSON.json
    // EFFECTS: Adds individual user information into the JSON file
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        for (String i: infoOfUser) {
            json.put("" + getIndex(), i);
            index++;
            EventLog.getInstance().logEvent(new Event("Saved: " + i));
        }
        return json;
    }
}

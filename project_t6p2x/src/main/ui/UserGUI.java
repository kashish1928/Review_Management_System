package ui;


import model.Event;
import model.exceptions.CommentTooLongException;
import model.exceptions.InsufficientInformationException;
import model.exceptions.LogException;
import model.User;
import model.UserInfo;
import persistence.ReadUsingJson;
import persistence.WriteUsingJson;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// REFERENCE:
// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
// Represents the GUI part of the project
public class UserGUI extends JFrame implements ActionListener {

    private JFrame frame;                                    // a JFrame object
    private JTextArea textArea;                              // a JTextArea object
    private JButton button1;                                 // a JButton object for Name
    private JTextField textField1;                           // a JTextField object for Name
    private JTextField textField2;                           // a JTextField object for Item Name
    private JTextField textField3;                           // a JTextField object for Comment
    private JTextField textField4;                           // a JTextField object for Rating
    private JPanel panel1;                                   // a JPanel object
    private JPanel panel2;                                   // a JPanel object
    private JButton button2;                                 // a JButton object for Item Name
    private JButton button3;                                 // a JButton object for Comment
    private JButton button4;                                 // a JButton object for Rating
    private JButton button5;                                 // a JButton object for Done
    private JButton button6;                                 // a JButton object for Exiting
    private JButton button7;                                 // a JButton object for Editing
    private JButton button8;                                 // a JButton object for Graph
    private JTextArea column1;                               // a JTextArea object for Name
    private JTextArea column2;                               // a JTextArea object for Item Name
    private JTextArea column3;                               // a JTextArea object for Comment
    private JTextArea column4;                               // a JTextArea object for Rating
    private ArrayList<Integer> listOfRatings;                // an ArrayList of Ratings
    private Graph graph;                                     // a Graph object
    private WriteUsingJson jsonWriter;                       // a JSON object is instantiated for writing
    private ReadUsingJson jsonReader;                        // a JSON object is instantiated for reading
    private static final String LOCATION = "./data/myJSON.json";   // location of the JSON file to read/write from
    private UserInfo userInfo;                               // an UserInfo object
    private String string = "";                              // a String
    private ArrayList<String> listOfGuiInfo;                 // an ArrayList of GUI information
    private User user;                                       // a User object
    private int count = 1;                                   // an int
    private JPanel panel3;                                   // a JPanel object
    private JRadioButton radioButton1;                       // a JRadioButton object
    private JRadioButton radioButton2;                       // a JRadioButton object
    private Border border;                                   // a Border object
    private JTextArea textArea2;                             // a JTextArea object
    private JPanel mainPanel;                                // a JPanel object
    private JPanel textFieldPanel;                           // a JPanel object
    private static final String NONE = "...None";            // a String to represent No option
    private static final String SCREEN = "...screen";        // a String to represent Screen option
    private JComboBox<String> printCombo;                    // a JComboBox<String> object
    private JFrame logFrame;                                 // a JFrame object
    private ScreenPrinter printer;                           // a ScreenPrinter object
    private JButton button9;                                 // a JButton object for Log



    // EFFECTS: instantiates the fields
    public UserGUI() {
        printer = new ScreenPrinter();
        listOfGuiInfo = new ArrayList<>();
        jsonWriter = new WriteUsingJson(LOCATION);
        jsonReader = new ReadUsingJson(LOCATION);
        textFieldPanel = new JPanel();
        userInfo = new UserInfo();
        mainPanel = new JPanel();
        frame = new JFrame();
        textArea = new JTextArea();
        textField1 = new JTextField();
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        buttonCall();
        columnCall();
        textArea2 = new JTextArea("PREVIOUS DATA \n");
        listOfRatings = new ArrayList<>();
        graph = new Graph(getRating());
        textArea2.append("Data is in the form : [NAME, ITEM NAME, COMMENT, RATING]\n");
        textArea2.append("\n");
    }

    // EFFECTS: a call function for buttons
    public void buttonCall() {
        logFrame = new JFrame();
        border = BorderFactory.createLineBorder(new Color(24,23,23), 5);
        button1 = new JButton("NAME (1)");
        button2 = new JButton("ITEM NAME (2)");
        button3 = new JButton("COMMENTS (3)");
        button4 = new JButton("RATING (4)");
        button5 = new JButton("DONE");
        button6 = new JButton("EXIT");
        button7 = new JButton("EDIT");
        button8 = new JButton("GRAPH");
        button9 = new JButton(new PrintLogAction());
        radioButton1 = new JRadioButton("SAVE INFO");
        radioButton2 = new JRadioButton("LOAD INFO");
    }

    // EFFECTS: a call function for columns
    public void columnCall() {
        column1 = new JTextArea("NAME \n");
        column2 = new JTextArea("ITEM NAME \n");
        column3 = new JTextArea("COMMENT \n");
        column4 = new JTextArea("RATING \n");
        column1.append("____________________________________________\n");
        column2.append("____________________________________________\n");
        column3.append("____________________________________________\n");
        column4.append("____________________________________________\n");
    }

    // EFFECTS: does the work of the constructor by calling functions related to the ones defined in the constructor
    public void constructorsJob() {
        frame();
        textArea();
        button1();
        textField1();
        panel1();
        panel2();
        functionCalls();
        textFieldPanel();
    }

    // EFFECTS: calls a bunch of buttons  and other  functions
    public void functionCalls() {
        button2();
        button3();
        button4();
        button5();
        button6();
        button7();
        button8();
        button9();
        panel3();
        radioButton1();
        radioButton2();
        textArea2();
        mainPanel();
        textField2();
        textField3();
        logFrame();
        textField4();
    }

    // MODIFIES: this
    // EFFECTS: creates a main frame for the entire display
    public void frame() {
        frame.setTitle("My GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(1000, 1000);
        frame.setLayout(new GridLayout(2, 1));
        frame.getContentPane().setBackground(Color.BLACK);
        frame.add(mainPanel);
        frame.add(panel1);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println(printer.getTextFromTextArea());
                logPrintFunction();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }
        });
        frame.setVisible(true);
    }

    public void logFrame() {
        logFrame.setTitle("LOG");
        logFrame.setResizable(true);
        logFrame.setSize(1000, 1000);
    }

    // MODIFIES: this
    // EFFECTS: creates a main panel for the entire display
    public void mainPanel() {
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.setBackground(Color.white);
        mainPanel.add(textArea);
        mainPanel.add(textArea2);
        mainPanel.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: creates a text area for representing the current user information
    public void textArea() {
        textArea.setLayout(new GridLayout(1, 4));
        textArea.setVisible(true);
        textArea.setBorder(border);
        textArea.add(column1);
        textArea.add(column2);
        textArea.add(column3);
        textArea.add(column4);
        textArea.setBackground(new Color(201, 222, 171));
        column1.setEditable(false);
        column2.setEditable(false);
        column3.setEditable(false);
        column4.setEditable(false);
    }

    // MODIFIES: this
    // EFFECTS: creates a text area for displaying loaded information
    public void textArea2() {
        //textArea2.setBackground(new Color(201,222,171));
        textArea2.setLayout(new GridLayout(1, 1));
        textArea2.setEditable(false);
        textArea2.setBorder(border);
        textArea2.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a text field for name
    public void textField1() {
        textField1.setBackground(new Color(201, 222, 171));
        textField1.setVisible(true);
        textField1.setBorder(border);
    }

    // MODIFIES: this
    // EFFECTS: creates a text field for item name
    public void textField2() {
        textField2.setBackground(new Color(201, 222, 171));
        textField2.setVisible(true);
        textField2.setEditable(false);
        textField2.setBorder(border);
    }

    // MODIFIES: this
    // EFFECTS: creates a text field for comment
    public void textField3() {
        textField3.setBackground(new Color(201, 222, 171));
        textField3.setVisible(true);
        textField3.setEditable(false);
        textField3.setBorder(border);
    }

    // MODIFIES: this
    // EFFECTS: creates a text field for rating
    public void textField4() {
        textField4.setBackground(new Color(201, 222, 171));
        textField4.setVisible(true);
        textField4.setEditable(false);
        textField4.setBorder(border);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the bottom part of the display
    public void panel1() {
        panel1.setLayout(new GridLayout(1, 2));
        panel1.setBackground(Color.orange);
        panel1.setVisible(true);
        panel1.add(textFieldPanel);
        panel1.add(panel2);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the text fields
    public void textFieldPanel() {
        textFieldPanel.setLayout(new GridLayout(4, 1));
        textFieldPanel.setBackground(Color.black);
        textFieldPanel.setVisible(true);
        textFieldPanel.add(textField1);
        textFieldPanel.add(textField2);
        textFieldPanel.add(textField3);
        textFieldPanel.add(textField4);
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the buttons
    public void panel2() {
        panel2.setLayout(new GridLayout(4, 3));
        panel2.setBackground(new Color(25, 51, 35));
        panel2.setVisible(true);
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);
        panel2.add(button5);
        panel2.add(button6);
        panel2.add(button7);
        panel2.add(button8);
        panel2.add(panel3);
        panel2.add(button9);
        panel2.add(createPrintCombo());
    }

    // MODIFIES: this
    // EFFECTS: creates a panel for the radio buttons
    public void panel3() {
        panel3.setBorder(border);
        panel3.setLayout(new GridLayout(2, 1));
        panel3.setBackground(new Color(201, 222, 171));
        panel3.add(radioButton2);
        panel3.add(radioButton1);
        panel3.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates a button for name
    public void button1() {
        button1.setVisible(true);
        button1.setFont(new Font("Apple Casual", Font.BOLD, 15));
        button1.addActionListener(this);
        button1.setBorder(border);
        button1.setForeground(Color.white);
    }

    // MODIFIES: this
    // EFFECTS: creates a button for item name
    public void button2() {
        button2.setBorder(border);
        button2.setFont(new Font("Apple Casual", Font.BOLD, 15));
        button2.setVisible(true);
        button2.addActionListener(this);
        button2.setEnabled(false);
        button2.setBackground(Color.ORANGE);
        button2.setForeground(Color.white);
    }

    // EFFECTS: creates a button for comment
    public void button3() {
        button3.setBorder(border);
        button3.setFont(new Font("Apple Casual", Font.BOLD, 15));
        button3.setVisible(true);
        button3.addActionListener(this);
        button3.setEnabled(false);
        button3.setForeground(Color.white);
    }

    // MODIFIES: this
    // EFFECTS: creates a button for rating
    public void button4() {
        button4.setBorder(border);
        button4.setFont(new Font("Apple Casual", Font.BOLD, 15));
        button4.setVisible(true);
        button4.addActionListener(this);
        button4.setEnabled(false);
        button4.setForeground(Color.white);
    }

    // MODIFIES: this
    // EFFECTS: creates a button for proceeding further
    public void button5() {
        button5.setBorder(border);
        button5.setVisible(true);
        button5.setFont(new Font("Apple Casual", Font.BOLD, 15));
        button5.addActionListener(this);
        button5.setEnabled(false);
        button5.setForeground(Color.white);
    }

    // MODIFIES: this
    // EFFECTS: creates a button for exiting
    public void button6() {
        button6.setBorder(border);
        button6.setFont(new Font("Apple Casual", Font.BOLD, 15));
        button6.setVisible(true);
        button6.addActionListener(this);
        button6.setForeground(Color.white);
    }

    // MODIFIES: this
    // EFFECTS: creates a button for editing
    public void button7() {
        button7.setBorder(border);
        button7.setFont(new Font("Apple Casual", Font.BOLD, 15));
        button7.setVisible(true);
        button7.addActionListener(this);
        button7.setForeground(Color.white);
    }

    // MODIFIES: this
    // EFFECTS: creates a button for graph
    public void button8() {
        button8.setBorder(border);
        button8.setFont(new Font("Apple Casual", Font.BOLD, 15));
        button8.setVisible(true);
        button8.addActionListener(this);
        button8.setForeground(Color.white);
    }

    // MODIFIES: this
    // EFFECTS: creates a radio-button for saving info
    public void radioButton1() {
        radioButton1.setForeground(Color.WHITE);
        radioButton1.setVisible(true);
        radioButton1.setFont(new Font("Apple Casual", Font.BOLD, 15));
        radioButton1.addActionListener(this);
        radioButton1.setForeground(Color.black);
    }

    // MODIFIES: this
    // EFFECTS: creates a radio-button for loading info
    public void radioButton2() {
        radioButton2.setForeground(Color.WHITE);
        radioButton2.setVisible(true);
        radioButton2.setFont(new Font("Apple Casual", Font.BOLD, 15));
        radioButton2.addActionListener(this);
        radioButton2.setForeground(Color.black);
    }

    // MODIFIES: this
    // EFFECTS: creates a button for logging events
    public void button9() {
        button9.setVisible(true);
        button9.setFont(new Font("Apple Casual", Font.BOLD, 15));
        button9.addActionListener(this);
        button9.setBorder(border);
        button9.setForeground(Color.white);
        button9.setEnabled(true);
    }

    // EFFECTS: performs the actions pertaining to button and radiobutton
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            button1Action();
        }

        if (e.getSource() == button2) {
            button2Action();
        }

        if (e.getSource() == button3) {
            button3Action();
        }

        if (e.getSource() == button4) {
            button4Action();
        }

        if (e.getSource() == button5) {
            button5Action();
        }

        buttonCondition(e);

    }

    // EFFECTS: conditions required for the buttons to work
    public void buttonCondition(ActionEvent e) {
        if (e.getSource() == button6) {
            frame.setVisible(false);
            System.out.println(printer.getTextFromTextArea());
            logPrintFunction();
            System.exit(0);
        }

        if (e.getSource() == button7) {
            button7Action();
        }

        if (e.getSource() == button8) {
            graph.getFrame().setVisible(true);
        }

        if (e.getSource() == radioButton1) {
            radioButton1Action();
        }

        if (e.getSource() == radioButton2) {
            radioButton2Action();
        }

        if (e.getSource() == button9) {
            new PrintLogAction();
        }
    }

    // EFFECTS: a button for entering the name
    public void button1Action() {
        if (count % 4 != 0) {
            string = string + textField1.getText() + " ";
            count++;
        } else {
            listOfGuiInfo.add(string);
            count = 1;
            string = "";
            string = string + textField1.getText() + " ";
        }
        textField2.setEditable(true);
        column1.append(textField1.getText() + "\n");
        button1.setEnabled(false);
        button2.setEnabled(true);
    }

    // EFFECTS: a button for entering the item name
    public void button2Action() {
        count++;
        string = string + " " + textField2.getText();
        column2.append(textField2.getText() + "\n");
        textField3.setEditable(true);
        button2.setEnabled(false);
        button3.setEnabled(true);
        textField1.setEditable(false);
    }

    // EFFECTS: a button for entering the comment
    public void button3Action() {
        count++;
        string = string + " " + textField3.getText();
        column3.append(textField3.getText() + "\n");
        textField4.setEditable(true);
        button3.setEnabled(false);
        button4.setEnabled(true);
        textField2.setEditable(false);
    }

    // EFFECTS: a button for entering the rating
    public void button4Action() {
        count++;
        string = string + " " + textField4.getText();
        int parseInt = -1;
        try {
            parseInt = Integer.parseInt(textField4.getText());
        } catch (NumberFormatException error) {
            numberErrorMessage();
            column4.append("N/A \n");
        }
        if (-1 < parseInt && parseInt < 11) {
            userInfo.addRating(parseInt);
            listOfRatings.add(parseInt);
            column4.append(parseInt + "\n");
        } else if (parseInt != -1) {
            ratingErrorMessage();
            column4.append("0 \n");
        }
        button4.setEnabled(false);
        button5.setEnabled(true);
        textField3.setEditable(false);
    }

    // EFFECTS: display an error message if rating is not an integer
    public void numberErrorMessage() {
        JOptionPane.showMessageDialog(frame,
                "Expected a number!!",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // EFFECTS: display an error message if rating < 0 or > 10
    public void ratingErrorMessage() {
        JOptionPane.showMessageDialog(frame,
                "Rating should be between 1 and 10!!",
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // EFFECTS: a button for proceeding further
    public void button5Action() {
        button1.setEnabled(true);
        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        button5.setEnabled(false);
        textArea.append("\n");
        displayingLoadedString();
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField1.setEditable(true);
    }

    // MODIFIES: this
    // EFFECTS: a button for editing
    public void button7Action() {
        textArea();
        int parseInt = -1;
        try {
            parseInt = Integer.parseInt(textField1.getText());
        } catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(frame,
                    "Expected a number!!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        if (parseInt == 1) {
            column1.setEditable(true);
            userInfo.logEdit("Name");
        } else if (parseInt == 2) {
            column2.setEditable(true);
            userInfo.logEdit("Item Name");
        } else if (parseInt == 3) {
            column3.setEditable(true);
            userInfo.logEdit("Comment");
        } else if (parseInt == 4) {
            column4.setEditable(true);
            userInfo.logEdit("Rating");
        }
    }

    // EFFECTS: constructs the radio button for saving the workroom
    public void radioButton1Action() {
        if (radioButton1.isSelected()) {
            saveWorkRoom();
            JFrame jframe = new JFrame();
            JOptionPane.showMessageDialog(jframe, "Successfully Saved");
            radioButton1.setSelected(false);
        } else {
            radioButton2.setEnabled(true);
        }
    }

    // EFFECTS: constructs the radio button for loading the workroom
    public void radioButton2Action() {
        if (radioButton2.isSelected()) {
            loadWorkRoom();
            for (String i : userInfo.getUserInfo()) {
                textArea2.append(i + "\n");
            }
            JFrame jframe = new JFrame();
            JOptionPane.showMessageDialog(jframe, "Successfully Loaded");
            radioButton2.setSelected(false);
        } else {
            radioButton1.setEnabled(true);
        }
    }

    // MODIFIES: this, userInfoList();
    // EFFECTS: initiates a user and adds it to the userInfo List
    public void displayingLoadedString() {
        String name = textField1.getText();
        String itemName = textField2.getText();
        String comments = textField3.getText();
        int rating = Integer.parseInt(textField4.getText());
        user = new User(name, itemName, comments, rating);
        userInfo.addGuiList(name);
        try {
            userInfo.addUser(user);
        } catch (InsufficientInformationException e) {
            //
        } catch (CommentTooLongException e) {
            //
        }
    }

    // Getter method for rating
    public ArrayList<Integer> getRating() {
        return listOfRatings;
    }

    // Getter method for GUI list
    public ArrayList<String> getNewGuiList() {
        return listOfGuiInfo;
    }


    // MODIFIES: myJSON.json
    // EFFECTS: saves the workroom to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(userInfo);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + LOCATION);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            userInfo = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + LOCATION);
        }
    }

    // EFFECTS: helper to create print options' combo box
    private JComboBox<String> createPrintCombo() {
        printCombo = new JComboBox<>();
        printCombo.addItem(NONE);
        printCombo.addItem(SCREEN);
        return printCombo;
    }

    // EFFECTS: action to be taken when the user wants to print the event log.
    private class PrintLogAction extends AbstractAction {
        PrintLogAction() {
            super("Print log to...");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String selected = (String) printCombo.getSelectedItem();
            LogPrinter lp;
            try {
                if (selected.equals(NONE)) {
                    JOptionPane.showMessageDialog(frame,
                            "OOPS",
                            "Input Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    lp = new ScreenPrinter();
                    logFrame();
                    logFrame.add((ScreenPrinter) lp);
                    logFrame.setVisible(true);
                    userInfo.logPrintIt(lp);
                }


            } catch (LogException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "System Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // EFFECTS: prints out the log on the console
    public void logPrintFunction() {
        for (Event next : userInfo.getInstance()) {
            System.out.println(next.toString());
        }
    }

}

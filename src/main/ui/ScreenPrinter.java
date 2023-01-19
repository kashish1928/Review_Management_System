package ui;

import java.awt.Component;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import model.Event;
import model.EventLog;

// REFERENCE:
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
// The display screen for the log events
// Represents a screen printer for printing event log to screen.
public class ScreenPrinter extends JInternalFrame implements LogPrinter {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private JTextArea logArea;
    private String string = "";

    // EFFECTS: Constructor for a text area in which log will be printed
    public ScreenPrinter() {
        super("Event log", false, true, false, false);
        logArea = new JTextArea();
        logArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        string = logArea.getText();
    }

    // Getter method for returning the text displayed on the text area
    public String getTextFromTextArea() {
        return string;
    }

    // MODIFIES: this
    // EFFECTS: Prints the events occurring onto the text area
    @Override
    public void printLog(EventLog el) {
        for (Event next : el) {
            logArea.setText(logArea.getText() + next.toString() + "\n\n");
        }

        repaint();
    }


    // MODIFIES: this
    // EFFECTS: Sets the position of window
    private void setPosition(Component parent) {
        setLocation(parent.getWidth() - getWidth() - 20,
                parent.getHeight() - getHeight() - 20);
    }

}

package ui;

import model.exceptions.LogException;
import model.EventLog;


// REFERENCE:
// https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
// Defines behaviours that event log printers must support.
public interface LogPrinter {

    // Prints the log
    void printLog(EventLog el) throws LogException;
}

# REVIEW SYSTEM
***
***

## ***What will the application do?***

This application takes in user reviews of certain products and displays it on the
user's command.The user also has the ability to add a review comment along with it's 
rating from a scale of 1 to 10 (1 being very dissatisfied and 10 being very satisfied)

## ***Who will use it?***

This application can be used by almost anyone. However, it will be most useful for
customers looking to buy a certain product for checking other user's experience or
expressing their opinions on that particular item so that it could be of other's use.

## ***Why is this project of interest to you?***

This project interested me because whenever we purchase online goods, there is always a 
chance that the product comes out not as expected. In order to prevent that a simple solution 
would be for the people to express their experience. In this way online shopping would be made
easier and the reviewer would get a chance to help others out.

***
***
#PHASE DESCRIPTION
***
**THIS PROJECT WAS DONE IN 4 PHASES :-**

- **PHASE 1 :**
Created the basic version of my Review System using user interface.

- **PHASE 2 :**
Adding persistence to my project

- **PHASE 3 :**
Creating the GUI Version of my project

- **PHASE 4 :**
Logging events


***
***
## USER STORIES
***

- As a user, I want to be able to input name, item names, comment and rating.
- As a user, I want to be able to view all the reviews on my infoOfUser list.
- As a user, I want to be able to add all my users to the listOfUsers list.
- As a user, I want to be able to edit my users to the listOfUsers list.
- As a user, I want to be able to add my users from User class to the UserInfo
- As a user, I want to be able to save my reviews in the infoOfUser list even after I have terminated my program.
- As a user, I want to be given an option to load my saved infoOfUser list.

***
***
##Sources: 
***
- TellerApp() - https://github.students.cs.ubc.ca/CPSC210/TellerApp
- JsonSerializationDemo() - https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
- Graph() - https://stackoverflow.com/questions/8693342/drawing-a-simple-line-graph-in-java
- Oracle - https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
- StackOverflow - https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
- AlarmSystem() - https://github.students.cs.ubc.ca/CPSC210/AlarmSystem

***
***
##PHASE 4: TASK 2
***
###***A SAMPLE LOG DISPLAYED IN THE CONSOLE :***

Fri Apr 01 11:21:24 PDT 2022

Rated item : 10

Fri Apr 01 11:21:25 PDT 2022

Kashish entered their information

Fri Apr 01 11:21:42 PDT 2022

Rated item : 2

Fri Apr 01 11:21:42 PDT 2022

Billy  entered their information

Fri Apr 01 11:21:45 PDT 2022

Edited the Item Name

Fri Apr 01 11:21:49 PDT 2022

Saved: [Kashish, Ball, Amazing bounce, 10]

Fri Apr 01 11:21:49 PDT 2022

Saved: [Billy , TV, Bad quality, 2]

Fri Apr 01 11:21:52 PDT 2022

Loaded : [Kashish, Ball, Amazing bounce, 10]

Fri Apr 01 11:21:52 PDT 2022

Loaded : [Billy , TV, Bad quality, 2]



###***THE LOG EVENTS ARE RECORDED WHEN:***
- The user rates the item
- The user enters all the required information i.e Name, Item Name, Comments and Rating
- The user edits/updates some information
- The user decides to save and load it's information

***
***
##PHASE 4: TASK 3
***

###***UML DIAGRAM:***
In my UML Diagram I have included all my classes except the built-in ones, furthermore, associations and
multiplicities along with relationships like extends and implements have also been used as described below :
 - EventLog class makes use of a list of Events
 - UserInfo makes use of user class (there is an aggregate relationship)
 - ObjectJson is an interface which is implemented by UserInfo
 - ReadUsingJson and WriteUsingJson are used in Menu and UserGUI class (used for persistence)
 - Graph and ScreenPrinter (which implements the interface LogPrinter) are a part of UserGUI

###***REFLECTION:***
 - If I had more time to do the project I would have made the visual component of my project a bit more holistic and
   compact and would have also tried to make it a bit more appealing.
 - For the visual aspect of my program I would have tried to implement the graph with respective labels and a better
   scale for representing my data.
 - I should have also made my edit feature better by maybe using checkboxes so that we can edit multiple columns at one 
   time

***
***


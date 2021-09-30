# Event Calender 
Event Calender is a simple event storing android application with cloud backup

## Technology Used:<b>
* Java 
* MVVM Achitectural Pattern
* Room 
* JUnit4 (unit testing)
* LiveData
* Firebase Authenthication
* Cloud Firestore</b>
<br/>

## Problem Requirement Analysis
<b>Use Cases:</b> 
1. Authenticate user
2. User Opens the app
3. See weekly calender with current dates events
4. User can add new events
5. Events store to room database
6. Backup events from room db to cloud firestore
7. Restore events from cloud to room db (if nessessary)

<img src="https://user-images.githubusercontent.com/30120066/135444568-27e6b476-eaf7-463d-a82e-67bc2bb522c4.jpeg" height="360"/>


### UI Design On Figma
------------------

<p align="center"> <img src="https://user-images.githubusercontent.com/30120066/135445079-a3ad392e-5276-4cc4-9d18-5741c3a41313.jpg" width="50%"/></p>

<br><br>

## How I approached
<b>1.</b> First did the requirement analysis and use case diagram<br>
<b>2.</b> Google searched for UI design and got inpired from them<br>
<b>3.</b> Designed my UI<br>
<b>4.</b> Prepared android project for <b>MVVM pattern</b> (made all packages and all and spd , room, view-model etc dependencies) <br>
<b>5.</b> Coded xml of homepage and Event input dialog<br>
<b>6.</b> Test CalenderUtils functionality using <b>JUnit4</b><br>
<b>7.</b> Thought about <b>Class diagram</b> but here only simple one class so didn't design any<br>
<b>8.</b> Event info data store to <b>ROOM database</b> and test if it's working<br>
<b>8.</b> Add firebase dependencies and project add to firebase<br>
<b>9.</b> XML of Login Register pages and Code functionality<br>
<b>10.</b> Backup and restore from cloud firestore<br>
<br>

## Reason behind technology and library selection
* <b >*MVVM (Model View ViewModel)*</b>: MVVM is a popular architectural pattern which is recommeneded by google. 
In this pattern, user requests from UI , that request first goes to corresponding ViewModel. Then that ViewModel requests to data repository. Meanwhile, <b>Observable (LiveData)</b> continues to obserse the data changes and notify when requested data is ready to serve.

* *<b>ViewModel</b>*: ViewModels holds the LiveData. As it is lifecycle aware, we don't need to think about sync data with UI and don't have to request multiple time for same data.

* *<b>ROOM Database</b>*: Room Database basically an abstract layer on top of SQLite database. It helps to write less code and do more of the local database works. We don't have to think about database cursor.
<br>

## Challenges I faced And How I solved it
* **MutableLiveData Nullable**: 
<i>Problem:</i> At first observers are getting *null* value and if we use that value directly, app crashes.
<i>Solution:</i> I had to check null before using that value
<br>

* **Executor Interface**: 
<i>Problem:</i> While creating new account or login account, firebase provided method requires <i>Executor instance</i>. In general case, we call those method from Activity class, so, firebase snippet recommend to use "this" as paramenter in the place where Executor object needed.
In my case, I had to use it from "AuthRepo" class. "this" was not working as "AuthRepo" is not on UI thread. 
<i>Solution:</i> So, I created another class which extend from "Executor" interface and used instance of that class. That's how background thread was being created. 
<br>

* **postValue() vs setValue()**: 
<i>Problem:</i> At first I used setvalue() method to assign values on a MutableLiveData instance. It was not working. Then I found out the reason. 
<i>Solution:</i> setValue() sets value only on UI thread. In my case, I need to assign value from background thread. So, I used postValue() method.
<br>

* **Room Database Insert and Background thread**: 
<i>Problem:</i> RoomDatabase doesn't allow to store data from Main Thread(UI Thread)
<i>Solution:</i> I created different java thread and inserted value from there.


## Design Pattern Used
* <b>Singleton Pattern</b>: RoomDatabasase Instance is heavy object, so I need to stop creating many object of it. So, singleton pattern is the answer. I used the same object using a method. That method was synchronised, that mean's <b>thread-safe</b>
 * <b>Observer Pattern</b>: Didn't implemented from scratch but it came with LiveData



## Clean Code
* <b>Code Reuse</b>: LoginActivity and RegisterActivity both need to use validate method to check email and password. So, I created another class "AuthCommonActivity", later LoginActivity and RegisterActivity both extended from "AuthCommonActivity".
![dry](https://user-images.githubusercontent.com/30120066/135458426-895c9e34-9f94-48be-a82c-656e05130e40.jpg)

* <b>Meaningful variables and function</b>: Tried to use as meaningful as possible variable and method name. Some case, that names became bigger.


##Data Structure Used

* **HashMap**
* **Array**
* **ArrayList**


## OOP principles 
* <b> Single Responsibility:</b> "Event" and "CalendarUtils" both have single responsibility. For example: CalendarUtils only deals with calerdar related data formatting.


## Future works
* Email validation, varifications and forgot password feature
* User profile with personalised data
* Events auto synchronization with cloud
* Events manipulation
* Different colored event based on priority
* Notifications feature before event time


## Screenshots of the app


<p align="center">
  <img alt="screen1" src="https://user-images.githubusercontent.com/30120066/135463398-7284c294-9ca7-451f-904e-fc5e9df57872.png" width="30%">
&nbsp; &nbsp; &nbsp; &nbsp;
  <img alt="screen2" src="https://user-images.githubusercontent.com/30120066/135463443-147ab93e-db9b-4c88-bd3e-d33424664c08.png" width="30%">

  <img alt="screen3" src="https://user-images.githubusercontent.com/30120066/135463453-17af8be1-8416-4b87-86f1-6b5666ffe005.png" width="30%">
</p>






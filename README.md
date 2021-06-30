# MyAttendance

## Description
QR Scanning based Attendance Management App which has the following features:
- Enables a teacher to create a class and generate a QR code , which the students have to scan in class.
- Allows teachers to send notice to students of a particular class
- Has a Bunk manager in students side of the app which allows them to calculate how many classes they can bunk to maintain attendance
- Feature to scan notes and convert them to a file and retrieve data from it, without saving it as a file on the phone

The app is built in Android Studio using Java. Firebase Database is used for backend. 

## Features Preview 
### Login and email verification
<p float="left"> 
  <img src="screenshots/starting page.jpeg" width="150">
  <img src="screenshots/instructions.jpeg" width="150">
  <img src="screenshots/views.jpeg" width="150">
  <img src="screenshots/sign up.jpeg" width="150">
  <img src="screenshots/verify.jpeg" width="150">
  <img src="screenshots/login.jpeg" width="150">
</p>
### Create classes for attendance, and a QR code, then scanning of QR from students side
<p float="left">
  <img src="screenshots/studentpage.jpeg" width="150">
  <img src="screenshots/teacherpage.jpeg" width="150">
</p>
<p float="left"> 
  <img src="screenshots/createclass.jpeg" width="150">
  <img src="screenshots/startscan.jpeg" width="150">
  <img src="screenshots/scanner.jpeg" width="150">
  <img src="screenshots/studentlist.jpeg" width="150">
  <img src="screenshots/classlist.jpeg" width="150">
</p>

### Additional Features (student side)
<p float="left">
  <img src="screenshots/bunk manager.jpeg" width="150">
  <img src="screenshots/scannote.jpeg" width="150">
  <img src="screenshots/notes.jpeg" width="150">
</p>

## Database Structure 
- Given below is the basic database structure, having a student side and a teachers side. The classes have another structure outside the member tree. (Not active currently)
<img src="screenshots/database.jpg" width="500">

## Setup 
- Clone this repo: git clone (link of this project)
- Open the project in Android Studio and run build

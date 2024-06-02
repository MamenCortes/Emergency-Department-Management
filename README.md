# Emergency Department Management
## Project User Manual
An emergency department management software developed as a group proyect for the Database University Course.
The system will have 4 main actors: 
- Manager
- Recepcionist
- Nurse
- Doctor

## Table of Contents
1. [How to Download and Run the Project](#how-to-download-and-run-the-project)
2. [Initial Conditions of the Program](#initial-conditions-of-the-program)
3. [User Interface Views](#user-interface-views)
    - [Actor 1: Manager](#actor-1-manager)
    - [Actor 2: Recepcionist](#actor-2-recepcionist)
    - [Actor 3: Triage Nurse](#actor-3-triage-nurse)
    - [Actor 4: Doctor](#actor-4-doctor)

## How to Download and Run the Project

1. Clone the repository:
    ```bash
    git clone https://github.com/MamenCortes/Emergency-Department-Management.git
    ```
2. Execute the class **Application.java** in the package urgency.ui

## Initial Conditions of the Program

Upon executing the system for the first time, the following users will be automatically added to facilitate the system's navigation: 

| Email  | Password   | Role     |
|-----------|------------|----------|
| mamen.cortes@hospital.com     | 12345678   | Manager    |
| carmen.navarro@hospital.com   | 12345678 | Recepcionist  |
| maria.gala@hospital.com | 12345678| Nurse |
| sam.bennett@hospital.com     | 12345678   | Doctor    |
| meredith.grey@hospital.com     | 12345678   | Doctor    |
| owen.hunt@hospital.com     | 12345678   | Doctor    |
| carina.deluca@hospital.com     | 12345678   | Doctor    |
| alex.karev@hospital.com     | 12345678   | Doctor    |
| raj.sen@hospital.com     | 12345678   | Doctor    |

*The password 12345678 was used by default. However it can be changed from the LogIn view*

## User Interface Views
### LogIn and Register
##### Log In
<img src="images/logIn.png" width="500">

- Upon running the Application class, the first view will be the LogIn Panel. Here, the actors can log In into the system using their respective emails and passwords.
- By clicking the *Register* button, the LogIn panel changes into the Create Account panel.

##### Register
<img src="images/register.png" width="500">

- Here recepcionists, nurses and managers can be registered.
- New doctors can only be created by the manager. 
- Only emails ending in *@hospital.com* will be able to register.
  
##### Change Password
<img src="images/changePasword.png" width="500">

- Passwords can be changed by clicking the *Forgot your password?* button. The user must introduce the email before. If the email introduced belongs to a user, the Change Password panel pops up.
- The passwords must contain at least one number and a minimum of 8 characters.
  
### Actor 1: Manager
##### Menu
<img src="images/managerMenu.png" width="500">

##### Add Doctor
<img src="images/addDoctor.png" width="500">

##### Search Doctor
<div>
    <img src="images/searchDoctor.png" style="float: left; margin-right: 10px;" width="550"/>
    <img src="images/modifyDoctor.png" style="float: left;" width="400"/>
</div>

##### Add Speciality
<img src="images/addSpec.png" width="500">

##### Add Room
<img src="images/addRoom.png" width="500">

##### Search Room
<div>
    <img src="images/searchRoom.png" style="float: left; margin-right: 10px;" width="500"/>
    <img src="images/modifyRoom.png" style="float: left;" width="500"/>
</div>

##### General View
<img src="images/generalView.png" width="700">

### Actor 2: Recepcionist

#### Menu
<img src="images/recepView.png" width="500">

- **Description**: 

#### Add Patient
<img src="images/addPatient.png" width="500">

- **Description**:

#### Search and Admit Patient
<div>
    <img src="images/searchPatient.png" style="float: left; margin-right: 10px;" width="500"/>
    <img src="images/admitPatient.png" style="float: left;" width="500"/>
</div>

- **Description**: 

### Actor 3: Triage Nurse

##### Select triage
<img src="images/selectTriage.png" width="500">

- **Description**: 

#### Nurse View
<img src="images/nurseView.png" width="500">

- **Description**: 

#### Patient Form
<div>
    <img src="images/nursePatForm1.png" style="float: left; margin-right: 10px;" width="500"/>
    <img src="images/nursePatForm2.png" style="float: left;" width="500"/>
</div>

- **Description**: 

### Actor 4: Doctor

#### Doctor View
<img src="images/doctorView.png" width="500">

- **Description**:

#### Patient Form
<div>
    <img src="images/docPatForm1.png" style="float: left; margin-right: 10px;" width="300"/>
    <img src="images/docPatForm2.png" style="float: left;" width="300"/>
    <img src="images/docPatForm3.png" style="float: left;" width="300"/>
</div>

- **Description**: 


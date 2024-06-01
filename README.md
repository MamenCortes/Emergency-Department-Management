# Emergency Department Management
## Project User Manual
An emergency department management software developed as a group proyect for the Database University Course.

## Table of Contents
1. [How to Download and Run the Project](#how-to-download-and-run-the-project)
2. [Initial Conditions of the Program](#initial-conditions-of-the-program)
3. [User Interface Views](#user-interface-views)
    - [Actor 1: Admin](#actor-1-admin)
    - [Actor 2: Manager](#actor-2-manager)
    - [Actor 3: Employee](#actor-3-employee)
    - [Actor 4: Guest](#actor-4-guest)

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


### Actor 1: Admin

#### Dashboard
![Admin Dashboard](path/to/admin_dashboard_image.png)

- **Description**: The Admin Dashboard provides an overview of system metrics, user activities, and system health.

#### User Management
![User Management](path/to/user_management_image.png)

- **Description**: The User Management view allows the Admin to add, edit, and remove users, as well as assign roles.

### Actor 2: Manager

#### Dashboard
![Manager Dashboard](path/to/manager_dashboard_image.png)

- **Description**: The Manager Dashboard displays project statuses, team performance, and pending approvals.

#### Team Management
![Team Management](path/to/team_management_image.png)

- **Description**: The Team Management view enables the Manager to assign tasks, monitor team progress, and set deadlines.

### Actor 3: Employee

#### Task List
![Employee Task List](path/to/employee_task_list_image.png)

- **Description**: The Task List shows the employee their assigned tasks, deadlines, and progress tracking.

#### Time Tracking
![Time Tracking](path/to/time_tracking_image.png)

- **Description**: The Time Tracking view allows employees to log their work hours and view their time reports.

### Actor 4: Guest

#### Home Page
![Guest Home Page](path/to/guest_home_page_image.png)

- **Description**: The Home Page provides guests with general information about the project and allows them to request access or contact support.

#### Contact Form
![Contact Form](path/to/contact_form_image.png)

- **Description**: The Contact Form enables guests to send inquiries or support requests directly to the team.

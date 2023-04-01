# SCHOOL MANAGEMENT SYSTEM

This is a school management API for students, teachers to manage courses, grades, GPA, and other operations happening within a school management system. 

Students can enroll to courses or unenroll from them, view their grades, course calendar, internships, GPA, semester and total credits. Their semester credits will be updated automatically after their enroll to course and if they become successful, their total credits will be updated too.

Teachers can create, update and delete courses, they can grade students' midterm, finalExam and their final grade letter of course. 

Also secretaries are able to manage only internship operations.

This API contains role-based authorization, so every user doesn't have all authorities, please check API for authorization details.

This API isn't developed semester-based, so think of it as only one semester for every operation you will try in API.



# Dependencies and Tools 

- Express
- Sequelize ORM
- Postgres
- Jsonwebtoken
- Bcrypt
- Dotenv


# Prerequisites

Before running API, please run npm install on your terminal to install required dependencies and modules and also create an .env file to store your database informations.




# Database Design

<img width="801" alt="Screenshot 2023-04-01 at 19 16 48" src="https://user-images.githubusercontent.com/73110402/229302326-cc079620-9155-42bf-95d4-63e2a2f2e5fc.png">




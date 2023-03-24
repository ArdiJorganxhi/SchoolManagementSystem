let departments = ["COMPUTER ENGINEERING", "ELECTRIC-ELECTRONIC ENGINEERING", "CHEMICAL ENGINEERING", "CIVIL ENGINEERING",
                   "MECHANICAL ENGINEERING", "MATERIAL SCIENCE AND ENGINEERING", "INDUSTRIAL ENGINEERING"]
let internshipStatusArray = ["2 COMPULSORY INTERNSHIPS", "1 COMPULSORY INTERNSHIP", "NO INTERNSHIPS REQUIRED"]

module.exports = (sequelize, Sequelize) => {
  const Student = sequelize.define("students", {
    name: {
      type: Sequelize.STRING,
    },
    surname: {
      type: Sequelize.STRING,
    },
    email: {
      type: Sequelize.STRING,
    },
    password: {
      type: Sequelize.STRING,
    },
    department: {
      type: Sequelize.ENUM(departments),
    },
    totalCredits: {
      type: Sequelize.FLOAT,
      defaultValue: 0.0,
    },
    internshipStatus: {
      type: Sequelize.ENUM(internshipStatusArray),
      defaultValue: internshipStatusArray[0] 
    },
    semesterCredits: {
      type: Sequelize.FLOAT,
      defaultValue: 0.0
    },
    gpa: {
      type: Sequelize.FLOAT,
      defaultValue: 0.0,
    },
  });

  return Student;
};

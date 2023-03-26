const departments = require("../common/enums/departments.enum");
const internshipStatusArray = require("../common/enums/internship.status.enum");


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

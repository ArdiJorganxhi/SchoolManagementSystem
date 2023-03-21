const db = require('../config/sequelize.config.js');

module.exports = (sequelize, Sequelize) => {
    const StudentCourse = sequelize.define("students_courses", {
     
        midterm: {
            type: Sequelize.FLOAT,
            defaultValue: 0.0
        },
        finalExam: {
            type: Sequelize.FLOAT,
            defaultValue: 0.0
        },
        finalGrade: {
            type: Sequelize.FLOAT,
            defaultValue: 0.0
        },
        finalGradeLetter: {
            type: Sequelize.ENUM("AA", "AB", "BA", "BB", "BC", "CB", "CC", "CD", "DC", "DD", "FF")
        }
        

    });
  
    return StudentCourse;
  };
  
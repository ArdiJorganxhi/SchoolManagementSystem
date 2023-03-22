const { letters } = require('../common/grade.letters.js');
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
            defaultValue: (this.midterm + this.finalExam) / 2
        },
        finalGradeLetter: {
            type: Sequelize.ENUM("AA", "AB", "BA", "BB", "BC", "CB", "CC", "CD", "DC", "DD", "FF"),
            defaultValue: letters((this.midterm + this.finalExam) / 2)
        }
        

    });
  
    return StudentCourse;
  };
  
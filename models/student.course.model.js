const gradeLetters = require('../common/enums/grade.letters.enum.js');
const { letters } = require('../common/grade.letters.js');
const db = require('../config/sequelize.config.js');
const Student = db.students;
const Course = db.courses;

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
            type: Sequelize.VIRTUAL,
            get(){
                return (this.getDataValue('midterm') + this.getDataValue('finalExam')) / 2
            }
            
        },
        finalGradeLetter: {
            type: Sequelize.ENUM(gradeLetters)
        }
        

    }, {
        hooks: {
            afterDestroy: (async (instance) => {
                const student = await Student.findOne({
                    where: {
                        id: instance.student_id
                    }
                })
                const course = await Course.findOne({
                    where: {
                        id: instance.course_id
                    }
                })
                await student.decrement('semesterCredits', {by: course.credits})

            })
        }
    });
  
    return StudentCourse;
  };
  
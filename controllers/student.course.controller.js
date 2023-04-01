const gradeletterswithcredits = require("../common/calculate.gpa");
const coursePassed = require("../common/course.passed.validation");
const { letters } = require("../common/grade.letters");
const { sequelize } = require("../config/sequelize.config");
const db = require("../config/sequelize.config");
const Student = db.students;
const Course = db.courses;
const StudentCourse = db.studentcourses;
const GradeLetters = db.gradeletters;
const Op = db.Sequelize.Op;

const gradeStudent = async function (req, res) {
  let { courseId } = req.params;
  let { midterm, finalExam } = req.body;

  const studentCourse = await StudentCourse.findAll({
    attributes: ["student_id"],
    where: {
      course_id: courseId,
    },
    raw: true,
  });

  const course = await Course.findOne({
    where: {
      id: courseId,
      teacherId: req.user.id,
    },
  });

  if (!course) {
    return res.status(400).send({ message: "Course not found!" });
  }

  let midtermGrades = [midterm];
  let finalGrades = [finalExam];

  let studentsAndGrades = [];

  for (var i = 0; i < studentCourse.length; i++) {
    studentsAndGrades.push({
      id: studentCourse[i].student_id,
      midterm: midtermGrades[0][i],
      finalExam: finalGrades[0][i],
    });
  }

  for (var i = 0; i < studentsAndGrades.length; i++) {
    await StudentCourse.update(
      {
        midterm: studentsAndGrades[i].midterm,
        finalExam: studentsAndGrades[i].finalExam,
      },
      {
        where: {
          student_id: studentsAndGrades[i].id,
          course_id: courseId,
        },
      }
    );
  }

  return res.status(200).send({ message: "Students are graded!" });
};

const insertGradeLetters = async function (req, res) {
  let { courseId } = req.params;

  let studentCourse = await StudentCourse.findAll({
    attributes: ["student_id", "midterm", "finalExam", "finalGradeLetter"],
    where: {
      course_id: courseId,
    },
    raw: true,
  });

  let course = await Course.findOne({
    attributes: ['credits'],
    where: {
      id: courseId
    }
  })

  let grades = await GradeLetters.findOne({
    attributes: [
      "AA",
      "AB",
      "BA",
      "BB",
      "BC",
      "CB",
      "CC",
      "CD",
      "DC",
      "DD",
      "FF",
    ],
    where: {
      course_id: courseId,
    },
    raw: true,
  });
  if (!grades) {
    return res.status(400).send({ message: "This course doesnt have letters" });
  }

  if (!studentCourse) {
    return res
      .status(400)
      .send({ message: "Student isn't enrolled in this course!" });
  }

  const studentsGrades = [];

  for (var i = 0; i < studentCourse.length; i++) {
    studentsGrades.push({
      id: studentCourse[i].student_id,
      midterm: studentCourse[i].midterm,
      finalExam: studentCourse[i].finalExam,
      finalLetter: studentCourse[i].finalGradeLetter
    });
  }

  for (var i = 0; i < studentsGrades.length; i++) {
    await StudentCourse.update(
      {
        finalGradeLetter: letters(grades, studentsGrades[i].midterm, studentsGrades[i].finalExam),
      },
      {
        where: {
          student_id: studentsGrades[i].id,
          course_id: courseId,
        },
      }
    );

    await Student.update({
      totalCredits: coursePassed(studentsGrades[i].finalGradeLetter, course[i].credits)
    }, {
      where: {
        id: studentsGrades[i].id
      }
    })
  }

  return res.status(200).send();
};

const calculateGpa = async function(req, res){
  let students = await Student.findAll(
    {
      attributes: ['id', [sequelize.literal('courses.credits'), 'credits']],
      include: [{
        model: Course,
        as: 'courses',
        attributes: [],
        through: {
          model: StudentCourse,
          as: 'students_courses',
          attributes: []
        }
      }]
    }
  );

  

  let studentsInfo = []
  let totalCredits = 0
  let studentCredits = 0

  for(var i = 0; i < students.length; i++){
    let gradeLetters = await StudentCourse.findAll({
      attributes: ['finalGradeLetter'],
      where: {
        student_id: students[i].id
      }
    }) 

      studentsInfo.push({
        id: students[i].id,
        credits: students[i].credits,
        grade: gradeLetters[i].finalGradeLetter
      })
  }

  for(var i = 0; i < studentsInfo.length; i++){
      totalCredits += studentsInfo[i].credits
      studentCredits += gradeletterswithcredits(studentsInfo[i].grade, studentsInfo[i].credits)
      await Student.update(
        {
          gpa: studentCredits / totalCredits
        },
        {
          where: {
            id: studentsInfo[i].id,
          },
        }
      );
  }

 

  return res.status(200).send()
}

module.exports = { gradeStudent, insertGradeLetters, calculateGpa };

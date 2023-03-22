const { letters } = require("../common/grade.letters");
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
    attributes: ["student_id", "finalGrade"],
    where: {
      course_id: courseId,
    },
    raw: true,
  });

  let grades = await GradeLetters.findOne({
    attributes: ["AA", "AB", "BA", "BB", "BC", "CB", "CC", "CD", "DC", "DD", "FF"],
    where: {
        course_id: 1
    },
    raw: true
  })
  console.log("GRAAAAADESSSSSS:" +  grades.AA)
  if(!grades){
    return res.status(400).send({message: "This course doesnt have letters"})
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
      grade: studentCourse[i].finalGrade,
    });
  }

  for (var i = 0; i < studentsGrades.length; i++) {
    await StudentCourse.update(
      {
        finalGradeLetter: letters(grades, studentsGrades[i].grade),
      },
      {
        where: {
          student_id: studentsGrades[i].id,
          course_id: courseId,
        },
      }
    );
  }

  return res.status(200).send();
};

module.exports = { gradeStudent, insertGradeLetters };

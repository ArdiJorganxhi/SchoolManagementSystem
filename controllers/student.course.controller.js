const { letters } = require("../common/grade.letters");
const db = require("../config/sequelize.config");
const Student = db.students;
const Course = db.courses;
const StudentCourse = db.studentcourses;
const Op = db.Sequelize.Op;

const gradeStudent = async function (req, res) {
  let { studentId, courseId } = req.params;
  let { midterm, finalExam } = req.body;

  const student = await Student.findOne({
    where: {
      id: studentId,
    },
  });

  if (!student) {
    return res.status(400).send({ message: "Student not found!" });
  }

  const course = await Course.findOne({
    where: {
      id: courseId,
      teacherId: req.user.id,
    },
  });

  if (!course) {
    return res.status(400).send({ message: "Course not found!" });
  }

  await StudentCourse.update(
    {
      midterm: midterm,
      finalExam: finalExam,
      finalGrade: (midterm + finalExam) / 2,
    },
    {
      where: {
        student_id: studentId,
        course_id: courseId,
      },
    }
  );

  return res.status(200).send({ message: "Student is graded!" });
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
        finalGradeLetter: letters(studentsGrades[i].grade),
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

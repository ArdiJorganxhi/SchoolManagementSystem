const { sequelize } = require("../config/sequelize.config");
const db = require("../config/sequelize.config");
const Student = db.students;
const Course = db.courses;
const StudentCourse = db.studentcourses;
const Internship = db.internships;

const findAllStudents = async function (req, res) {
  let students = await Student.findAll({
    attributes: ["id", "name", "surname"],
    where: {
      role: "STUDENT",
    },
    raw: true,
  });

  return res.status(200).send(students);
};

const findStudent = async function (req, res) {
  let student = await Student.findOne({
    attributes: [
      "id",
      "name",
      "surname",
      "department",
      "totalCredits",
      "semesterCredits",
      "gpa"
    ],
    where: {
      id: req.user.id,
    },
    include: [
      {
        model: Course,
        as: "courses",
        attributes: ["name", "credits"],
        through: {
          attributes: [
            "midterm",
            "finalExam",
            "finalGrade",
            "finalGradeLetter",
          ],
        },
      },
      {
        model: Internship,
        as: "internships",
        attributes: ["companyName", "startDate", "endDate"],
      },
    ],
  });
  return res.status(200).send(student);
};

const deleteStudent = async function (req, res) {
  let { id } = req.params;

  await Student.destroy({
    where: {
      id: id,
    },
  });

  return res.status(200).send({ message: "Student is deleted successfully!" });
};

const enrollToCourse = async function (req, res) {
  let { courseId } = req.params;

  const student = await Student.findOne({
    where: {
      id: req.user.id,
    },
  });
  if (!student) {
    return res.status(400).send({ message: "Student not found!" });
  }

  const studentCourses = await Student.findAll({
    attributes: [
      [sequelize.literal("courses.lectureday"), "lectureday"],
      [sequelize.literal("courses.lecturestart"), "lecturestart"],
    ],
    where: {
      id: req.user.id,
    },
    include: {
      model: Course,
      as: "courses",
      attributes: [],
      through: {
        attributes: [],
      },
    },
    raw: true,
  });

  const course = await Course.findOne({
    where: {
      id: courseId,
    },
    raw: true,
  });
  if (!course) {
    return res.status(400).send({ message: "Course not found!" });
  }

 

  const studentCourse = await StudentCourse.create({
    student_id: req.user.id,
    course_id: courseId,
  });
  if(studentCourse){
    await student.increment('semesterCredits', {by: course.credits})
  }

  

  return res.status(200).send("Student is enrolled!");
};

const unenrollFromCourse = async function (req, res) {
  let { courseId } = req.params;

  const student = await Student.findOne({
    where: {
      id: req.user.id
    }
  })

  const course = await Course.findOne({
    where: {
      id: courseId
    }
  })
 const studentCourse = await StudentCourse.destroy({
    where: {
      course_id: courseId,
      student_id: req.user.id,
    },
  });
  if(studentCourse){
    await student.decrement('semesterCredits', {by: course.credits})
  }
 
  return res
    .status(200)
    .send({ message: "Student is unenrolled from course!" });
};

const getSchedule = async function (req, res) {
  const schedule = await Student.findAll({
    include: [
      {
        model: Course,
        as: "courses",
        attributes: ["name", "lectureday", "lecturestart", "lecturefinish"],
        through: {
          attributes: [],
        },
      },
    ],
    where: {
      id: req.user.id,
    },
    attributes: [],
  });
  return res.status(200).send(schedule);
};

module.exports = {
  findAllStudents,
  findStudent,
  deleteStudent,
  enrollToCourse,
  unenrollFromCourse,
  getSchedule,
};

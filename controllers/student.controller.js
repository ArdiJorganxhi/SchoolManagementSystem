const db = require("../config/sequelize.config");
const Student = db.students;
const Course = db.courses;
const StudentCourse = db.studentcourses;

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
    attributes: ["id", "name", "surname", "department", "totalCredits", "semesterCredits"],
    where: {
      id: req.user.id,
    },
    include: [{
        model: Course,
        as: 'courses',
        attributes: ['name', 'credits']
    }]
  });
  return res.status(200).send(student);
};

const deleteStudent = async function (req, res) {
  let { id } = req.params;

  const user = await Student.destroy({
    where: {
      id: id,
      role: "STUDENT",
    },
  });

  return res.status(200).send({ message: "Student is deleted successfully!" });
};

const enrollToCourse = async function (req, res) {
  let { courseId } = req.params;

  const student = await Student.findOne({
    where: {
        id: req.user.id,
    }
  });
  if(!student){
    return res.status(400).send({message: "Student not found!"})
  };

  const course = await Course.findOne({
    where: {
        id: courseId,
    }
  });
  if(!course){
    return res.status(400).send({message: "Course not found!"});
  }

  const studentCourse = await StudentCourse.create({
    student_id: req.user.id,
    course_id: courseId,

  });
    if(studentCourse){
        await Student.update({ semesterCredits: course.credits }, { where: { id: req.user.id } });
    }

  return res.status(200).send({message: "Student is enrolled to course!"})



};

module.exports = { findAllStudents, findStudent, deleteStudent, enrollToCourse};

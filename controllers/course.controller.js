const db = require("../config/sequelize.config.js");
const Course = db.courses;
const Student = db.students;

const createCourse = async function (req, res) {
  let { name, credits, midtermPercentage, finalExamPercentage } = req.body;

  let checkCourse = await Course.findOne({
    where: {
      name: name,
    },
  });
  if (checkCourse) {
    return res.status(400).send({ message: "Course already exists!" });
  }
  await Course.create({
    name: name,
    credits: credits,
    department: req.user.department,
    midtermPercentage: midtermPercentage,
    finalExamPercentage: finalExamPercentage,
    teacherId: req.user.id,
  });
  return res.status(200).send({ message: "Course is created!" });
};

const findCourse = async function (req, res) {
  let { id } = req.params;
  let course = await Course.findOne({
    where: {
      id: id,
    },
  });
  if (!course) {
    return res.status(400).send({ message: "Course doesn't exist!" });
  }
  return res.status(200).send(course);
};

const getStudents = async function (req, res) {
  let { courseId } = req.params;

  const students = await Course.findOne({
    attributes: [],
    where: {
      id: courseId,
    },
    include: {
      model: Student,
      attributes: ["name", "surname"],
    },
    raw: true,
  });

  return res.status(200).send(students);
};

const deleteCourse = async function (req, res) {
  let { id } = req.params;
  let course = await Course.destroy({
    where: {
      id: id,
    },
  });
  if (!course) {
    return res.status(400).send({ message: "Course doesn't exist!" });
  }
  return res.status(200).send({ message: "Course deleted successfully!" });
};

module.exports = { createCourse, findCourse, deleteCourse, getStudents };

const db = require("../config/sequelize.config.js");
const Course = db.courses;

const createCourse = async function (req, res) {
  let { name, credits, department } = req.body;

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
    department: department,
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

module.exports = { createCourse, findCourse, deleteCourse };

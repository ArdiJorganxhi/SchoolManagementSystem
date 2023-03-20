const db = require("../config/sequelize.config.js");
const Teacher = db.teachers;
const Course = db.courses;

const findAllTeachers = async function (req, res) {
  let teachers = await Teacher.findAll({
    attributes: ["id", "name", "surname"],
    where: {
      role: "TEACHER",
    },
    raw: true,
  });

  return res.status(200).send(teachers);
};

const findTeacher = async function (req, res) {
  let teacher = await Teacher.findOne({
    attributes: ["id", "name", "surname"],
    where: {
      id: req.user.id,
      role: "TEACHER",
    },
    include: [ {
        model: Course,
        as: 'courses',
        attributes: ["id", "name"]
    } ,
 
  ]});

  return res.status(200).send(teacher);
};

const deleteTeacher = async function (req, res) {
  let { id } = req.params;
  let teacher = await Teacher.destroy({
    where: {
      id: id,
      role: "TEACHER",
    },
  });

  return res.status(200).send({ message: "Teacher is deleted!" });
};

module.exports = { findAllTeachers, findTeacher, deleteTeacher };

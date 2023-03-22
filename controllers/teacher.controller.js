const { letters } = require("../common/grade.letters.js");
const db = require("../config/sequelize.config.js");
const Teacher = db.teachers;
const Course = db.courses;
const Student = db.students;
const StudentCourse = db.studentcourses;


const findAllTeachers = async function (req, res) {
  let teachers = await Teacher.findAll({
    attributes: ["id", "name", "surname"],
    raw: true,
  });

  return res.status(200).send(teachers);
};

const findTeacher = async function (req, res) {
  let teacher = await Teacher.findOne({
    attributes: ["id", "name", "surname"],
    where: {
      id: req.user.id,
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
    },
  });

  return res.status(200).send({ message: "Teacher is deleted!" });
};




module.exports = { findAllTeachers, findTeacher, deleteTeacher};

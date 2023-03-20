const db = require("../config/sequelize.config");
const Student = db.students;

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
    attributes: ["id", "name", "surname"],
    where: {
      id: req.user.id,
    },
    raw: true,
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

module.exports = { findAllStudents, findStudent, deleteStudent };

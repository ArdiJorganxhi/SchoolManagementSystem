const { internshipValidation } = require("../common/internship.validation.js");
const { sequelize } = require("../config/sequelize.config.js");
const db = require("../config/sequelize.config.js");
const Internship = db.internships;
const Student = db.students;

const insertInternship = async function (req, res) {
  let { studentId } = req.params;
  let { companyName, startDate, endDate, internshipType } = req.body;

  const student = await Student.findOne({
    attributes: ["department"],
    where: {
      id: studentId,
    },
  });

  if (!student) {
    return res.status(400).send({ message: "Student not found!" });
  }

  await Internship.create({
    student_id: studentId,
    companyName: companyName,
    startDate: startDate,
    endDate: endDate,
    internshipType: internshipType,
    department: student.department,
  });

  const internshipCount = await Internship.count({
    where: {
      student_id: studentId,
    },
  });

  internshipValidation(internshipCount, studentId);

  return res.status(200).send({ message: "Internship is inserted!" });
};

const getAllInternships = async function (req, res) {
  const internships = await Internship.findAll({
    attributes: [
      "companyName",
      "startDate",
      "endDate",
      [sequelize.literal("students.name"), "name"],
    ],
    include: {
      model: Student,
      as: "students",
      attributes: [],
    },
    raw: true,
  });

  return res.status(200).send(internships);
};

const getStudentInternships = async function (req, res) {
  const getInternships = await Internship.findAll({
    attributes: ["companyName", "startDate", "endDate"],
    where: {
      student_id: req.user.id,
    },
    raw: true,
  });
  return res.status(200).send(getInternships);
};

module.exports = { insertInternship, getAllInternships, getStudentInternships };

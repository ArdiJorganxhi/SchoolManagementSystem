const { internshipValidation } = require("../common/internship.validation.js");
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

module.exports = { insertInternship };

const db = require("../config/sequelize.config.js");
const Student = db.students;

const internshipValidation = async function (internship, studentId) {
  if (internship === 2) {
    await Student.update(
      { internshipStatus: "NO INTERNSHIP REQUIRED" },
      { where: { id: studentId } }
    );
  } else if (internship === 1) {
    await Student.update(
      { internshipStatus: "1 COMPULSORY INTERNSHIP" },
      { where: { id: studentId } }
    );
  } else {
    await Student.update(
      { internshipStatus: "2 COMPULSORY INTERNSHIPS" },
      { where: { id: studentId } }
    );
  }
};

module.exports = { internshipValidation };

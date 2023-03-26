const db = require("../config/sequelize.config.js");
const GradeLetters = db.gradeletters;

const letters = (grades, midterm, finalExam) => {
  let finalGrade = (midterm + finalExam) / 2
  if (finalGrade < grades.FF) {
    return "FF";
  }
  if (finalGrade > grades.FF - 1 && finalGrade < grades.DC - 1) {
    return "DD";
  }
  if (finalGrade > grades.DC - 1 && finalGrade < grades.CD - 1) {
    return "DC";
  }
  if (finalGrade > grades.CD - 1 && finalGrade < grades.CC - 1) {
    return "CD";
  }
  if (finalGrade > grades.CC - 1 && finalGrade < grades.CB - 1) {
    return "CC";
  }
  if (finalGrade > grades.CB - 1 && finalGrade < grades.BC - 1) {
    return "CB";
  }
  if (finalGrade > grades.BC - 1 && finalGrade < grades.BB - 1) {
    return "BC";
  }
  if (finalGrade > grades.BB - 1 && finalGrade < grades.BA - 1) {
    return "BB";
  }
  if (finalGrade > grades.BA - 1 && finalGrade < grades.AB - 1) {
    return "BA";
  }
  if (finalGrade > grades.AB - 1 && finalGrade < grades.AA - 1) {
    return "AB";
  }
  if (finalGrade > grades.AA - 1) {
    return "AA";
  }
};

module.exports = { letters };

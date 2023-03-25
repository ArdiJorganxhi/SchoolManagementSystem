let internshipTypes = ["COMPULSORY", "VOLUNTARY"];
let departments = [
  "COMPUTER ENGINEERING",
  "ELECTRIC-ELECTRONIC ENGINEERING",
  "CHEMICAL ENGINEERING",
  "CIVIL ENGINEERING",
  "MECHANICAL ENGINEERING",
  "MATERIAL SCIENCE AND ENGINEERING",
  "INDUSTRIAL ENGINEERING",
];

module.exports = (sequelize, Sequelize) => {
  const Internship = sequelize.define("internships", {
    companyName: {
      type: Sequelize.STRING,
    },
    startDate: {
      type: Sequelize.DATEONLY,
    },
    endDate: {
      type: Sequelize.DATEONLY,
    },
    internshipType: {
      type: Sequelize.ENUM(internshipTypes),
    },
    department: {
      type: Sequelize.ENUM(departments),
    },
  });

  return Internship;
};

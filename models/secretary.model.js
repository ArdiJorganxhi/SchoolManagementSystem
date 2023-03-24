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
  const Secretary = sequelize.define("secretaries", {
    name: {
      type: Sequelize.STRING,
    },
    surname: {
      type: Sequelize.STRING,
    },
    email: {
      type: Sequelize.STRING,
    },
    password: {
      type: Sequelize.STRING,
    },
    department: {
      type: Sequelize.ENUM(departments),
    }
  });

  return Secretary;
};

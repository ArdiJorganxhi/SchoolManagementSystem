const departments = require("../common/enums/departments.enum");

module.exports = (sequelize, Sequelize) => {
  const Teacher = sequelize.define("teachers", {
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
      type: Sequelize.ENUM(departments)
    },
  });

  return Teacher;
};

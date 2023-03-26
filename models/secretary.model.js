const departments = require("../common/enums/departments.enum");


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

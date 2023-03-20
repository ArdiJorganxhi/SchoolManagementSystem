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
    role: {
      type: Sequelize.ENUM("TEACHER", "STUDENT"),
      defaultValue: "TEACHER",
    },
    department: {
      type: Sequelize.STRING,
    },
  });

  return Teacher;
};

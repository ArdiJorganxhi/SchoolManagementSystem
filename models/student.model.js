module.exports = (sequelize, Sequelize) => {
  const Student = sequelize.define("students", {
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
      type: Sequelize.STRING,
      defaultValue: "STUDENT",
    },
    department: {
      type: Sequelize.STRING,
    },
    totalCredits: {
      type: Sequelize.INTEGER,
      defaultValue: 0,
    },
    gpa: {
      type: Sequelize.FLOAT,
      defaultValue: 0.0,
    },
  });

  return Student;
};

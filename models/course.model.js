module.exports = (sequelize, Sequelize) => {
  const Course = sequelize.define("courses", {
    name: {
      type: Sequelize.STRING,
    },
    credits: {
      type: Sequelize.FLOAT,
    },
    department: {
      type: Sequelize.STRING,
    },
    midtermPercentage: {
        type: Sequelize.INTEGER,
    },
    finalExamPercentage: {
        type: Sequelize.INTEGER
    }
  });

  return Course;
};

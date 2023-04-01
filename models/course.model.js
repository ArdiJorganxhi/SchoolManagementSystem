let days = [
  "Monday",
  "Tuesday",
  "Wednesday",
  "Thursday",
  "Friday",
  "Saturday",
  "Sunday",
];

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
    lectureday: {
      type: Sequelize.ENUM(days),
    },
    lecturestart: {
      type: Sequelize.TIME,
    },
    lecturefinish: {
      type: Sequelize.TIME,
    },
    
  });

  return Course;
};

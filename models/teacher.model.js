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
        type: Sequelize.STRING,
        defaultValue: "TEACHER"
      }
      
      
    });
  
    return Teacher;
  };
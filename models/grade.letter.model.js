
module.exports = (sequelize, Sequelize) => {
    const GradeLetters = sequelize.define("grades_letters", {
     
        AA: {
            type: Sequelize.FLOAT,
        },
        AB: {
            type: Sequelize.FLOAT,
        },
        BA: {
            type: Sequelize.FLOAT
        },
        BB: {
            type: Sequelize.FLOAT,
        },
        BC: {
            type: Sequelize.FLOAT
        },
        CB: {
            type: Sequelize.FLOAT,
        },
        CC: {
            type: Sequelize.FLOAT,
        },
        CD: {
            type: Sequelize.FLOAT
        },
        DC: {
            type: Sequelize.FLOAT
        },
        DD: {
            type: Sequelize.FLOAT
        },
        FF: {
            type: Sequelize.FLOAT
        }



    });
    return GradeLetters;
  };
  
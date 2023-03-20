const dbConfig = require("./db.config.js");

const Sequelize = require("sequelize");
const sequelize = new Sequelize(dbConfig.DB, dbConfig.USER, dbConfig.PASSWORD, {
  host: dbConfig.HOST,
  dialect: dbConfig.dialect,
  operatorsAliases: false,

  pool: {
    max: dbConfig.pool.max,
    min: dbConfig.pool.min,
    acquire: dbConfig.pool.acquire,
    idle: dbConfig.pool.idle,
  },
});

const db = {};

db.Sequelize = Sequelize;
db.sequelize = sequelize;

db.students = require("../models/student.model.js")(sequelize, Sequelize);
db.teachers = require("../models/teacher.model.js")(sequelize, Sequelize);
db.courses = require("../models/course.model.js")(sequelize, Sequelize);

db.students.belongsToMany(db.courses, {
  through: 'student_course',
  as: 'courses',
  foreignKey: 'student_id' 
});
db.courses.belongsToMany(db.students, {
  through: 'student_course',
  as: 'students',
  foreignKey: 'course_id'
})

db.teachers.hasMany(db.courses, {
  as: 'courses'
})


module.exports = db;

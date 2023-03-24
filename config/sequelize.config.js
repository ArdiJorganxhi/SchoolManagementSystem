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
db.studentcourses = require("../models/student.course.model.js")(sequelize, Sequelize);
db.gradeletters = require('../models/grade.letter.model.js')(sequelize, Sequelize);
db.internships = require('../models/internship.model.js')(sequelize, Sequelize)
db.secretaries = require('../models/secretary.model.js')(sequelize, Sequelize)

db.students.belongsToMany(db.courses, {
  through: db.studentcourses,
  as: 'courses',
  foreignKey: 'student_id' 
});
db.courses.belongsToMany(db.students, {
  through: db.studentcourses,
  as: 'students',
  foreignKey: 'course_id'
})

db.teachers.hasMany(db.courses, {
  as: 'courses'
})

db.courses.hasMany(db.gradeletters, {
  as: 'grades_letters',
  foreignKey: "course_id"
})

db.students.hasMany(db.internships, {
  foreignKey: 'student_id',
  as: 'internships',
})

db.internships.belongsTo(db.students, {
  foreignKey: "student_id",
  as: 'students',
})


module.exports = db;

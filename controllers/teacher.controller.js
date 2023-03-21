const db = require("../config/sequelize.config.js");
const Teacher = db.teachers;
const Course = db.courses;
const Student = db.students;
const StudentCourse = db.studentcourses;

const findAllTeachers = async function (req, res) {
  let teachers = await Teacher.findAll({
    attributes: ["id", "name", "surname"],
    where: {
      role: "TEACHER",
    },
    raw: true,
  });

  return res.status(200).send(teachers);
};

const findTeacher = async function (req, res) {
  let teacher = await Teacher.findOne({
    attributes: ["id", "name", "surname"],
    where: {
      id: req.user.id,
      role: "TEACHER",
    },
    include: [ {
        model: Course,
        as: 'courses',
        attributes: ["id", "name"]
    } ,
 
  ]});

  return res.status(200).send(teacher);
};

const deleteTeacher = async function (req, res) {
  let { id } = req.params;
  let teacher = await Teacher.destroy({
    where: {
      id: id,
      role: "TEACHER",
    },
  });

  return res.status(200).send({ message: "Teacher is deleted!" });
};

const gradeStudent = async function(req, res){
    let {studentId, courseId} = req.params;
    let {midterm, finalExam} = req.body;
    
    const student = await Student.findOne({
        where: {
            id: studentId
        }
    });

    if(!student){
        return res.status(400).send({message: "Student not found!"});
    }

    const course = await Course.findOne({
        where: {
            id: courseId
        }
    });

    if(!course){
        return res.status(400).send({message: "Course not found!"});
    }

    await StudentCourse.update({
        midterm: midterm,
        finalExam: finalExam,
        finalGrade: (midterm + finalExam) / 2
    }, {
        where: {
            student_id: studentId,
            course_id: courseId
        }
    });

    return res.status(200).send({message: "Student is graded!"})
}

module.exports = { findAllTeachers, findTeacher, deleteTeacher, gradeStudent };

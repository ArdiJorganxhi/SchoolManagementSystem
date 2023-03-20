const db = require('../config/sequelize.config');
const Student = db.students;


const findAllStudents = async function(req, res){

    let students = await Student.findAll({
        where: {
            role: "STUDENT"
        },
        raw: true
    })



    return res.status(200).send(students)
    
}

const findStudent = async function(req, res){

    let student = await Student.findOne({
        where: {
            id: req.user.id,
            role: "STUDENT"
        },
        raw: true
    });
    if(student.role != "STUDENT"){
        return res.status(400).send({message: "You are not authorized to view this user"});
    }
    return res.status(200).send({
        id: student.id,
        name: student.name,
        surname: student.surname
    })
}

const deleteStudent = async function(req, res){
    let {id} = req.params;

    const user = await Student.destroy({
        where: {
            id: id,
            role: "STUDENT"
        }
    });
  

    return res.status(200).send({message: "Student is deleted successfully!"});

}

module.exports = {findAllStudents, findStudent, deleteStudent}
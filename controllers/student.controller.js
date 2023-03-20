const db = require('../config/sequelize.config');
const User = db.users;


const findAllStudents = async function(req, res){

    let students = await User.findAll({
        where: {
            role: "STUDENT"
        },
        raw: true
    })



    return res.status(200).send(students)
    
}

const findStudent = async function(req, res){

    let user = await User.findOne({
        where: {
            id: req.user.id,
            role: "STUDENT"
        },
        raw: true
    });
    if(user.role != "STUDENT"){
        return res.status(400).send({message: "You are not authorized to view this user"});
    }
    return res.status(200).send({
        id: user.id,
        name: user.name,
        surname: user.surname
    })
}

const deleteStudent = async function(req, res){
    let {id} = req.params;

    const user = await User.destroy({
        where: {
            id: id,
            role: "STUDENT"
        }
    });
    if(user.role != "STUDENT"){
        return res.status(400).send({message: "You are not authorized to delete this user"});
    }

    return res.status(200).send({message: "Student is deleted successfully!"});

}

module.exports = {findAllStudents, findStudent, deleteStudent}
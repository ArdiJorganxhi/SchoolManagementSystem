const db = require('../config/sequelize.config.js');
const User = db.users;



const findAllTeachers = async function(req, res){

    let teachers = await User.findAll({
        where: {
            role: "TEACHER",        
        },
        raw: true
    });

    return res.status(200).send(teachers);


}


const findTeacher = async function(req, res){

    let teacher = await User.findOne({
        where: {
            id: req.user.id,
            role: "TEACHER"
        },
        raw: true
    });
    if(teacher.role != "TEACHER"){
        return res.status(400).send({message: "You are not authorized to view this user"});
    }
    return res.status(200).send({
        id: teacher.id,
        name: teacher.name,
        surname: teacher.surname
    })

}

const deleteTeacher = async function(req, res){
    let {id} = req.params;
    let teacher = await User.destroy({
        where: {
            id: id,
            role: "TEACHER",
        }
    })
    if(teacher.role != "TEACHER"){
        return res.status(400).send({message: "You are not authorized to delete this user"});
    }

    return res.status(200).send({message: "Teacher is deleted!"});
}

module.exports = {findAllTeachers, findTeacher, deleteTeacher}
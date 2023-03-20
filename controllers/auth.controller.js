require('dotenv').config();
const db = require('../config/sequelize.config')
const Student = db.students
const Teacher = db.teachers
const bcrypt = require('bcryptjs');
var salt = bcrypt.genSaltSync(10);
const jwt = require('jsonwebtoken');
const jwtSecret = process.env.JWT_SECRET;

const registerStudent = async function(req, res){
    let {name, surname, email, password, department} = req.body;

    var passwordHash = bcrypt.hashSync(password, salt);

    let checkStudent = await Student.findOne({
        where: {
            email: email
        }
    });
    if(checkStudent){
        return res.status(400).send({message: "Student already exists!"});
    }

    let student = Student.create({
        name: name,
        surname: surname,
        email: email,
        password: passwordHash,
        department: department
    });

    return res.status(200).send({message: "Student is registered!"});

}

const registerTeacher = async function(req, res){
    
    let {name, surname, email, password, department} = req.body;

    var passwordHash = bcrypt.hashSync(password, salt);

    let checkTeacher = await Teacher.findOne({
        where: {
            email: email
        }
    });
    if(checkTeacher){
        return res.status(400).send({message: "Teacher already exists!"});
    }

    let teacher = Teacher.create({
        name: name,
        surname: surname,
        email: email,
        password: passwordHash,
        department: department
    });

    return res.status(200).send({message: "Teacher is registered!"});

}

const loginStudent = async function(req, res){
    let {email, password} = req.body;

    let student = await Student.findOne({
        where: {
            email: email
        }
    });
    if(!student){
        return res.status(400).send({message: "User doesn't exist!"});
    }
    let passwordCheck = bcrypt.compareSync(password, student.password)

    if(!passwordCheck){
        return res.status(400).send({message: "Passwords do not match!"});
    }

    let token = jwt.sign({...student}, jwtSecret, {expiresIn: 60*60});

    return res.status(200).send(token);
}

const loginTeacher = async function(req, res){
    let {email, password} = req.body;

    let teacher = await Teacher.findOne({
        where: {
            email: email
        }
    });
    if(!teacher){
        return res.status(400).send({message: "User doesn't exist!"});
    }
    let passwordCheck = bcrypt.compareSync(password, teacher.password)

    if(!passwordCheck){
        return res.status(400).send({message: "Passwords do not match!"});
    }

    let token = jwt.sign({...teacher}, jwtSecret, {expiresIn: 60*60});

    return res.status(200).send(token);
}

module.exports = {registerStudent, registerTeacher, loginStudent, loginTeacher}
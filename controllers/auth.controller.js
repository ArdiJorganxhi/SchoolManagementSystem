require('dotenv').config();
const db = require('../config/sequelize.config')
const User = db.users
const bcrypt = require('bcryptjs');
var salt = bcrypt.genSaltSync(10);
const jwt = require('jsonwebtoken');
const jwtSecret = process.env.JWT_SECRET;

const registerStudent = async function(req, res){
    let {name, surname, email, password} = req.body;

    var passwordHash = bcrypt.hashSync(password, salt);

    let checkUser = await User.findOne({
        where: {
            email: email
        }
    });
    if(checkUser){
        return res.status(400).send({message: "Student already exists!"});
    }

    let user = User.create({
        name: name,
        surname: surname,
        email: email,
        password: passwordHash,
        role: "STUDENT"
    });

    return res.status(200).send({message: "Student is registered!"});

}

const registerTeacher = async function(req, res){
    
    let {name, surname, email, password} = req.body;

    var passwordHash = bcrypt.hashSync(password, salt);

    let checkUser = await User.findOne({
        where: {
            email: email
        }
    });
    if(checkUser){
        return res.status(400).send({message: "Teacher already exists!"});
    }

    let user = User.create({
        name: name,
        surname: surname,
        email: email,
        password: passwordHash,
        role: "TEACHER"
    });

    return res.status(200).send({message: "Teacher is registered!"});

}

const login = async function(req, res){
    let {email, password} = req.body;

    let user = await User.findOne({
        where: {
            email: email
        }
    });
    if(!user){
        return res.status(400).send({message: "User doesn't exist!"});
    }
    let passwordCheck = bcrypt.compareSync(password, user.password)

    if(!passwordCheck){
        return res.status(400).send({message: "Passwords do not match!"});
    }

    let token = jwt.sign({...user}, jwtSecret, {expiresIn: 60*60});

    return res.status(200).send(token);



}

module.exports = {registerStudent, registerTeacher, login}
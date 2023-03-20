const db = require('../config/sequelize.config');
const User = db.users;


exports.findAllStudents = async function(req, res){

    let students = await User.findAll({
        where: {
            role: "STUDENT"
        }
    });
    return res.status(200).send(students);
}
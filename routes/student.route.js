const {findAllStudents, findStudent, deleteStudent} = require('../controllers/student.controller.js')
const router = require('express').Router();
const {verifyStudent, verifyUsers, verifyTeacher} = require('../middleware/verify.token.js')
 

router.get('/list', verifyUsers, findAllStudents);
router.get('/', verifyStudent, findStudent);
router.delete('/:id', verifyTeacher, deleteStudent);



module.exports = router;
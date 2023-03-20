const {findAllTeachers, findTeacher, deleteTeacher} = require('../controllers/teacher.controller.js');
const {verifyUsers, verifyTeacher} = require('../middleware/verify.token.js')
const router = require('express').Router();


router.get('/list', verifyUsers, findAllTeachers)
router.get('/', verifyTeacher, findTeacher)
router.delete('/:id', verifyTeacher, deleteTeacher)



module.exports = router
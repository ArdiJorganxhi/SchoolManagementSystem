const {registerStudent, registerTeacher, login} = require('../controllers/auth.controller.js')
const router = require('express').Router();

router.post('/student/register', registerStudent);
router.post('/teacher/register', registerTeacher);
router.post('/login', login);


module.exports = router
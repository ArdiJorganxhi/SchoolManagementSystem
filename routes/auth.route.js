const authController = require('../controllers/auth.controller.js');
const router = require('express').Router();

router.post('/student/register', authController.registerStudent);
router.post('/teacher/register', authController.registerTeacher);
router.post('/login', authController.login);


module.exports = router
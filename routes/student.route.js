const userController = require('../controllers/student.controller.js');
const router = require('express').Router();
const authentication = require('../middleware/verify.token.js');


router.get('/', authentication.verifyLogin, userController.findAllStudents);


module.exports = router;
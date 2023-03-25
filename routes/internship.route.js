const { insertInternship, getAllInternships } = require('../controllers/internship.controller');
const { verifySecretary } = require('../middleware/verify.token');

const router = require('express').Router();


router.get('/', verifySecretary, getAllInternships)


module.exports = router
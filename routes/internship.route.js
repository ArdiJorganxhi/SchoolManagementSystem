const { insertInternship } = require('../controllers/internship.controller');
const { verifySecretary } = require('../middleware/verify.token');

const router = require('express').Router();


router.post('/', verifySecretary, insertInternship)


module.exports = router
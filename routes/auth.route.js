const {
  registerStudent,
  registerTeacher,
  loginStudent,
  loginTeacher,
  registerSecretary,
  loginSecretary,
} = require("../controllers/auth.controller.js");
const router = require("express").Router();

router.post("/student/register", registerStudent);
router.post("/teacher/register", registerTeacher);
router.post("/student/login", loginStudent);
router.post("/teacher/login", loginTeacher);
router.post('/secretary/register', registerSecretary);
router.post('/secretary/login', loginSecretary);

module.exports = router;

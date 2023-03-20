const {
  registerStudent,
  registerTeacher,
  loginStudent,
  loginTeacher,
} = require("../controllers/auth.controller.js");
const router = require("express").Router();

router.post("/student/register", registerStudent);
router.post("/teacher/register", registerTeacher);
router.post("/student/login", loginStudent);
router.post("/teacher/login", loginTeacher);

module.exports = router;

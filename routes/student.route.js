const {
  findAllStudents,
  findStudent,
  deleteStudent,
  enrollToCourse,
} = require("../controllers/student.controller.js");
const router = require("express").Router();
const {
  verifyStudent,
  verifyUsers,
  verifyTeacher,
} = require("../middleware/verify.token.js");

router.get("/list", verifyUsers, findAllStudents);
router.get("/", verifyStudent, findStudent);
router.delete("/:id", verifyTeacher, deleteStudent);
router.post("/courses/:courseId", verifyStudent, enrollToCourse);

module.exports = router;

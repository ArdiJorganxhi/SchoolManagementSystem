const { insertInternship, getStudentInternships } = require("../controllers/internship.controller.js");
const {
  findAllStudents,
  findStudent,
  deleteStudent,
  enrollToCourse,
  unenrollFromCourse,
  getSchedule,
} = require("../controllers/student.controller.js");
const { calculateGpa } = require("../controllers/student.course.controller.js");
const router = require("express").Router();
const {
  verifyStudent,
  verifyUsers,
  verifyTeacher,
  verifySecretary,
} = require("../middleware/verify.token.js");

router.get("/list", verifyUsers, findAllStudents);
router.get("/", verifyStudent, findStudent);
router.delete("/:id", verifyTeacher, deleteStudent);
router.post("/courses/:courseId", verifyStudent, enrollToCourse);
router.delete("/courses/:courseId", verifyStudent, unenrollFromCourse)
router.post('/:studentId/internship', verifySecretary, insertInternship)
router.get('/course-calendar', verifyStudent, getSchedule)
router.get('/internships', verifyStudent, getStudentInternships)
router.get('/gpa', calculateGpa)

module.exports = router;

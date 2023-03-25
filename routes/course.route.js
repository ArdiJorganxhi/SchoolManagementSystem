const {
  createCourse,
  findCourse,
  deleteCourse,
  getStudents,
} = require("../controllers/course.controller.js");
const { insertLetters } = require("../controllers/grade.letter.controller.js");
const {
  gradeStudent,
  insertGradeLetters,
} = require("../controllers/student.course.controller.js");
const { verifyTeacher } = require("../middleware/verify.token.js");
const router = require("express").Router();

router.post("/", verifyTeacher, createCourse);
router.get("/:id", verifyTeacher, findCourse);
router.delete("/:id", verifyTeacher, deleteCourse);
router.put("/:courseId/grades", verifyTeacher, gradeStudent);
router.put("/:courseId/grades/letters", verifyTeacher, insertGradeLetters);
router.post("/:courseId/grades/letters", verifyTeacher, insertLetters);
router.get("/:courseId/students", verifyTeacher, getStudents);

module.exports = router;

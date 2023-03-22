const {
  createCourse,
  findCourse,
  deleteCourse,
} = require("../controllers/course.controller.js");
const { gradeStudent, insertGradeLetters } = require("../controllers/student.course.controller.js");
const { verifyTeacher } = require("../middleware/verify.token.js");
const router = require("express").Router();

router.post("/", verifyTeacher, createCourse);
router.get("/:id", verifyTeacher, findCourse);
router.delete("/:id", verifyTeacher, deleteCourse);
router.put("/grade/:studentId/course/:courseId", verifyTeacher, gradeStudent);
router.put("/:courseId/grades/letters", insertGradeLetters)

module.exports = router;

const {
  createCourse,
  findCourse,
  deleteCourse,
} = require("../controllers/course.controller.js");
const { gradeStudent } = require("../controllers/teacher.controller.js");
const { verifyTeacher } = require("../middleware/verify.token.js");
const router = require("express").Router();

router.post("/", verifyTeacher, createCourse);
router.get("/:id", verifyTeacher, findCourse);
router.delete("/:id", verifyTeacher, deleteCourse);
router.post("/grade/:studentId/course/:courseId", verifyTeacher, gradeStudent);

module.exports = router;

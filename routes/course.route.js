const {
  createCourse,
  findCourse,
  deleteCourse,
} = require("../controllers/course.controller.js");
const { verifyTeacher } = require("../middleware/verify.token.js");
const router = require("express").Router();

router.post("/", verifyTeacher, createCourse);
router.get("/:id", verifyTeacher, findCourse);
router.delete("/:id", verifyTeacher, deleteCourse);

module.exports = router;

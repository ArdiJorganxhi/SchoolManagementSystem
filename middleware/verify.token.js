require("dotenv").config();
const jwt = require("jsonwebtoken");
const jwtSecret = process.env.JWT_SECRET;
const db = require("../config/sequelize.config.js");
const Student = db.students;
const Teacher = db.teachers;
const Secretary = db.secretaries;

const verifyUsers = (req, res, next) => {
  let header = req.header("Authorization");

  if (!header) {
    return res.status(401).send({ message: "Unauthorized!" });
  }

  let token = header.replace("Bearer ", "");

  jwt.verify(token, jwtSecret, function (err, decoded) {
    if (err) {
      return res.status(401).send({ message: "Unauthorized" });
    }
    req.user = decoded.dataValues;
    next();
  });
};

const verifyStudent = (req, res, next) => {
  verifyUsers(req, res, () => {
    const student = Student.findOne({
      where: {
        id: req.user.id,
      },
    });
    if (req.user.id === req.params.id || student != null) {
      next();
    } else {
      return res.status(403).send({ message: "You are not authorized!" });
    }
  });
};

const verifyTeacher = (req, res, next) => {
  verifyUsers(req, res, () => {
    const teacher = Teacher.findOne({
      where: {
        id: req.user.id,
      },
    });

    if (req.user.id === req.params.id || teacher != null) {
      next();
    } else {
      return res.status(403).send({ message: "You are not authorized!" });
    }
  });
};

const verifySecretary = (req, res, next) => {
  verifyUsers(req, res, () => {
    const secretary = Secretary.findOne({
      where: {
        id: req.user.id,
      },
    });
    if (req.user.id === req.params.id || secretary != null) {
      next();
    } else {
      return res
        .status(403)
        .send({ message: "You are not authorized or user doesn't exist!" });
    }
  });
};

module.exports = { verifyUsers, verifyStudent, verifyTeacher, verifySecretary };

require("dotenv").config();
const jwt = require("jsonwebtoken");
const jwtSecret = process.env.JWT_SECRET;

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
    if (req.user.id === req.params.id || req.user.role === "STUDENT") {
      next();
    } else {
      return res.status(403).send({ message: "You are not authorized!" });
    }
  });
};

const verifyTeacher = (req, res, next) => {
  verifyUsers(req, res, () => {
    if (req.user.id === req.params.id || req.user.role === "TEACHER") {
      next();
    } else {
      return res.status(403).send({ message: "You are not authorized!" });
    }
  });
};

module.exports = { verifyUsers, verifyStudent, verifyTeacher };

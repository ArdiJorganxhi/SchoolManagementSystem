require("dotenv").config();
const jwt = require("jsonwebtoken");
const jwtSecret = process.env.JWT_SECRET;

exports.verifyLogin = function(req, res, next) {
  let header = req.header("Authorization");

  if (!header) {
    return res.status(401).send({ message: "Unauthorized!" });
  }

  let token = header.replace("Bearer ", "");

  jwt.verify(token, jwtSecret, function (err, decoded) {
    if (err) {
      return res.status(401).send({ message: "Unauthorized" });
    }
    req.user = decoded;
    next();
  });
}


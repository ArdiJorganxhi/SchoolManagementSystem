const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");

const app = express();

var corsOptions = {
  origin: "http://localhost:8081",
};

app.use(cors(corsOptions));

app.use(express.json());

app.use(express.urlencoded({ extended: true }));

const db = require("./config/sequelize.config");
db.sequelize
  .sync()
  .then(() => {
    console.log("Synced db.");
  })
  
  .catch((err) => {
    console.log("Failed to sync db: " + err.message);
  });


const authRouter = require("./routes/auth.route.js");
const studentRouter = require("./routes/student.route.js");
const teacherRouter = require("./routes/teacher.route.js");
const courseRouter = require('./routes/course.route.js');
const internshipRouter = require('./routes/internship.route.js')


app.use("/api/auth", authRouter);
app.use("/api/students", studentRouter);
app.use("/api/teachers", teacherRouter);
app.use("/api/courses", courseRouter);
app.use('/api/internships', internshipRouter)

app.get("/", (req, res) => {
  res.json({ message: "Welcome." });
});

// set port, listen for requests
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});

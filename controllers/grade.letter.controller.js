const db = require("../config/sequelize.config.js");
const Course = db.courses;
const GradeLetters = db.gradeletters;


const insertLetters = async function (req, res) {
  let { AA, AB, BA, BB, BC, CB, CC, CD, DC, DD, FF } = req.body;
  let { courseId } = req.params;

 


  const course = Course.findOne({
    where: {
        id: courseId
    }
  });
  if(!course){
    return res.status(400).send({message: "Course not found!"});
  }

  await GradeLetters.create({
    AA: AA,
    AB: AB,
    BA: BA,
    BB: BB,
    BC: BC,
    CB: CB,
    CC: CC,
    CD: CD,
    DC: DC,
    DD: DD,
    FF: FF,
    course_id: courseId
  })
  return res.status(200).send({message: 'Letters are inserted!'})
};

module.exports = {insertLetters}

const coursePassed = (finalGrade, credits) => {
  let totalCredits = 0;
  if (finalGrade === "FF") {
    return totalCredits;
  } else {
    totalCredits += credits;
  }
  return totalCredits
};

module.exports = coursePassed
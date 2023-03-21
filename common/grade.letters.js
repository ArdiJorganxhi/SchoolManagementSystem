const letters = (finalGrade) => {

    if(finalGrade < 20){
        return "FF";
    }
    if(finalGrade > 20 && finalGrade < 24){
        return "DD";
    }
    if(finalGrade > 24 && finalGrade < 29){
        return "DC";
    }
    if(finalGrade > 29 && finalGrade < 39){
        return "CD";
    }
    if(finalGrade > 39 && finalGrade < 49){
        return "CC";
    }
    if(finalGrade > 49 && finalGrade < 57){
        return "CB";
    }
    if(finalGrade > 57 && finalGrade < 64){
        return "BC";
    }
    if(finalGrade > 64 && finalGrade < 72){
        return "BB";
    }
    if(finalGrade > 72 && finalGrade < 79){
        return "BA";
    }
    if(finalGrade > 79 && finalGrade < 86){
        return "AB";
    }
    if(finalGrade > 86){
        return "AA";
    }
}

module.exports = {letters}
const gradeletterswithcredits = (finalGrade, credits) => {

    let result = 0
    switch(finalGrade){
        case "AA":
            result += 4.00 * credits;
            break;
        case "AB":
            result += 3.70 * credits;
            break;
        case "BA":
            result += 3.30 * credits;
            break;
        case "BB":
            result += 3.00 * credits;
            break;
        case "BC":
            result += 2.70 * credits;
            break;
        case "CB":
            result += 2.30 * credits
            break;
        case "CC":
            result += 2.00 * credits;
            break;
        case "CD":
            result += 1.70 * credits;
            break;
        case "DC":
            result += 1.30 * credits;
            break;
        case "DD":
            result += 1.00 * credits;
            break;
        
    }  
    
    return result
}

module.exports = gradeletterswithcredits
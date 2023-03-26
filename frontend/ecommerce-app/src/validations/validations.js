export function textValidation(value) {      
    if (!value) return 'This field is required'
    const regex = /^[a-z ,.'-]+$/i
    if (!regex.test((String(value)))) {
      return 'Please enter a valid name'
    }
    return true
}

export function passwordValidation(value) {
    if(!value) {
      return "This field is required.";
    }
    
    const isWhitespace = /^(?=.*\s)/;
    if (isWhitespace.test(value)) {
      return "Password must not contain whitespaces.";
    }

    const isContainsUppercase = /^(?=.*[A-Z])/;
    if (!isContainsUppercase.test(value)) {
      return "Password must have at least one uppercase character.";
    }

    const isContainsLowercase = /^(?=.*[a-z])/;
    if (!isContainsLowercase.test(value)) {
      return "Password must have at least one lowercase character.";
    }

    const isContainsNumber = /^(?=.*[0-9])/;
    if (!isContainsNumber.test(value)) {
      return "Password must contain at least one digit.";
    }

    const isContainsSymbol = /^(?=.*[~`!@#$%^&*()--+={}[\]|\\:;"'<>,.?/_₹])/;
    if (!isContainsSymbol.test(value)) {
      return "Password must contain at least one special character.";
    }

    const isValidLength = /^.{8,16}$/;
    if (!isValidLength.test(value)) {
      return "Password must be 8-16 characters long.";
    }

    return true
}

export function emailValidation(value) {
    if (!value) return 'This field is required'

    const regex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    if (!regex.test(String(value).toLowerCase())) return 'Please enter a valid email address'

    return true
}
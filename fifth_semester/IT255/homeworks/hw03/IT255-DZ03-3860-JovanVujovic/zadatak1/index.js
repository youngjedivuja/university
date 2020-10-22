function changeColor(){
    document.body.style.background = "teal";
    document.getElementById("emailId").style.color ="red";
    document.getElementById("passwordId").style.color ="red";
    document.getElementById("nameId").style.color ="red";
    document.getElementById("surnameId").style.color ="red";

    document.getElementById("finalText").value = document.getElementById("emailId").value + " "
    + document.getElementById("passwordId").value + " "
    + document.getElementById("nameId").value + " "
    + document.getElementById("surnameId").value;
}


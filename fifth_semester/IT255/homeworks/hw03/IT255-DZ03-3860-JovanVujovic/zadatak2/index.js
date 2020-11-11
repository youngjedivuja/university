function sum() {
    let z = document.getElementById("firstNum").value;
    let y = document.getElementById("secondNum").value;
    document.getElementById("result").value = +z + +y;
}

function times() {
    document.getElementById("result").value =
        document.getElementById("firstNum").value * document.getElementById("secondNum").value;
}

function divide() {
    document.getElementById("result").value =
        document.getElementById("firstNum").value / document.getElementById("secondNum").value;
}

function subtract() {
    let z = document.getElementById("firstNum").value;
    let y = document.getElementById("secondNum").value;
    document.getElementById("result").value = +z - +y;
}

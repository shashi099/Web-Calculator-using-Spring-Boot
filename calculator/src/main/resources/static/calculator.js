document.addEventListener("DOMContentLoaded", function () {

    const form = document.querySelector("form");

    const firstNumber =
        document.getElementById("firstNumber");

    const secondNumber =
        document.getElementById("secondNumber");

    const operator =
        document.getElementById("operator");


    form.addEventListener("submit", function (event) {

        const first = parseFloat(firstNumber.value);
        const second = parseFloat(secondNumber.value);
        const operation = operator.value;


        if (operation === "/" && second === 0) {

            event.preventDefault();

            alert("Cannot divide by zero.");

            return;
        }


        if (operation === "%" && second === 0) {

            event.preventDefault();

            alert("Cannot calculate modulus with zero.");

            return;
        }


        console.log("Sending calculation to Spring Boot...");

    });

});
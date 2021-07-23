document.addEventListener("DOMContentLoaded", function () {
    const buttons = document.querySelectorAll(".cssWebSpy");
    const confirm = document.getElementById("confirmWebSpy");
    for (let i = 0; i < buttons.length; i++) {
        buttons[i].addEventListener("click", function (event) {
            confirm.setAttribute("href", this.innerText);
            const css = document.querySelectorAll("." + this.innerText);
            for (let j = 0; j < css.length; j++) {
                css[j].style.backgroundColor = "red";
            }
        });
    }
});
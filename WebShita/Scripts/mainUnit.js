/*
to acheive understanding of making this tool
i decided to put every comments possible if one
of the contributors want to not just understand
but also enhance the functionality of this tool

    thank you
                                BELDAD-Ace

*/
//BELDAD-Ace on Github
//this is the main jScript for the WebShita
//it will handle the main unit of the web app

//for greeting page before the user sign in/up

//added functionality to the buttons
//uses css properties to show and hide the following containers:
// the .signIn-Container and .signUp-Container
// and the .greeting-open-pag
//the default display for the greeting page is block
document.getElementById("SignIn").addEventListener("click", function () {
    document.querySelector(".signIn-Container").style.display = "block";
    document.querySelector(".signUp-Container").style.display = "none";
    document.querySelector(".greeting-open-pag").style.display = "none";
});
document.getElementById("SignUp").addEventListener("click", function () {
    document.querySelector(".signUp-Container").style.display = "block";
    document.querySelector(".signIn-Container").style.display = "none";
    document.querySelector(".greeting-open-pag").style.display = "none";
});//end of greeting page handler

//already have an account button
document.getElementById("log-link").addEventListener("click", function () {
    document.querySelector(".signIn-Container").style.display = "block";
    document.querySelector(".signUp-Container").style.display = "none";
    document.querySelector(".greeting-open-pag").style.display = "none";

});//life so miserable without a kiss/lovey dovey message
//i need motivation xD 


//don't have an account button
document.getElementById("sign-link").addEventListener("click", function () {
    document.querySelector(".signIn-Container").style.display = "none";
    document.querySelector(".signUp-Container").style.display = "block";
    document.querySelector(".greeting-open-pag").style.display = "none";

});


        //type shit  for sign in (when clicking the shit out of sign in)
document.getElementById("button-login").addEventListener("submit", async (e) => {
  e.preventDefault();

  const email = document.getElementById("signinEmail").value;
  const password = document.getElementById("signinPassword").value;

  const response = await fetch("/webshita/signin", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`
  });

  const data = await response.json();
  const msgEl = document.getElementById("error-message");

  if (data.success) {
    msgEl.textContent = data.message;
    msgEl.style.color = "green";

    // Redirect based on role
    if (data.role === "admin") window.location.href = "/webshita/pages/admin-dashboard.html";
    else window.location.href = "/webshita/pages/user-dashboard.html";
  } else {
    msgEl.textContent = data.message;
    msgEl.style.color = "red";
  }
});






/*
functionalities for sign up*
This is impossible without backend and i need urgent help
from contributors who know backend development
so if you are one please help me out
*/
    //type shit  for sign up (when clicking the shit out of sign up)
document.getElementById("button-signup").addEventListener("submit", async (e) => {
  e.preventDefault();

  const name = document.getElementById("name").value;
  const email = document.getElementById("signupEmail").value;
  const password = document.getElementById("signupPassword").value;

  const response = await fetch("/webshita/signup", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `email=${encodeURIComponent(email)}&password=${encodeURIComponent(password)}`
  });

  const data = await response.json();
  const msgEl = document.getElementById("error-message");
  msgEl.textContent = data.message;
  msgEl.style.color = data.success ? "green" : "red";
});
//// 

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

/*
functionalities for sign in*
This is impossible without backend and i need urgent help
from contributors who know backend development
so if you are one please help me out
*/
//don't have an account button
document.getElementById("sign-link").addEventListener("click", function () {
    document.querySelector(".signIn-Container").style.display = "none";
    document.querySelector(".signUp-Container").style.display = "block";
    document.querySelector(".greeting-open-pag").style.display = "none";

});


/*
functionalities for sign up*
This is impossible without backend and i need urgent help
from contributors who know backend development
so if you are one please help me out
*/

//already have an account button
document.getElementById("log-link").addEventListener("click", function () {
    document.querySelector(".signIn-Container").style.display = "block";
    document.querySelector(".signUp-Container").style.display = "none";
    document.querySelector(".greeting-open-pag").style.display = "none";

});//life so miserable without a kiss/lovey dovey message
//i need motivation xD 

    //type shit  for sign up (when clicking the shit out of sign up)
function Signup(){
    //calling the element by identification (id
    const name = document.getElementById("name").value ;      
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const confPas = document.getElementById("confirmPassword").value;
    
    if (!name || !email || !password || !confPas){//if any of this is not filled
        document.getElementById("error-message").textContent = "Please fill up the form!";

    }
    if (password !== confPas) {//if the password and matching is not equal
     document.getElementById("error-message").textContent = "the password does not match";
        event.preventDefault();
    }
}
//// 

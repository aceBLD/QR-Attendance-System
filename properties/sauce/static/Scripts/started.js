/*
QR Attendy base on Website and WebApp lol
Develop by BELDAD-Ace on Github with the team group 1 for PR2
aka Jhon Benedict Belad

all rights reserved 2025

*/


//for lets get started button and click to sign up or in 
async function afterIn() {
  document.querySelector(".body-sign-up").style.display = "block";
  document.querySelector(".body-sign-in").style.display = "none";
}
async function afterUp() {
  document.querySelector(".body-sign-up").style.display = "none";
  document.querySelector(".body-sign-in").style.display = "block";
}

//for mobile version
async function startSetup() {
  document.querySelector('.main-titleMOBILE').style.display = "none";
  document.querySelector(".body-sign-upMOBILE").style.display = "none";
  document.querySelector(".body-sign-inMOBILE").style.display = "block";

}

async function afterInMOBILE() {
  document.querySelector(".body-sign-upMOBILE").style.display = "block";
  document.querySelector(".body-sign-inMOBILE").style.display = "none";
}
async function afterUpMOBILE() {
  document.querySelector(".body-sign-upMOBILE").style.display = "none";
  document.querySelector(".body-sign-inMOBILE").style.display = "block";
}//when was the day i could get a real girlfriend bro..


//for the dashboards

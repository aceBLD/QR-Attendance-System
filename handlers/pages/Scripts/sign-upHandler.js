/*
QR Attendy base on Website and WebApp lol
Develop by BELDAD-Ace on Github with the team group 1 for PR2
aka Jhon Benedict Belad

all rights reserved 2025

*/
//for SIGN UP form at main and main_mobil
document.getElementById("signupForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const formData = new FormData(e.target);
  const msg = document.getElementById("signupMessage");
  msg.textContent = ""; // clear old message

  // Check password match on frontend before sending
  const password = formData.get("password");
  const confirmPassword = formData.get("confirmPassword");
  if (password !== confirmPassword) {
    msg.style.color = "red";
    msg.textContent = "Passwords do not match!";
    return;
  }

    //for debugging lol it works so yeah
  try {
    const response = await fetch("signup", {
      method: "POST",
      body: new URLSearchParams(formData),
    });

    const result = await response.json();
    msg.style.color = result.status === "success" ? "green" : "red";
    msg.textContent = result.message;

    if (result.status === "success") {
      setTimeout(() => (window.location.href = "Main.html"), 1000);
    }
  } catch (err) {
    msg.style.color = "red";
    msg.textContent = "Error while signing up: " + err;
  }
});


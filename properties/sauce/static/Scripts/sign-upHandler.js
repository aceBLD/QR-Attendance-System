/*
QR Attendy base on Website and WebApp lol
Develop by BELDAD-Ace on Github with the team group 1 for PR2
aka Jhon Benedict Belad

all rights reserved 2025

*/


const signUpForm = document.getElementById("signUpForm");
  const msg = document.getElementById("signupMessage");

if (signUpForm) {
  signUpForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const fullName = signUpForm.querySelector("input[name='fullname']").value;
    const email = signUpForm.querySelector("input[name='email']").value;
    const password = signUpForm.querySelector("input[name='password']").value;
    const confirmPassword = signUpForm.querySelector("input[name='confirmPassword']").value;
    const role = signUpForm.querySelector("input[name='role']").value;

    if (password !== confirmPassword) {
      signupMessage.textContent = "Passwords do not match.";
      signupMessage.style.color = "red";
      return;
    }

   try {
      const res = await fetch("/auth/signup", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        credentials: "same-origin",
        body: JSON.stringify({ fullName, email, password, role })
      });

      if (res.redirected) {
        window.location.href = res.url;
        return;
      }

      const ct = (res.headers.get("content-type") || "").toLowerCase();
      if (ct.includes("application/json")) {
        const data = await res.json();
        if (res.ok) window.location.href = data.redirect || "/dashboard/index.html";
        else {
          msg.textContent = data.error || "Signup failed";
          msg.style.color = "red";
        }
        return;
      }

      const text = await res.text();
      try {
        const parsed = JSON.parse(text);
        if (res.ok) window.location.href = parsed.redirect || "/dashboard/index.html";
        else {
          msg.textContent = parsed.error || "Signup failed";
          msg.style.color = "red";
        }
      } catch {
        if (res.ok) window.location.href = "/dashboard/index.html";
        else {
          msg.textContent = text ? text.slice(0,200) : "Signup failed";
          msg.style.color = "red";
        }
      }
    } catch (err) {
      msg.textContent = "Network error: " + err.message;
      msg.style.color = "red";
    }
  });
}
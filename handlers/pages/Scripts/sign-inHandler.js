document.getElementById("signinForm").addEventListener("submit", async (e) => {
  e.preventDefault();

  const formData = new FormData(e.target);
  const msg = document.getElementById("signinMessage");
  msg.textContent = ""; // clear old message

  try {
    const response = await fetch("signin", {
      method: "POST",
      body: new URLSearchParams(formData),
    });

    const result = await response.json();
    msg.style.color = result.status === "success" ? "green" : "red";
    msg.textContent = result.message;

    if (result.status === "success") {
      // Redirect by role
      if (result.role === "Teacher") {
        window.location.href = "TeacherDash.html";
      } else if (result.role === "Student") {
        window.location.href = "StudentDash.html";
      } else if (result.role === "Admin") {
        window.location.href = "CoreDash.html";
      } else {
        window.location.href = "Main.html";
      }
    }
  } catch (err) {
    msg.style.color = "red";
    msg.textContent = "Error while signing in: " + err;
  }
});


let user = {
    'id': null,
    'username': null
}
let storedUser = sessionStorage.getItem("user");

if (storedUser) {
    user = JSON.parse(storedUser);
} else {
    let username = prompt("What's your name?");

    if (username !== null && username.trim() !== "") {
        user.username = username.trim();
        sessionStorage.setItem("user", JSON.stringify(user));
    }
}

let welcomeMessage = document.getElementById("welcome-message")
welcomeMessage.textContent = `Welcome ${user.username}!`;

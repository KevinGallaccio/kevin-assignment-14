let user = {
    'id': null,
    'username': null
}
let username = prompt("What's your name?");
if (username !== null && username !== "") {
    user.username = username;
    sessionStorage.setItem("user", JSON.stringify(user));
}
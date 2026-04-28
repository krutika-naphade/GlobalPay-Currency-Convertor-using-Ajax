// LOGIN
function login() {

    var user = document.getElementById("username").value;
    var pass = document.getElementById("password").value;

    if (user === "admin" && pass === "1234") {

        document.getElementById("loginPage").style.display = "none";
        document.getElementById("appPage").style.display = "block";

        showPage("dashboard");

    } else {
        alert("Wrong credentials");
    }
}

// LOGOUT
function logout() {
    location.reload();
}

// PAGE SWITCH
function showPage(id) {

    var pages = document.querySelectorAll(".page");
    pages.forEach(p => p.classList.remove("active"));

    document.getElementById(id).classList.add("active");
}

// CONVERT
function convert() {

    var amt = document.getElementById("amount").value;
    var from = document.getElementById("from").value;
    var to = document.getElementById("to").value;

    var rate = 83; // simple fixed rate

    var result = amt * rate;

    document.getElementById("result").innerText =
        amt + " " + from + " = " + result + " " + to;
}

// SEND
function sendMoney() {

    var name = document.getElementById("receiver").value;
    var amt = document.getElementById("sendAmount").value;

    var bal = document.getElementById("balance").innerText;

    bal = bal - amt;

    document.getElementById("balance").innerText = bal;

    document.getElementById("sendResult").innerText =
        "Sent ₹" + amt + " to " + name;

    var list = document.getElementById("historyList");
    var li = document.createElement("li");
    li.innerText = name + " - ₹" + amt;
    list.prepend(li);
}

// SAVE PROFILE
function saveProfile() {
    alert("Profile Saved");
}

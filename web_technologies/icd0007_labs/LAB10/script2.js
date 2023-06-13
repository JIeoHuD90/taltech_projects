/*global document: false */
/*global sessionStorage: false, console: false, $: false */
//Refrence https://www.w3schools.com/js/js_cookies.asp
function setCookie(cname, cvalue, exdays) {
    "use strict";
    sessionStorage.setItem(cname, cvalue);
    var d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/~levagu/icd0007_labs/LAB10/";
}
//Refrence https://www.w3schools.com/js/js_cookies.asp

function getCookie(cname) {
    "use strict";
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}
//Refrence https://www.w3schools.com/js/js_cookies.asp

if (sessionStorage.getItem('username') === null) {
    var username = prompt("What is your name");
    while (username === '' || username === null) {
        username = prompt("What is your name");
    }
    setCookie('username', username, 30);

}
//Refrence https://www.w3schools.com/js/js_cookies.asp

function checkCookie() {
    var user = sessionStorage.getItem("username");
    if (user !== "" && user !== null) {
        var greet = user + "s Shopping list:";
        document.getElementById("greeting").innerHTML = greet;
        fillTable(user);
    }
}

function fillTable(user) {
    if (localStorage.getItem(user) !== null) {
        var Parent = document.getElementById("growing-table");
        while (Parent.hasChildNodes()) {
            Parent.removeChild(Parent.lastChild);
        }
        var groceries = JSON.parse(localStorage.getItem(user));
        var table = document.getElementById("growing-table");
        var number = table.rows.length;

        for (var key in groceries) {

            var name = groceries[key];
            var row = table.insertRow(number);
            row.setAttribute('id', key);
            row.onclick = function deleteRow() {
                if (window.confirm("Delete " + this.id + "?") == 1) {
                    delete groceries[this.id];
                    localStorage.setItem(user, JSON.stringify(groceries));
                    location.reload();
                }
            }
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            cell1.innerHTML = number + ".";
            cell2.innerHTML = key;
            cell3.innerHTML = name;
            number += 1;
        }
    }
}

function storage() {
    var user = getCookie("username");
    var groceries = {};
    if (localStorage.getItem(user)) {
        groceries = JSON.parse(localStorage.getItem(user));
    } else {
        groceries = {};
    }
    var prod = document.getElementById("product").value;
    var quant = document.getElementById("quant").value;
    groceries[prod] = quant;
    localStorage.setItem(user, JSON.stringify(groceries));
    fillTable(user);

}

function logout() {
    document.cookie = "username=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/~levagu/icd0007_labs/LAB10/;";
    sessionStorage.clear();
    location.reload();

}
var xhttp = new XMLHttpRequest();

xhttp.onreadystatechange = function () {
    if (xhttp.readyState == 4 && xhttp.status == 200){
        var btn = document.getElementById("addFriendBtn");

        console.log(btn.className.toString().contains("btn-primary"));
        console.log(btn.className);

        if (btn.className.toString().contains("btn-primary")){
            btn.className = "btn btn-warning";
            btn.innerText = "Delete Friend";
        } else {
            btn.className = "btn btn-primary";
            btn.innerText = "Add to friend";
        }
    }
}

document.forms.addFriend.onsubmit = function (e) {
    e.preventDefault();

    var userId = document.getElementById("addFriend").dataset.userid;

    xhttp.open("POST", "/changeFriendStatus?userid=" + userId);
    xhttp.setRequestHeader("Content-Type", "application/x-www-form-url;");

    xhttp.send();
}

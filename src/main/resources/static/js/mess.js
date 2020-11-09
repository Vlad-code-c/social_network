
var xhttp = new XMLHttpRequest();

xhttp.onreadystatechange = function () {
    if (xhttp.readyState == 4 && xhttp.status == 200){
        unparseMessages(xhttp.responseText);
    }
}
xhttp.open("GET", "/test/0", true);
xhttp.send();


function unparseMessages(data){
    let jsonData = JSON.parse(data);

    var none = document.getElementById("none");

    for (var i = 0; i < jsonData.length; i++) {
            var newElem = document.createElement("DIV");
            newElem.innerHTML = jsonData[i];

        insertBefore(newElem, none);
    }

}
function insertBefore(el, referenceNode) {
    referenceNode.parentNode.insertBefore(el, referenceNode);
}




//////////////////////////   POST   //////////////////////////
var xhttp2 = new XMLHttpRequest();

document.forms.message_send.onsubmit = function (e) {
    e.preventDefault();

    var message = document.forms.message_send.message.value;
    document.forms.message_send.message.value = "";


    xhttp2.open("POST", "/messages/0?message=" + message);
    xhttp2.setRequestHeader("Content-Type", "application/x-www-form-url;");

    xhttp2.send();
}

xhttp2.onreadystatechange = function () {
    if (xhttp2.readyState == 4 && xhttp2.status == 200){
        unparseMessages(xhttp2.responseText);
    }
}

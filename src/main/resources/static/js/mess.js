//////////////////////////   GET MESSAGES   //////////////////////////
var xhttp = new XMLHttpRequest();

xhttp.onreadystatechange = function () {
    if (xhttp.readyState == 4 && xhttp.status == 200){
        unparseMessages(xhttp.responseText);
    }
}
xhttp.open("GET", "/getMessages/" + getChatId(), true);
xhttp.send();

//////////////////////////   SEND MESSAGE   //////////////////////////
var xhttp2 = new XMLHttpRequest();

document.forms.message_send.onsubmit = function (e) {
    e.preventDefault();

    var message = document.forms.message_send.message.value;
    document.forms.message_send.message.value = "";


    xhttp2.open("POST", "/sendMessage/" + getChatId() + "?message=" + message);
    xhttp2.setRequestHeader("Content-Type", "application/x-www-form-url;");

    xhttp2.send();
}

xhttp2.onreadystatechange = function () {
    if (xhttp2.readyState == 4 && xhttp2.status == 200){
        unparseMessages(xhttp2.responseText);
    }
}

//////////////////////////   OTHER METHODS   //////////////////////////
function unparseMessages(data){
    let jsonData = JSON.parse(data);

    var none = document.getElementById("none");

    if (jsonData["error"] != undefined){
        console.log(jsonData["error"]);
        return;
    }

    for (var i = 0; i < jsonData.length; i++) {
        var newElem = document.createElement("DIV");
        newElem.innerHTML = jsonData[i];

        insertBefore(newElem, none);
    }

    pageScroll()

}
function insertBefore(el, referenceNode) {
    referenceNode.parentNode.insertBefore(el, referenceNode);
}

function pageScroll(){
    let msger_chat = document.getElementById("msger-chat");
    msger_chat.scrollBy(0, msger_chat.scrollHeight);
}
function getChatId() {
    var chatId_div = document.getElementById("none");
    var chatId = chatId_div.dataset.chatid;

    return chatId;
}

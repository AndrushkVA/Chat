var msgList = [];

function run(){
	var appContainer = document.getElementsByClassName('todos')[0];

	appContainer.addEventListener('click', delegateEvent);

	msgList = loadMsgs() || [];
    renderLocalFiles(msgList);
}

function loadMsgs() {
    if (typeof(Storage) == "undefined") {
        alert("cant access localStorage");
        return;
    }
    var item = localStorage.getItem("MessageList");
    return item && JSON.parse(item);
}

function delegateEvent(evtObj) {
	if (evtObj.type == 'click' && evtObj.target.id == 'reName') {
        onNameEditButton(evtObj);
    }
	if ((evtObj.type == 'click') && evtObj.target.classList.contains('flat_button')) {
        onInputButton(evtObj);
        document.getElementById('chat').scrollTop = document.getElementById('chat').scrollHeight - document.getElementById('chat').clientHeight;
    }
    if (evtObj.type == 'click' && evtObj.target.id.substring(0, 6) == 'btn-re') {
        onEditMessage(evtObj.target.id.substring(6), evtObj);
    }
    if (evtObj.type == 'click' && evtObj.target.id.substring(0, 7) == 'btn-del') {
        onDeleteMessage(evtObj.target.id.substring(7), evtObj);
    }
}

function onNameEditButton() {
    var check = confirm('Do you really want to edit your name?');
    if (check) {
        var newName = prompt("Enter your name", document.getElementById("nameUser").innerHTML);
        document.getElementById('nameUser').innerHTML = newName;
    }
}

function onInputButton() {

    var text = document.getElementById('todoText').value;
    var author = document.getElementById('nameUser').innerHTML;

    if (text.length > 0) {
        var message = createMessage(author, text);
        var msgArea = document.getElementById('chat');
        msgArea.appendChild(message);
    }
    else {
        alert("You can't send nothing!");
    }
    document.getElementById('todoText').value = '';
}

function createMessage(author, content) {

    var msgId = uniqueId();
    var b = document.createElement('b');
    b.appendChild(document.createTextNode(author + ": ")) 

    var divItem = document.createElement('div');
    divItem.classList.add('myMessage');
    divItem.id = 'd' + msgId;

    var buttonRe = document.createElement('button');
    buttonRe.classList.add('todo-button-re');
    buttonRe.classList.add('todo-button-re-msg');
    buttonRe.id = 'btn-re' + msgId;

    var buttonDel = document.createElement('button');
    buttonDel.classList.add('todo-button-del');
    buttonDel.classList.add('todo-button-del-msg');
    buttonDel.id = 'btn-del' + msgId;

    var textItem = document.createElement('div');
    textItem.classList.add('text');

    var p = document.createElement('p');
    p.id = msgId;
    var aside = document.createElement('aside');
    aside.appendChild(buttonRe);
    aside.appendChild(buttonDel);
    textItem.appendChild(b);
    p.appendChild(document.createTextNode(content))

    textItem.appendChild(p)
    divItem.appendChild(aside)
    divItem.appendChild(textItem);

    var msg = msgLocal(content, author, msgId);
    msgList.push(msg);
    saveMsgs(msgList);

    return divItem;
}

function uniqueId() {
    var date = Date.now();
    var random = Math.random() * Math.random();
    return Math.floor(date * random).toString();
}

function msgLocal(text, auth, ID, edited, deleted) {
    return {
        message: text,
        author: auth,
        ide: ID,
        edited: !!edited,
        deleted: !!deleted
    }
}

function saveMsgs(listSave) {
    if (typeof(Storage) == "undefined") {
        alert("cant access localStorage");
        return;
    }
    localStorage.setItem("MessageList", JSON.stringify(listSave));
}

function renderLocalFiles(list) {
    document.getElementById('nameUser').innerHTML = list[list.length - 1].author;
    for (var i = 0; i < list.length; i++) {
        renderMsg(list[i]);
    }
    document.getElementById('chat').scrollTop = document.getElementById('chat').scrollHeight - document.getElementById('chat').clientHeight;
}

function renderMsg(element) {

    var msgId = element.ide;
    var b = document.createElement('b');
    if(element.deleted){
        var de = document.createElement('de');
        de.classList.add('delete');
        de.innerHTML = "*deleted*";
        b.appendChild(document.createTextNode(element.author + ": "));

        var divItem = document.createElement('div');
        divItem.classList.add('myMessage');
        divItem.id = 'd' + msgId;

        var textItem = document.createElement('div');
        textItem.classList.add('text');

        var p = document.createElement('p');
        p.id = msgId;
        p.appendChild(de);
        textItem.appendChild(b);
        textItem.appendChild(p);
        divItem.appendChild(textItem);
    }else{
        if (element.edited) {
        var ed = document.createElement('ed');
        ed.classList.add('edit');
        ed.innerHTML = "*edited* ";
        b.appendChild(ed);
        b.appendChild(document.createTextNode(element.author + ": "));
        } else{
            b.appendChild(document.createTextNode(element.author + ": "));
        }

        var divItem = document.createElement('div');
        divItem.classList.add('myMessage');
        divItem.id = 'd' + msgId;

        var buttonRe = document.createElement('button');
        buttonRe.classList.add('todo-button-re');
        buttonRe.classList.add('todo-button-re-msg');
        buttonRe.id = 'btn-re' + msgId;

        var buttonDel = document.createElement('button');
        buttonDel.classList.add('todo-button-del');
        buttonDel.classList.add('todo-button-del-msg');
        buttonDel.id = 'btn-del' + msgId;

        var textItem = document.createElement('div');
        textItem.classList.add('text');

        var p = document.createElement('p');
        p.id = msgId;
        var aside = document.createElement('aside');
        aside.appendChild(buttonRe);
        aside.appendChild(buttonDel);
        textItem.appendChild(b);
        p.appendChild(document.createTextNode(element.message))

        textItem.appendChild(p)
        divItem.appendChild(aside)
        divItem.appendChild(textItem);
    }
    
    document.getElementById('chat').appendChild(divItem);
}

function onEditMessage(messageID) {

    var check = confirm('Do you really want to edit this message?');
    if (check) {
        var newMsg = prompt("Enter new message", document.getElementById(messageID).innerText);
        for(var i = 0; i < msgList.length; i++){
            if(msgList[i].ide == messageID){
                msgList[i].message = newMsg;
                if(!msgList[i].edited){
                    msgList[i].edited = !msgList[i].edited;
                }
            }
        }
    }
    saveMsgs(msgList);
    document.getElementById('chat').innerHTML = '';
    renderLocalFiles(msgList);
}

function onDeleteMessage(messageID) {
    var check = confirm("You really want to delete this message?");
    if (check) {
        for(var i = 0; i < msgList.length; i++){
            if(msgList[i].ide == messageID){
                if(!msgList[i].deleted){
                    msgList[i].deleted = !msgList[i].deleted;
                }
            }
        }
    }
    saveMsgs(msgList);
    document.getElementById('chat').innerHTML = '';
    renderLocalFiles(msgList);
}

/*function updateCounter(){
	var items = document.getElementsByClassName('chat')[0];
	var counter = document.getElementsByClassName('counter-holder')[0];

    counter.innerText = chat.children.length.toString();
}*/
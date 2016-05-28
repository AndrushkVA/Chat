//var msgList = [];

var Application = {
    mainUrl : 'http://localhost:999/chat',
    msgList:[],
    token : 'TN11EN'
};

var url = Application.mainUrl + '?token=' + Application.token;

function loadTasks(done) {
    ajax('GET', url, null, function(responseText){
        var response = JSON.parse(responseText);

        Application.msgList = response.messages;
        Application.token = response.token;
        done();
    });
}

/*function addTask(text, done) {
    if(text == '' || text == null)
        return;

    var task = newTask(text);

    var url = Application.mainUrl + '?token=' + Application.token;
    ajax('POST', Application.mainUrl, JSON.stringify(task), function(){
        //Application.msgList.push(task);
        done();
    });
}*/

function run(){
	var appContainer = document.getElementsByClassName('todos')[0];

    appContainer.addEventListener('click', delegateEvent);

    loadTasks(function(){
        renderLocalFiles(Application.msgList);
    });

    //msgList = loadMsgs() || [];
    //renderLocalFiles(Application.msgist);
    document.getElementById('chat').scrollTop = document.getElementById('chat').scrollHeight - document.getElementById('chat').clientHeight;
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

function renderLocalFiles(list) {
    document.getElementById('nameUser').innerHTML = list[list.length - 1].author;
    for (var i = 0; i < list.length; i++) {
        renderMsg(list[i]);
    }
}

function renderLocalFilesWithoutName(list) {
    for (var i = 0; i < list.length; i++) {
        renderMsg(list[i]);
    }
}

function renderMsg(element) {
    if(element.author == document.getElementById('nameUser').innerHTML){
        if(element.deleted) {
            var divItem = msgDeleted(element);
        } else {
            var divItem = myMsgNotDeleted(element);
        }
    } else{
        if(element.deleted) {
            var divItem = msgDeleted(element);
        } else {
            var divItem = msgNotDeleted(element);
        }
    }
    document.getElementById('chat').appendChild(divItem);
}

function msgDeleted(element){
    var msgId = element.ide;
    var b = document.createElement('b');

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

    return divItem;
}

function myMsgNotDeleted(element){
    var msgId = element.id;
    var b = document.createElement('b');

    if (element.edited) {
        var ed = document.createElement('ed');
        ed.classList.add('edit');
        ed.innerHTML = "*edited* ";
        b.appendChild(ed);

    }
    b.appendChild(document.createTextNode(element.author + ": "));

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
    p.appendChild(document.createTextNode(element.text))

    textItem.appendChild(p)
    divItem.appendChild(aside)
    divItem.appendChild(textItem);

    return divItem;
}

function msgNotDeleted(element){
    var msgId = element.id;
    var b = document.createElement('b');

    if (element.edited) {
        var ed = document.createElement('ed');
        ed.classList.add('edit');
        ed.innerHTML = "*edited* ";
        b.appendChild(ed);

    }
    b.appendChild(document.createTextNode(element.author + ": "));

    var divItem = document.createElement('div');
    divItem.classList.add('myMessage');
    divItem.id = 'd' + msgId;

    /*var buttonRe = document.createElement('button');
    buttonRe.classList.add('todo-button-re');
    buttonRe.classList.add('todo-button-re-msg');
    buttonRe.id = 'btn-re' + msgId;

    var buttonDel = document.createElement('button');
    buttonDel.classList.add('todo-button-del');
    buttonDel.classList.add('todo-button-del-msg');
    buttonDel.id = 'btn-del' + msgId;*/

    var textItem = document.createElement('div');
    textItem.classList.add('text');

    var p = document.createElement('p');
    p.id = msgId;
    /*var aside = document.createElement('aside');
    aside.appendChild(buttonRe);
    aside.appendChild(buttonDel);*/
    textItem.appendChild(b);
    p.appendChild(document.createTextNode(element.text))

    textItem.appendChild(p)
    //divItem.appendChild(aside)
    divItem.appendChild(textItem);

    return divItem;
}

/*function loadMsgs() {
    if (typeof(Storage) == "undefined") {
        alert("cant access localStorage");
        return;
    }
    var item = localStorage.getItem("MessageList");
    return item && JSON.parse(item);
}*/

function onNameEditButton() {
    var check = confirm('Do you really want to edit your name?');
    if (check) {
        var newName = prompt("Enter your name", document.getElementById("nameUser").innerHTML);
        document.getElementById('nameUser').innerHTML = newName;
    }
    document.getElementById('chat').innerHTML = '';
    renderLocalFilesWithoutName(Application.msgList);
}

function onInputButton() {

    var text = document.getElementById('todoText').value;
    var author = document.getElementById('nameUser').innerHTML;
    var id = uniqueId();

    if (text.length > 0) {
        var message = msgLocalToPost(text, author, id);
        var json_message = JSON.stringify(message);
        ajax('POST', url, json_message, function(){
            loadTasks(function(){
                document.getElementById('chat').innerHTML = '';
                renderLocalFilesWithoutName(Application.msgList);
            });
        });
    } else {
        alert("You can't send nothing!");
    }
    document.getElementById('todoText').value = '';
}

function onEditMessage(messageID) {

    var check = confirm('Do you really want to edit this message?');
    if (check) {
        var newMsg = prompt("Enter new message", document.getElementById(messageID).innerText);
        var json_message = JSON.stringify(msgLocalToEdited(newMsg, messageID));
        ajax('PUT', url, json_message, function(){
            document.getElementById('chat').innerHTML = '';
            loadTasks(function(){
                renderLocalFilesWithoutName(Application.msgList);
            });
        });
    }
    /*saveMsgs(msgList);
    document.getElementById('chat').innerHTML = '';
    renderLocalFiles(msgList);*/
}

function onDeleteMessage(messageID) {
    var check = confirm("You really want to delete this message?");
    if (check) {
        var json_message = JSON.stringify(msgLocalToDelete(messageID));
        ajax('DELETE', url, json_message, function(){
            document.getElementById('chat').innerHTML = '';
            loadTasks(function(){
                renderLocalFilesWithoutName(Application.msgList);
            });
        });
    }
    /*saveMsgs(msgList);
    document.getElementById('chat').innerHTML = '';
    renderLocalFiles(msgList);*/
}

/*function createMessage(author, content) {

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
}*/

/*function createMessage(author, text){
    var msg = msgLocal(text, author, uniqueId());
    return 
}*/

function uniqueId() {
    var date = Date.now();
    var random = Math.random() * Math.random();
    return Math.floor(date * random).toString();
}

function msgLocal(text, auth, ID, edited, deleted) {
    return {
        text: text,
        author: auth,
        id: ID,
        timestamp: new Date().getTime(),
        edited: !!edited,
        deleted: !!deleted
    }
}

function msgLocalToPost(text, auth, ID) {
    return {
        text: text,
        author: auth,
        id: ID,
        timestamp: new Date().getTime(),
    }
}

function msgLocalToEdited(text, ID) {
    return {
        text: text,
        id: ID
    }
}

function msgLocalToDelete(ID) {
    return {
        id: ID
    }
}

function saveMsgs(listSave) {
    if (typeof(Storage) == "undefined") {
        alert("cant access localStorage");
        return;
    }
    localStorage.setItem("MessageList", JSON.stringify(listSave));
}

/*function updateCounter(){
	var items = document.getElementsByClassName('chat')[0];
	var counter = document.getElementsByClassName('counter-holder')[0];

    counter.innerText = chat.children.length.toString();
}*/

function defaultErrorHandler(message) {
    console.error(message);
    output(message);
}

function isError(text) {
    if(text == "")
        return false;
    
    try {
        var obj = JSON.parse(text);
    } catch(ex) {
        return true;
    }

    return !!obj.error;
}

function ajax(method, url, data, continueWith, continueWithError) {
    var xhr = new XMLHttpRequest();

    continueWithError = continueWithError || defaultErrorHandler;
    xhr.open(method || 'GET', url, true);

    xhr.onload = function () {
        if (xhr.readyState !== 4)
            return;

        if(xhr.status != 200) {
            continueWithError('Error on the server side, response ' + xhr.status);
            return;
        }

        if(isError(xhr.responseText)) {
            continueWithError('Error on the server side, response ' + xhr.responseText);
            return;
        }

        continueWith(xhr.responseText);
    };    

    xhr.ontimeout = function () {
        ontinueWithError('Server timed out !');
    };

    xhr.onerror = function (e) {
        var errMsg = 'Server connection error !\n'+
        '\n' +
        'Check if \n'+
        '- server is active\n'+
        '- server sends header "Access-Control-Allow-Origin:*"\n'+
        '- server sends header "Access-Control-Allow-Methods: PUT, DELETE, POST, GET, OPTIONS"\n';

        continueWithError(errMsg);
    };

    xhr.send(data);
}
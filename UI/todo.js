function run(){
	var appContainer = document.getElementsByClassName('todos')[0];

	updateCounter();

	appContainer.addEventListener('click', delegateEvent);

	msgList = loadMsgs() || [];
    renderLocalFiles(msgList);

	updateCounter();
}

function renderLocalFiles(list) {
    for (var i = 0; i < list.length; i++) {
        renderMsg(list[i]);
    }
}

function renderMsg(element) {

    var divItem = document.createElement('div');
    divItem.classList.add('message_box');
    divItem.classList.add('your_msg');
    divItem.id = 'd' + element.ide;
    var msgContent = document.createElement('span');
    msgContent.classList.add('message-style');
    msgContent.id = element.ide;

    msgContent.innerHTML = element.author + ': ' + element.message;
    divItem.appendChild(msgContent);
    var buttonEdit = createButton('edit', 'btn-edit' + element.ide);
    var buttonDelete = createButton('delete', 'btn-delete' + element.ide);
    divItem.appendChild(buttonDelete);
    divItem.appendChild(buttonEdit);
    if (element.edited) {
        var editCheck = document.createElement('span');
        editCheck.classList.add('sp');
        editCheck.id = 'sp' + element.ide;
        editCheck.innerHTML = 'edited';
        divItem.appendChild(editCheck);
    }
    if (element.deleted) {
        msgContent.innerHTML = "/message deleted/";
        if (editCheck) {
            divItem.removeChild(editCheck);
        }
        divItem.removeChild(buttonDelete);
        divItem.removeChild(buttonEdit);
    }

    document.getElementById('message-area').appendChild(divItem);

}

function createMessage(author, content) {

    var msgId = uniqueId();
    var divItem = document.createElement('div');
    divItem.classList.add('message_box');
    divItem.classList.add('your_msg');
    divItem.id = 'd' + msgId;
    var msgContent = document.createElement('span');
    msgContent.classList.add('message-style');
    msgContent.id = msgId;
    msgContent.innerHTML = author + ': ' + content;
    divItem.appendChild(msgContent);
    var buttonEdit = createButton('edit', 'btn-edit' + msgId);
    var buttonDelete = createButton('delete', 'btn-delete' + msgId);
    divItem.appendChild(buttonDelete);
    divItem.appendChild(buttonEdit);
    var msg = msgLocal(content, author, msgId);
    msgList.push(msg);
    saveMsgs(msgList);
    return divItem;
}

function saveMsgs(listSave) {
    if (typeof(Storage) == "undefined") {
        alert("cant access localStorage");
        return;
    }
    localStorage.setItem("MessageList", JSON.stringify(listSave));
}

function loadMsgs() {
    if (typeof(Storage) == "undefined") {
        alert("cant access localStorage");
        return;
    }
    var item = localStorage.getItem("MessageList");
    return item && JSON.parse(item);

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

function delegateEvent(evtObj) {
	if(evtObj.type === 'click' && evtObj.target.classList.contains('flat_button')){
		onAddButtonClick(evtObj);
	}
	if (evtObj.type === 'click' && evtObj.target.class == 'todo-button-re'){
		reButton(evtObj);
	}
}

function onAddButtonClick(){
	var todoText = document.getElementById('todoText');

	addTodo(todoText.value);
	todoText.value = '';
	updateCounter();
} 

function addTodo(value) {
	if(!value){
		return;
	}

	var item = createItem(value);
	var items = document.getElementById('chat');

	var k = 2;

	items.appendChild(item);
	updateCounter();
}

function createItem(text){
	var divItem = document.createElement('div');
	var buttonRe = document.createElement('button');
	var buttonDel = document.createElement('button');
	var textItem = document.createElement('div');
	var p = document.createElement('p');
	var asside = document.createElement('asside');

	divItem.classList.add('myMessage');
	buttonRe.classList.add('todo-button-re');
	buttonDel.classList.add('todo-button-del');
	textItem.classList.add('text');
	asside.appendChild(buttonRe);
	asside.appendChild(buttonDel);
	p.appendChild(document.createTextNode(text))
	textItem.appendChild(p)
	divItem.appendChild(asside)
	divItem.appendChild(textItem);

	return divItem;
}

function reButton(){
	
}

function updateCounter(){
	var items = document.getElementsByClassName('chat')[0];
	var counter = document.getElementsByClassName('counter-holder')[0];

    counter.innerText = chat.children.length.toString();
}
function run(){
	var appContainer = document.getElementsByClassName('todos')[0];

	updateCounter();

	appContainer.addEventListener('click', delegateEvent);
	//appContainer.addEventListener('change', delegateEvent);

	updateCounter();
}

function delegateEvent(evtObj) {
	if(evtObj.type === 'click' && evtObj.target.classList.contains('flat_button')){
		onAddButtonClick(evtObj);
	}
	/*if(evtObj.type === 'change' && evtObj.target.nodeName == 'INPUT'){
		var labelEl = evtObj.target.parentElement;

		onToggleItem(labelEl);
	}*/
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
	//var checkbox = document.createElement('input');
	//checkbox.classList.add('item-check');

	divItem.classList.add('myMessage');
	buttonRe.classList.add('todo-button-re');
	buttonDel.classList.add('todo-button-del');
	textItem.classList.add('text');
	//checkbox.setAttribute('type', 'checkbox');

	//divItem.appendChild(checkbox);
	asside.appendChild(buttonRe);
	asside.appendChild(buttonDel);
	p.appendChild(document.createTextNode(text))
	textItem.appendChild(p)
	divItem.appendChild(asside)
	divItem.appendChild(textItem);

	return divItem;
}

function updateCounter(){
	var items = document.getElementsByClassName('chat')[0];
	var counter = document.getElementsByClassName('counter-holder')[0];

    counter.innerText = chat.children.length.toString();
}
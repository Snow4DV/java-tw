function openAlertDialog(title, message) {
    var dialog = document.createElement('div');
    dialog.classList.add('alert-dialog');

    var content = document.createElement('div');
    content.classList.add('alert-dialog-content');

    var titleElem = document.createElement('div');
    titleElem.classList.add('alert-dialog-title');
    titleElem.textContent = title;
    content.appendChild(titleElem);

    var messageElem = document.createElement('div');
    messageElem.classList.add('alert-dialog-message');
    messageElem.textContent = message;
    content.appendChild(messageElem);

    var closeElem = document.createElement('button');
    closeElem.classList.add('alert-dialog-close');
    closeElem.textContent = 'OK';
    closeElem.onclick = function() {
        dialog.remove();
    };
    content.appendChild(closeElem);

    dialog.appendChild(content);
    document.body.appendChild(dialog);
}

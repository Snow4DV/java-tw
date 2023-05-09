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


function openOfferPriceDialog(id, price) {
    var dialog = document.createElement('div');
    dialog.classList.add('alert-dialog');

    var content = document.createElement('div');
    content.classList.add('alert-dialog-content');

    var titleElem = document.createElement('div');
    titleElem.classList.add('alert-dialog-title');
    titleElem.textContent = "Предложить цену";
    content.appendChild(titleElem);

    var messageElem = document.createElement('div');
    messageElem.classList.add('alert-dialog-message');
    messageElem.textContent = "Предложите вашу цену для этого заказа:";
    content.appendChild(messageElem);

    var inputElement = document.createElement('input');
    inputElement.classList.add('alert-dialog-message');
    inputElement.value = price;
    content.appendChild(inputElement);


    var buttonsDiv = document.createElement('div');
    buttonsDiv.classList.add('buttons-flex');

    var offerElem = document.createElement('button');
    offerElem.classList.add('alert-dialog-close');
    offerElem.textContent = 'Предложить';
    offerElem.onclick = function() {
        window.location.href= "order/" + id + "/offer?price=" + inputElement.value;
        dialog.remove();
    };
    buttonsDiv.appendChild(offerElem);

    var closeElem = document.createElement('button');
    closeElem.classList.add('alert-dialog-close');
    closeElem.textContent = 'Отмена';
    closeElem.onclick = function() {
        dialog.remove();
    };
    buttonsDiv.appendChild(closeElem);

    content.appendChild(buttonsDiv);

    dialog.appendChild(content);
    document.body.appendChild(dialog);
}

function openAddRatingDialog(id) {
    var dialog = document.createElement('div');
    dialog.classList.add('alert-dialog');

    var content = document.createElement('div');
    content.classList.add('alert-dialog-content');

    var titleElem = document.createElement('div');
    titleElem.classList.add('alert-dialog-title');
    titleElem.textContent = "Оставить отзыв";
    content.appendChild(titleElem);

    var messageElem = document.createElement('div');
    messageElem.classList.add('alert-dialog-message');
    messageElem.textContent = "Укажите рейтинг водителю:";
    content.appendChild(messageElem);

    // Create the radio buttons for the rating system
    var ratingDiv = document.createElement('div');
    ratingDiv.classList.add('rating-div');
    for (var i = 1; i <= 5; i++) {
        var radioElem = document.createElement('input');
        radioElem.setAttribute('type', 'radio');
        radioElem.setAttribute('name', 'rating');
        radioElem.setAttribute('value', i);
        if (i === 0) {
            radioElem.checked = true;
        }
        ratingDiv.appendChild(radioElem);

        var labelElem = document.createElement('label');
        labelElem.textContent = i;
        ratingDiv.appendChild(labelElem);
    }
    content.appendChild(ratingDiv);

    var buttonsDiv = document.createElement('div');
    buttonsDiv.classList.add('buttons-flex');

    var offerElem = document.createElement('button');
    offerElem.classList.add('alert-dialog-close');
    offerElem.textContent = 'Оставить';
    offerElem.onclick = function() {
        var selectedValue = document.querySelector('input[name="rating"]:checked').value;
        window.location.href= id + "/giveRating?rating=" + selectedValue;
        dialog.remove();
    };
    buttonsDiv.appendChild(offerElem);

    var closeElem = document.createElement('button');
    closeElem.classList.add('alert-dialog-close');
    closeElem.textContent = 'Отмена';
    closeElem.onclick = function() {
        dialog.remove();
    };
    buttonsDiv.appendChild(closeElem);

    content.appendChild(buttonsDiv);

    dialog.appendChild(content);
    document.body.appendChild(dialog);
}

function newToDo() {
    var roomType = document.getElementById('roomType').value;
    var dateIn = document.getElementById('dateIn').value;
    var dateOut = document.getElementById('dateOut').value;
    var urlEnc = encodeURI('addRoom?roomType='+roomType+'&dateIn='+dateIn+'&dateOut='+dateOut)
    $.ajax({
        url: urlEnc
    }).done(function (response) {
        location.href = "listMyRooms.jsp";
    });
}

function loadToDo() {
    $.ajax({
        url: 'listroom'
    }).done(function (response) {
      //  printOnDiv(response.listFromBackend);
          display(response.listFromBackend);
    });
}

// function deleteAll() {
//     $.ajax({
//         url: 'manageMyToList?action=DELETE'
//     }).done(function (response) {
//         printOnDiv(response.listFromBackend); // ne vom asigura ca din backend ne vine listFromBackend goala
//     });
// }

function display (lista) {
    var randuri = "";
    lista.forEach(function (obiect) {
        randuri += "<tr>" +
            "<td>" + obiect.roomType +"</td>" +
            "<td>" + obiect.dateIn + "</td>" +
            "<td>" + obiect.dateOut + "</td>" +
            // "<td> <a href='neverforget?action=delete&id="+obiect.id+"'>x</a></td>" +
            "</tr>";
    });
    $("#obiect").html(randuri);
}

function search(myText) {
    $.ajax("listroom", {
        cache: false,
        dataType: "json",
        data: {
            // order: ordinea,
            search: myText
        }
    }).done(function (response) {
        display(response.listFromBackend);
    });
}


// function printOnDiv(listFromBackend) {
//     var listHtml = '';
//
//     var list = document.getElementById('listOfToDo');
//
//     for (var i = 0; i < listFromBackend.length; i++) {
//         var elemC = listFromBackend[i];
//         var el = '<li>'+elemC.roomType+' '+elemC.startDate+'</li>';
//         listHtml=listHtml+el;
//     }
//     list.innerHTML = '<ol>'+listHtml+'</ol>';
// }



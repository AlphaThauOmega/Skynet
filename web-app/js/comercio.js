/**
 * Define las funciones necesarias para el correcto funcionamiento
 * del skynet.
 * @author Mijail Gutiérrez Valdez
 * @version 1.0, febrero 2014
 */
var marker = null;
var comercioLatitud = 0;
var comercioLongitud = 0;
var defaultLat = 19.3142052;
var defaultLon = -99.1835216;

/*
 * Al cargar el documento se inserta la función para el manejo
 * errores al llenar la forma para registra a un usuario
 */
$(document).ready(function() {
    $("#edit-store").submit(function(event) {
	event.preventDefault();//se detiene el envió de la forma
        editStore(this);
    });
    $("#create-new-store").submit(function(event) {
	event.preventDefault();//se detiene el envió de la forma
        createStore(this);
    });
    $("#comment").submit(function(event) {
        event.preventDefault();
        addComment(this);
    }); 
    $("#add-new-meal").click(function() {
	addFood();
    });
    $("#add-new-station").click(function() {
	addStation();
    });
     $("#eliminar-comercio").submit(function(event) {
        event.preventDefault();//se detiene el envió de la forma
        eliminar(this);
    });
})

/**
 * Obtiene el mapa de google maps
 * @param {Integer} latitude
 * @param {Integer} longitude
 * @param {Boolean} search
 */
function getMap(latitude, longitude, search) {
    var lat = latitude ? latitude : defaultLat;
    var lon = longitude ? longitude : defaultLon;
    var pos = new google.maps.LatLng(lat, lon);
    var mapOptions = {
        zoom: 13,
        center: pos
    };
    var map = new google.maps.Map(document.getElementById('map'), mapOptions);
    if(search) {
        marker = new google.maps.Marker({
            position: pos,
            map: map,
            title: 'Comercio'
        });
    }
    google.maps.event.addListener(map, 'click', function(event) {
        if(marker != null) {
            marker.setMap(null);
        }
        marker = new google.maps.Marker({position: event.latLng, map: map});
        comercioLatitud = event.latLng.lat();
        comercioLongitud = event.latLng.lng();
        if(search) {
            $("#search-latitude").val(event.latLng.lat());
            $("#search-longitude").val(event.latLng.lng());
        }
    });
}

function deleteRow(row, table) {
    table.deleteRow(row.rowIndex);
}

/*
 * 
 */
function addFood() {
    var table = document.getElementById("add-meal");
    var length = table.rows.length;
    var row = table.insertRow(length);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    cell1.class="table_inf";
    cell2.class="table_inf";
    cell3.class="table_inf";
    cell4.class="table_inf";
    var inputCell1 = document.createElement("input");
    var inputCell2 = document.createElement("input");
    var inputCell3 = document.createElement("input");
    var div = document.createElement("div");
    div.className = "delete-row";
    div.addEventListener("click", function () { deleteRow(row, table); }, false);
    inputCell1.type = "text";
    inputCell1.className = "food-name";
    inputCell1.name = "meal" + length;
    inputCell1.placeholder = "Nombre comida";
    inputCell1.required;
    inputCell2.type = "text";
    inputCell2.className = "food-tipo";
    inputCell2.name = "kind-meal" + length;
    inputCell2.placeholder = "Tipo comida";
    inputCell2.required;
    inputCell3.type = "number";
    inputCell3.className = "food";
    inputCell3.name = "price-meal" + length;
    inputCell3.placeholder = "0";
    inputCell3.required;
    cell1.appendChild(inputCell1);
    cell2.appendChild(inputCell2);
    cell3.appendChild(inputCell3);
    cell4.appendChild(div);
}

function addStation(){
    var table = document.getElementById("add-station");
    var length = table.rows.length;
    var row = table.insertRow(length);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    cell1.className="table_inf";
    cell2.className="table_inf";
    var div = document.createElement("div");
    div.className = "delete-row";
    div.addEventListener("click", function () { deleteRow(row, table); }, false);
    var inputCell1 = document.createElement("input");
    var inputCell2 = document.createElement("input");
    inputCell1.type = "text";
    inputCell1.className = "station-name";
    inputCell1.name = "station" + length;
    inputCell1.placeholder = "Nombre estacion";
    inputCell1.required;
    inputCell2.type = "text";
    inputCell2.className = "transport";
    inputCell2.name = "transport-station" + length;
    inputCell2.placeholder = "Transporte";
    inputCell2.required;
    cell1.appendChild(inputCell1);
    cell2.appendChild(inputCell2);
    cell3.appendChild(div);
}

function createStore(form) {
    var formData = new FormData();
    formData.append('nombre', form.nombre.value);
    formData.append('recomendada', form.recomendada.value);
    formData.append('recomendadaTipo', form.recomendadaTipo.value);
    formData.append('recomendadaPrecio', form.recomendadaPrecio.value);
    formData.append('menorPrecio', form.menorPrecio.value);
    formData.append('mayorPrecio', form.mayorPrecio.value);
    formData.append('latitud', comercioLatitud);
    formData.append('longitud', comercioLongitud);
    formData.append('direccion', form.direccion.value);
    formData.append('pagina', form.pagina.value);
    formData.append('bano', form.bano.checked);
    formData.append('comedor', form.comedor.checked);
    formData.append('imagen', form.imagen.files[0]);
    var foodTable = document.getElementById("add-food");
    var meals = foodTable.getElementsByClassName("food-name");
    formData.append('totalComida', meals.length);
    var stationTable = document.getElementById("add-transports");
    var stations = stationTable.getElementsByClassName("station-name");
    formData.append('totalTransporte', stations.length);
    for (var i = 0; i < meals.length; i++) {
        formData.append('comida' + i, meals[i].value);
        formData.append('tipo' + i, foodTable.elements.namedItem('kind-' + meals[i].name).value);
        formData.append('precio'+ i, foodTable.elements.namedItem('price-' + meals[i].name).value);
    }
    for (var i = 0; i < stations.length; i++) { 
        formData.append('estacion' + i, stations[i].value);
        formData.append('transporte' + i, stationTable.elements.namedItem('transport-' + stations[i].name).value);
    }
    var envio = $.ajax({
 	type: 'POST',
 	url: $(form).attr('action'),
 	xhr: function() {
            var ownXhr = $.ajaxSettings.xhr();
            if(ownXhr.upload){
                ownXhr.upload.addEventListener('progress', function(e) {
                    progressSend(e,null);
                }, false);// false for asinchronous
            }
            return ownXhr;},
        timeout: 5000,
 	data: formData,
 	cache: false,
 	contentType: false,
    	processData: false,
 	dataType: 'json'});
    envio.done (function (object) {
        if(object.error) {
            showMessageS(null, 'Error:  <br />' + object.message, (5 * 1000), 0, 0, 'form-error', 'show-error');
            return;
        }
        if(object.success) {
            alert("El comercio se ha creado satisfactoriamente");
            goTo(home);
        }
        });
    envio.fail (function (object, estate, message) {
       	if(object.status && object.status === 404) {
            goTo(home + "error/notFound");
        }
        if(object.status && object.status === 500) {
            goTo(home + 'error/internalError');
        }
        alert('Error:\n' + estate + '\n' + message);
    });
}


function editStore(form) {
    var formData = new FormData();
    if(!confirm ("Quieres modifica el registro?")) {
        return false;
    }
    if(form.nombre.value != "") {
        formData.append('nombre', form.nombre.value);
    }
    if(form.recomendada.value != "") {
        formData.append('recomendada', form.recomendada.value);
    }
    if(form.recomendadaTipo.value != "") {
        formData.append('recomendadaTipo', form.recomendadaTipo.value);
    }
    if(form.recomendadaPrecio.value != "") {
        formData.append('recomendadaPrecio', form.recomendadaPrecio.value);
    }
    if(form.menorPrecio.value != "") {
        formData.append('menorPrecio', form.menorPrecio.value);
    }
    if(form.mayorPrecio.value != "") {
        formData.append('mayorPrecio', form.mayorPrecio.value);
    }
    if(comercioLatitud != 0) {
        formData.append('latitud', comercioLatitud);
    }
    if(comercioLongitud != 0) {
        formData.append('longitud', comercioLongitud);
    }
    if(form.direccion.value != "") {
        formData.append('direccion', form.direccion.value);
    }
    if(form.pagina.value != "") {
        formData.append('pagina', form.pagina.value);
    }
    formData.append('bano', form.bano.checked);
    formData.append('comedor', form.comedor.checked);
    if(form.imagen.files[0] != null) {
        formData.append('imagen', form.imagen.files[0]);
    }
    var foodTable = document.getElementById("add-food");
    var meals = foodTable.getElementsByClassName("food-name");
    formData.append('totalComida', meals.length);
    var stationTable = document.getElementById("add-transports");
    var stations = stationTable.getElementsByClassName("station-name");
    formData.append('totalTransporte', stations.length);
    for (var i = 0; i < meals.length; i++) {
        formData.append('comida' + i, meals[i].value);
        formData.append('tipo' + i, foodTable.elements.namedItem('kind-' + meals[i].name).value);
        formData.append('precio'+ i, foodTable.elements.namedItem('price-' + meals[i].name).value);
    }
    for (var i = 0; i < stations.length; i++) { 
        formData.append('estacion' + i, stations[i].value);
        formData.append('transporte' + i, stationTable.elements.namedItem('transport-' + stations[i].name).value);
    }
    var envio = $.ajax({
        type: 'POST',
        url: $(form).attr('action'),
        xhr: function() {
            var ownXhr = $.ajaxSettings.xhr();
            if(ownXhr.upload){
                ownXhr.upload.addEventListener('progress', function(e) {
                    progressSend(e,null);
                }, false);// false for asinchronous
            }
            return ownXhr;},
        timeout: 5000,
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        dataType: 'json'});
    envio.done (function (object) {
        if(object.error) {
            showMessageS(null, 'Error:  <br />' + object.message, (5 * 1000), 0, 0, 'form-error', 'show-error');
            return;
        }
        if(object.success) {
            alert("El comercio se ha modificado correctamente");
            goTo(home);
        }
        });
    envio.fail (function (object, estate, message) {
        if(object.status && object.status === 404) {
            goTo(home + "error/notFound");
        }
        if(object.status && object.status === 500) {
            goTo(home + 'error/internalError');
        }
        alert('Error:\n' + estate + '\n' + message);
    });
}

function deleteItem(type, id, url) {
    var formData = new FormData();
    if(!confirm ("Quieres eliminar el registro")) {
        return false;
    }
    formData.append(type, id);
        var envio = $.ajax({
        type: 'POST',
        url: url,
        xhr: function() {
            var ownXhr = $.ajaxSettings.xhr();
            if(ownXhr.upload){
                ownXhr.upload.addEventListener('progress', function(e) {
                    progressSend(e,null);
                }, false);// false for asinchronous
            }
            return ownXhr;},
        timeout: 5000,
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        dataType: 'json'});
    envio.done (function (object) {
        if(object.error) {
            showMessageS(null, 'Error:  <br />' + object.message, (5 * 1000), 0, 0, 'form-error', 'show-error');
            return;
        }
        if(object.success) {
            alert("La " + type + " se ha eliminado correctamente");
            var row = document.getElementById(type + "-" + id);
            if(row) {
                row.parentNode.removeChild(row);
            }
        }
        });
    envio.fail (function (object, estate, message) {
        if(object.status && object.status === 404) {
            goTo(home + "error/notFound");
        }
        if(object.status && object.status === 500) {
            goTo(home + 'error/internalError');
        }
        alert('Error:\n' + estate + '\n' + message);
    });
}


function addComment(form) {
    var formData = new FormData();
    var editor = CKEDITOR.instances.editor;
    formData.append('comentario', editor.getData());
        var envio = $.ajax({
        type: 'POST',
        url:$(form).attr('action'),
        xhr: function() {
            var ownXhr = $.ajaxSettings.xhr();
            if(ownXhr.upload){
                ownXhr.upload.addEventListener('progress', function(e) {
                    progressSend(e,null);
                }, false);// false for asinchronous
            }
            return ownXhr;},
        timeout: 5000,
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        dataType: 'json'});
    envio.done (function (object) {
        if(object.error) {
            showMessageS(null, 'Error:  <br />' + object.message, (5 * 1000), 0, 0, 'form-error', 'show-error');
            return;
        }
        if(object.success) {
            var comment = document.getElementById("add-comment");
            var divUser = document.createElement("div");
            divUser.className = "comment-user hidden";
            var divUserName = document.createElement("div");
            divUserName.className = "comment-user-name";
            divUserName.innerHTML = object.nombre;
            var divUserDate = document.createElement("div");
            divUserDate.className = "comment-user-date";
            divUserDate.innerHTML = object.fecha;
            var divComment = document.createElement("div");
            divComment.className = "comment-user-text hidden";
            divComment.innerHTML = object.comentario;
            divUser.appendChild(divUserName);
            divUser.appendChild(divUserDate);
            var firstchild = comment.childNodes[0];
            comment.insertBefore(divUser, firstchild);
            comment.insertBefore(divComment, firstchild);
            $(divUser).fadeIn();
            $(divComment).fadeIn();
        }
    });
    envio.fail (function (object, estate, message) {
        if(object.status && object.status === 404) {
            goTo(home + "error/notFound");
        }
        if(object.status && object.status === 500) {
            goTo(home + 'error/internalError');
        }
        alert('Error:\n' + estate + '\n' + message);
    });
}


function setRating(div, ratting) {
    var formData = new FormData();
    formData.append('calificacion', ratting);
    var form = document.getElementById("rate");
        var envio = $.ajax({
        type: 'POST',
        url:$(form).attr('action'),
        xhr: function() {
            var ownXhr = $.ajaxSettings.xhr();
            if(ownXhr.upload){
                ownXhr.upload.addEventListener('progress', function(e) {
                    progressSend(e,null);
                }, false);// false for asinchronous
            }
            return ownXhr;},
        timeout: 5000,
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        dataType: 'json'});
    envio.done (function (object) {
        if(object.error) {
            showMessageS(null, 'Error:  <br />' + object.message, (5 * 1000), 0, 0, 'form-error', 'show-error');
            return;
        }
        if(object.success) {
            alert("Se ha calificado correctamente");
            var stars = document.getElementsByClassName("rating-star");
            for(var i = 0; i < stars.length; i++) {
                if((i + 1) <= ratting) {
                    stars[i].className = "rating-star-on rating-star";
                } else {
                    stars[i].className = "rating-star-off rating-star";
                }
            }
        }
    });
    envio.fail (function (object, estate, message) {
        if(object.status && object.status === 404) {
            goTo(home + "error/notFound");
        }
        if(object.status && object.status === 500) {
            goTo(home + 'error/internalError');
        }
        alert('Error:\n' + estate + '\n' + message);
    });
}

function eliminar(form) {
    if(! confirm("Desea eliminar el registro")) {
        return false;
    }
    var envio = $.ajax({
        type: 'POST',
        url: $(form).attr('action'),
        data: null,
        dataType: 'json'});
    envio.done (function (object) {
        if(object.error) {
            showMessageS(null, 'Error:  <br/>' + object.message, (5 * 1000), 0, 0, 'form-error', 'show-error');
            return;
        }
        if(object.success) {
            alert("El comercio se ha eliminado satisfactoriamente");
            goTo(home);
        }
    });
    envio.fail (function (object, estate, message) {
        showMessageS(null, 'Error:  <br/>' + message, (5 * 1000), 0, 0, 'form-error', 'show-error');
    });
}
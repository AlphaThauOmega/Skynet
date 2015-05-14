/**
 * Define las funciones necesarias para el correcto funcionamiento
 * de la pagina index de skynet.
 * @author Mijail Gutiérrez Valdez
 * @version 1.0, febrero 2014
 */

var userId = null;
var session = null;
var admin = null;
var timeOut = 5 * 1000 * 60;//5 min


/*
 * Al cargar el documento se inserta la función para el manejo
 * de funciones al editar a un postulante
 */
$(document).ready(function() {
	$("#close-user").click(function(event) {
		event.preventDefault();//se detiene el envió de la forma
		closeSession(this);
	});
        startSession();
        storeLink();
        monitor();
})

/**
 * Redirecciona a un visitante a nivel de ingles que se desea,
 * atravez de la funcion  window.location.assign
 * @param pagina donde se dese ir
 * @returns {undefined}
 */
function goTo(page) {
    window.location.assign(page);
}

/**
 * Muestra un mensaje posicionado en left por posx y en top
 * por posy  respecto al objeto obj un a este sele agregara
 * la clase class, la clase y el mensaje msj duraran tiempo time
 * @param {DOMElement} obj
 * @param {String} msj
 * @param {Int} time
 * @param {Int} posx
 * @param {Int} posy
 * @param {String} class
 * @returns {undefined}
 */
function showMessageS(obj, msj, time, posx, posy, classObj, classMess) {
	var message = document.createElement('div');
	message.style.position= 'absolute';
	message.style.zIndex = '9999';//por defecto
	message.className = classMess;//esta clase debe de estar definida en css
	var pos = getPosition(obj);
	if(obj) {
		message.height = obj.offsetHeight + 'px';//por defecto
		message.height = obj.offsetHeight + 'px';//por defecto
		message.style.left = (pos.x + obj.offsetWidth + posx) + 'px';
		message.style.top = (pos.y + posy) + 'px';
		$(obj).addClass(classObj);
	}
	message.innerHTML = msj;
	message.style.display = 'none';
	document.body.appendChild(message);
	$("html, body").animate({ scrollTop: ((pos.y - 50) + 'px') });
	$(message).fadeIn('slow');
	setTimeout( function () {
		$(message).fadeOut('slow', function () {
			document.body.removeChild(message);
			if(obj) {
				$(obj).removeClass(classObj);
			}
		});
	}, time);
}

/**
 * Regresa la posicion x y del objeto DOM que es pasado
 * como parametro en la función
 * @param {DOMElement} obj
 * @returns {Object} {x,y}
 */
function getPosition(obj) {
	if(obj) {
		var left = obj.offsetLeft;
		var top = obj.offsetTop;
		var parentPos = getPosition(obj.offsetParent);
		left += parentPos.x;
		top += parentPos.y;
		return {x:left, y:top}
	}
	return {x:0, y:0}
}

/**
 * Funcion de progeso para el envio de la forma
 * como parametro en la función
 * @param {xhmr} progress
 * @param {DOMElement} obj
 * @returns {undefined}
 */
function progressSend(progress, obj) {	
    if(progress.lengthComputable) {
        console.log('xhr progress: ' + progress.loaded + '  max: ' + progress.total);
    }
}

/**
 * Crea los enlaces para poder ver los comercios.
 * @returns {Boolean}
 */
function storeLink(adm) {
    var stores = document.getElementsByClassName('store');
    var controller = null;
    var action = null;
    if(admin) {
        controller = 'administrador';
        action = 'editarComercio';
    } else {
        controller = 'comercio';
        action = 'mostrar';
    }
    for(var i = 0; i < stores.length; i++) {
        $(stores[i]).click(function(event){
            goTo(home  + controller + "/" + action + "/" + this.id);
        })
    }
}


/**
 * Verifica si la session ha sido iniciada
 * regresando la respuesta del servidor.
 * @param {JSON/formData} sendData
 * @param {string} sendUrl
 * @returns {JSON}
 */
function send(sendData, sendUrl) {
    var result = null;
    $.ajax({
        type: 'POST',
        url: sendUrl,
        data: sendData,
        async: false,
 	dataType: 'json',
        success: function(object) {
            result = object;
        },
        error: function (object, est, mess) {
            //showMessageS(null, 'Error al enviar la informacion', (5 * 1000), 15, 0, 'input-error', 'show-error');
            result = {data:object, estate: est, message:mess};
        }});
    return result;
}


/**
 * Inicia la session del navegador si se
 * inicio la del servidor.
 * @returns {Boolean}
 */
function startSession() {
    var object = send({sesion:true}, "/Skynet/sesion/sesionIniciada");
    if(object && !object.success && object.error && object.message) {
        showMessageS(null, 'Error:  <br />' + object.message, (5 * 1000), 15, 0, 'input-error', 'show-error');
    }
    if(object.success && object.user) {
        userId = object.user.id;
        session = true;
        $("#greeting-name-user").text(object.user.name);
        $("#close-user").fadeIn("slow");
        $("#register").fadeOut("slow");
        $("#sesion-start").fadeIn("slow");
        var show = null;
        if(object.admin) {
            admin = true;
            show = document.getElementsByClassName("admin-panel");
        } else {
            show = document.getElementsByClassName("user-panel");
        }
       for(var i = 0; i < show.length; i++) {
            $(show[i]).fadeIn("slow");
            var childs = show[i].getElementsByTagName("A");
            for(var j = 0; j < childs.length; j++) {
                $(childs[j]).attr("href", $(childs[j]).attr("href"));
            }
        }
        var rate = document.getElementById("set-rate");
        var comment = document.getElementById("new-comment");
        var share = document.getElementById("share");
        if(rate && comment) {
            $(rate).fadeIn();
            $(comment).fadeIn();
            $(share).fadeIn();
            $("#view-rate").fadeOut();
        }
        return true;
    }
    $("#access-user").fadeIn("slow");
    return false;
}

/**
 * Cierra sesion.
 * @returns {Boolean}
 */
function closeSession() {
    if(! confirm ("Cerrar sesion")) {
        return false;
    }
    var formData = new FormData();
    formData.append('cerrar', 'sesion');
    var envio = $.ajax({
        type: 'POST',
        url: "/Skynet/sesion/cerrar",
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
        if(object && object.error != null) {
            showMessageS(null, 'Error:  <br />' + object.message, (5 * 1000), 15, 0, 'input-error', 'show-error');
            return true;
        } else {
            goTo(home);
            return true;
        }
    });
    envio.fail (function (objeto, estado, mensaje) {
           showMessageS(null, 'Error al cerrar sesion', (5 * 1000), 15, 0, 'input-error', 'show-error');
    });
    return true;
}//validate_user_name


function monitor() {
    var object = send({sesion:true}, "/Skynet/sesion/sesionIniciada");
    if(object && object.error && object.message && session) {
        showMessageS(null, 'Error:  <br />  Tiempo de sesion expirado', (3 * 1000), 15, 0, 'input-error', 'show-error');
        setTimeout(goTo(home),(3 * 1000));
        return false;
    }
    setTimeout(monitor, timeOut);
}
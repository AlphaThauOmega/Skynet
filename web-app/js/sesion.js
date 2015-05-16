/**
 * Define las funciones necesarias para el correcto funcionamiento
 * de la edicion del studento de skynet.
 * @author Mijail Gutiérrez Valdez
 * @version 1.0, febrero 2014
 */

/*
 * Al cargar el documento se inserta la función para el manejo
 * de funciones al editar a un postulante
 */
$(document).ready(function() {
    $("#login-form").submit(function(event) {
	event.preventDefault();//se detiene el envió de la forma
	login(this);
    });
    $("#forget-password").click(function() {
        $("#overlay").fadeIn("slow");
	$("#overlay-forget-password").fadeIn("slow");
    });

    $(document.body).keydown(function(event) {
        if(event.which == 27) {
            $("#overlay").fadeOut("slow");
            $("#overlay-forget-password").fadeOut("slow");
	}
    });

    $(".close-overlay").click(function() {
        $("#overlay").fadeOut("slow");
	$("#overlay-forget-password").fadeOut("slow");
    });

    $("#send-email").submit(function(event){
        event.preventDefault();
        sendEmail(this);
    })
})

/*
 * Envía los datos necesarios de la forma form 
 * para el acceso a un uaruio
 * @para {DOMElement} form
 * @return {undefined}
 */
 function login(form) {
    var formData = new FormData();
    formData.append('nombreUsuario', form.nombreUsuario.value);
    var contrasena = CryptoJS.SHA3(form.contrasena.value, { outputLength: 512 });
    formData.append('contrasena', contrasena);
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
            goTo(home);
        }
    });
    envio.fail (function (object, estate, message) {
        if(object.status && object.status == 404) {
            goTo(home + "error/notFound");
        }
        if(object.status && object.status == 500) {
            goTo(home + 'error/internalError');
        }
        if(object.status && object.status == 405) {
            alert('Error metodo no encontrado');
            return;
        }
        alert('Error:\n' + estate + '\n' + message);
    });
}


/*
 * Envía los datos necesarios de la forma form 
 * para enviar un correo electronico
 * @para {DOMElement} form
 * @return {undefined}
 */
 function sendEmail(form) {
    var formData = new FormData();
    formData.append('correo', form.correo.value);
    formData.append('nombreUsuario', form.nombreUsuario.value);
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
            alert("Se ha enviado el el link de recuperacion a tu correo");
            $("#overlay").fadeOut("slow");
            $("#overlay-forget-password").fadeOut("slow");
        }
    });
    envio.fail (function (object, estate, message) {
        if(object.status && object.status == 404) {
            goTo(home + "error/notFound");
        }
        if(object.status && object.status == 500) {
            goTo(home + 'error/internalError');
        }
        if(object.status && object.status == 405) {
            alert('Error metodo no encontrado');
            return;
        }
        alert('Error:\n' + estate + '\n' + message);
    });
}
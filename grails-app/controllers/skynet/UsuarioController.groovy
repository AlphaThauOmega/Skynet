package skynet;

import static org.springframework.http.HttpStatus.*;
import grails.transaction.Transactional;

public class UsuarioController {

    public Object registrar() { 
        if (params == null ||
                                params.nombreUsuario == null ||
                                params.nombre == null ||
                                params.apellidoP == null ||
                                params.apellidoM == null ||
                                params.correo == null ||
                                params.contrasena == null) {
            return;
	}
        Usuario usuario = new Usuario(params);
        if(!usuario.save()) {
            render view: '/error', model: [status:500, exception:usuario.errors];
            return
        }
        response.setContentType("application/json");
        render '{"success":true,"message":"El usuario se ha registrado correctamente"}';
    }

    public Object existeUsuario() { 
        response.setContentType("application/json");
        if (params && params.nombreUsuario) {
            Usuario usuario = Usuario.findByNombreUsuario(params.nombreUsuario)
            if(usuario) {
                render '{"exist":true}';
                return;
            }
            render '{"exist":false}';
            return;
	}
        render '{"error":"nombre de usuario nulo"}'
    }

    public Object editar() {
        if(!session || !session.usuario) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        params.id = params.id ?: session.usuario.id;
	Usuario usuario = Usuario.get(params.id);
        if(!usuario) {
            render view: '/error', model: [status:500, exception: "Usuario no encontrado"];
            return;
        }
        if(!params || !params.editar) {
            return [editar:usuario];
        }
        if(params.nombre) {
            usuario.setNombre(params.nombre);
        }
        if(params.apellidoP) {
            usuario.setApellidoP(params.apellidoP);
        }
	if(params.apellidoM) {
            usuario.setApellidoM(params.apellidoM);
        }
        if(params.correo) {
            usuario.setCorreo(params.correo);
        }
        if(params.contrasena) {
            usuario.setContrasena(params.contrasena);
        }
        response.setContentType("application/json");
        if(!usuario.save(flush:true)) {
            StringBuilder sb = new StringBuilder();
            usuario.errors.each {
		sb.append(it.toString() + "<br/>");
            }
            render '{"error":true,"message":"' + sb.toString() + '"}';
            return;
	}
	render '{"success":true,"message":"El usuario se ha modificado satisfactoriamente"}';
    }
}
			
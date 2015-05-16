package skynet;

import static org.springframework.http.HttpStatus.*;
import grails.transaction.Transactional;
import java.text.SimpleDateFormat;
import grails.converters.JSON;


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
        SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date fecha = new Date();
        String codigo = (format.format(fecha) + usuario.nombre).encodeAsSHA1Hex();
        params.codigo = codigo;
        params.validado = false;
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

    public Object enviar() { 
        response.setContentType("application/json");
        if (params && params.nombreUsuario && params.correo) {
            Usuario usuario = Usuario.findByNombreUsuarioAndCorreo(params.nombreUsuario, params.correo);
            SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            Date fecha = new Date();
            String codigo = (format.format(fecha) + usuario.nombre).encodeAsSHA1Hex();
            usuario.setCodigo(codigo);
            usuario.save(flush:true);
            String url = "Skynet" + "/usuario/recuperar/${usuario.id}?codigo=${codigo}";
            if(usuario) {
                try {
                    sendMail {
                            async true;
                            to usuario.correo;
                            subject 'Reinicio contraseña'
                            html """<p>Da click en el sigiente link para restablecer tu contrasena</p>\n\
                                <a href="${url}">Recuperar contraseña</a>
                                <p style=\"font-size:6pt;\"> Si usted esta leyendo las letra pequeñas tiene buena vista</p>
				<p style=\"font-size:6pt;\"> Skynet es una marca registrada, lease los terminos y condicines de uso</p>"""
                    }
                } catch(Exception e) {
                    render '{"error":"${e.getMessage()}"}';
                    return;
                }
                render '{"success":true}';
                return;
            }
            render '{"error":"nombre de usuario o correo invalido, el usuario esta registrado?"}';
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

    public Object recuperar() {
        if(!params || !params.id || !params.codigo) {
            return;
        }
        Usuario usuario = Usuario.get(params.id);
        if(!usuario || usuario.getCodigo() != params.codigo) {
            response.setContentType("application/json");
        
            render '{"error":true,"message":"codigo invalido"}';
            return;
        }
        if(!params.contrasena) {
            return [editar:usuario];
        }
        usuario.setContrasena(params.contrasena);
        SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Date fecha = new Date();
        String codigo = (format.format(fecha) + usuario.nombre).encodeAsSHA1Hex();
        usuario.setCodigo(codigo);
        response.setContentType("application/json");
        if(!usuario.save(flush:true)) {
            StringBuilder sb = new StringBuilder();
            usuario.errors.each {
		sb.append(it.toString() + "<br/>");
            }
            render '{"error":true,"message":"' + sb.toString() + '"}';
            return;
	}
	render '{"success":true,"message":"El usuario se ha modificado su contraseña exitosamente"}';
    }
}
			
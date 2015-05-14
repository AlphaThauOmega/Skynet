package skynet;

import static org.springframework.http.HttpStatus.*;
import grails.transaction.Transactional;

class SesionController {

    public Object iniciar() {
        if(params && params.nombreUsuario && params.contrasena) {
            response.setContentType("application/json");
            Usuario usuario = Usuario.findByNombreUsuarioAndContrasena(params.nombreUsuario, params.contrasena)
            if(usuario) {
                session.usuario = usuario;
                render '{"success":true}';
                return;
            }
            render '{"error":true, "message":"usuario o contraseña incorrectos"}';
            return;
        }
    }

    public Object sesionIniciada() {
        response.setContentType("application/json");
        if(params && params.sesion) {
            if(session && session.usuario) {
                render "{\"success\":true, \"user\": {\"name\": \"${session.usuario.nombre}\", \"id\": ${session.usuario.id}}, \"admin\": ${session.usuario instanceof Administrador}}";
                return;
            }
            render '{"success":true, "error":true, "message":"usuario no ha iniciado sesion"}';
            return;
        }
        render '{"error":true, "message":"sesion no iniciada"}';
    }

    public Object cerrar() {
        response.setContentType("application/json");
        if(session.usuario) {
            session.invalidate();
            render '{"success":true, "message":"sesion cerrada"}';
            return;
        }
	render '{"error":true, "message":"sesion no iniciada"}';
    }
}
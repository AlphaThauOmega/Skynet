package skynet;

import static org.springframework.http.HttpStatus.*;
import grails.transaction.Transactional;

@Transactional(readOnly = true)
class AdministradorController {

    @Transactional
    public Object editarComercio() {
        if(!session.usuario || !(session.usuario instanceof Administrador)) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        params.max = params.max ?: 10;
        params.offset = params.offset ?: 0;
        if(params.id) {
            Comercio comercio = Comercio.get(params.id);
            if(comercio != null) {
                Object listaComentarios = Comentario.executeQuery("""FROM Comentario AS comentario WHERE
                                                                comentario.comercio.id = ? ORDER BY comentario.fecha desc""",
                                                                [Long.parseLong(params.id)] ,
                                                                [max:params.max, offset:params.offset]);
                return [editar:comercio, comentarios:listaComentarios];
            }
            render view: '/error', model: [status:500, exception: "Comercio no encontrado"];
            return;
        }
        Object listaComercios =  Comercio.list(params);
        Object totalComercios = Comercio.count();
        return [comercios:listaComercios, total:totalComercios];
    }

    @Transactional
    public Object editarUsuario(int max) {
        if(!session.usuario || !(session.usuario instanceof Administrador)) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        if(params.id && !params.editar) {
            Usuario usuario = Usuario.get(params.id);
            if(usuario != null) {
                return [editar:usuario];
            }
            render view: '/error', model: [status:500, exception: "Usuario no encontrado"];
            return;
        }
        params.max = max ?: 10;
        params.offset =  params.offset ?: 0;
        Object listaUsuarios =  Usuario.executeQuery("FROM Usuario as usuario WHERE usuario.class=skynet.Usuario",
                                                     [max:params.max, offset:params.offset]);
        Object totalUsuarios = Usuario.executeQuery("SELECT COUNT(usuario) FROM Usuario as usuario WHERE usuario.class=skynet.Usuario");
        return [usuarios:listaUsuarios, total:totalUsuarios];
    }

    @Transactional
    public Object eliminar() {
        response.setContentType("application/json")
        if(!session.usuario || !(session.usuario instanceof Administrador)) {
            return;
        }
        if(params && params.tipo == "usuario" && params.id) {
            Usuario usuario = Usuario.get(params.id);
            if(!usuario) {
                render view: '/error', model: [status:500, exception: "Usuario no encontrado"];
                return;
            }
            usuario.delete(flush:true);
            render '{"success":true,"message":"El usuario se ha eliminado satisfactoriamente"}'
            return;
        }
        if(params && params.tipo == "comercio" && params.id) {
            Comercio comercio = Comercio.get(params.id);
            if(!comercio) {
                render view: '/error', model: [status:500, exception: "Comercio no encontrado"];
                return;
            }
            comercio.delete(flush:true);
            render '{"success":true,"message":"El comercio se ha eliminado satisfactoriamente"}'
            return;
        }
        render '{"error":true,"message":"error al eliminar"}';
    }

    @Transactional
     public Object nuevoComercio() {
         if(!session || !session.usuario || !(session.usuario instanceof Administrador)) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        
        if(params && params.create) {
            response.setContentType("application/json");
            Comida especialidad = new Comida(nombre:params.recomendada,
                                             especialidad: true,
                                             tipo:params.recomendadaTipo,
                                             precio:Double.parseDouble(params.recomendadaPrecio)).save();
            Comercio comercio = new Comercio(recomendada:especialidad,
                                             nombre:params.nombre,
                                             direccion:params.direccion,
                                             bano:params.bano,
                                             comedor:params.comedor,
                                             pagina:params.pagina,
                                             foto:params.imagen.bytes,
                                             longitud:Double.parseDouble(params.longitud),
                                             latitud:Double.parseDouble(params.latitud),
                                             mayorPrecio:Double.parseDouble(params.mayorPrecio),
                                             menorPrecio:Double.parseDouble(params.menorPrecio));
            int totalComida = Integer.parseInt(params.totalComida);
            for(int i = 0; i < totalComida; i++) {
                Comida comida = new Comida(nombre: params['comida' + i],
                                    tipo:params['tipo' + i],
                                    precio:params['precio' + i]);
                comida.save();
                comercio.addToComidas(comida);
            }
            if (!comercio.save(flush:true, failOnError:true)) {
                StringBuilder sb = new StringBuilder();
                comercio.errors.each {
                    sb.append(it.toString() + "<br />");
                }
                render "{\"error\":true,\"message\":'" + sb.toString().replaceAll("\"", "") + "'}";
                return;
            }
            int totalTransporte = Integer.parseInt(params.totalTransporte);
            String nombreEstacion;
            String nombreTransporte;
            for(int i = 0; i < totalTransporte; i++) {
                nombreEstacion = params['estacion' + i];
                nombreTransporte = params['transporte' + i];
                Transporte transporte = Transporte.findByNombre(nombreTransporte) ?: new Transporte(nombre:nombreTransporte);
                Estacion estacion;
                estacion = Estacion.findByNombre(nombreEstacion);
                if(estacion == null || !estacion.getTransporte().nombre.equals(nombreTransporte)) {
                    estacion = new Estacion(nombre:nombreEstacion);
                    transporte.addToEstaciones(estacion);
                    transporte.save();
                }
                comercio.addToEstaciones(estacion);
            }
            if (!comercio.save(flush:true, failOnError:true)) {
                StringBuilder sb = new StringBuilder();
                comercio.errors.each {
                    sb.append(it.toString() + "<br />");
                }
                render "{\"error\":true,\"message\":'" + sb.toString().replaceAll("\"", "") + "'}";
                return;
            }
            render '{"success":true,"message":"Comercio creado"}';
            return;
        }
     }
}

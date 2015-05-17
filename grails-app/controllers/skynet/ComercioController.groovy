package skynet;

import static org.springframework.http.HttpStatus.*;
import grails.transaction.Transactional;
import java.text.SimpleDateFormat;
import grails.converters.JSON;

class ComercioController {


    public Object index(int max) {
        params.max = max ?: 5;
        params.offset =  params.offset ?: 0;
        Object listaComercios =  Comercio.executeQuery("FROM Comercio as comercio ORDER BY comercio.calificacion DESC",
                                                        [max:params.max, offset:params.offset]);
        Object totalComercios = Comercio.count();
        return [comercios:listaComercios, total:totalComercios];
    }

    public Object buscar() {
        Object listaComercios = Comercio.findByNombreLike("%${params.buscar}%");
        return [comercios:listaComercios];
    }

    public Object busquedaAvanzada() {
        if(params && params.busqueda) {
            Object resultado = [];
            if(params.nombre) {
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                        comercio.nombre LIKE CONCAT('%',?,'%')""",
                                                        [params.nombre]);
            }
            if(params.recomendada) {
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                        comercio.recomendada.nombre LIKE CONCAT('%',?,'%')""",
                                                        [params.recomendada]);
            }
            if(params.menorprecio && params.mayorprecio) {
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                        comercio.menorPrecio < ? AND
                                                        comercio.mayorPrecio > ?""",
                                                        [params.menorprecio, params.mayorprecio]);
            }
            if(params.estacion) {
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                        comercio.estaciones.nombre LIKE CONCAT('%',?,'%')""",
                                                        [params.estacion]);
            }
            if(params.latitud && params.longitud) {
                Double latitud = Double.parseDouble(params.latitud);
                Double longitud = Double.parseDouble(params.longitud);
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                        SQRT(POWER(comercio.latitud-?,2)
                                                        + POWER(comercio.longitud-?,2)) < 0.01 """,
                                                        [latitud, longitud]);
            }
            if(params.comida) {
                resultado += Comercio.executeQuery("""FROM Comercio comercio WHERE
                                                      comercio.id IN (SELECT comercio.id From Comida comida WHERE
                                                      comida.nombre LIKE CONCAT('%',?,'%'))""",
                                                      [params.comida]);
            }
            if(params.tipo) {
                resultado += Comercio.executeQuery("""FROM Comercio comercio WHERE
                                                      comercio.id IN (SELECT comercio.id From Comida comida WHERE
                                                      comida.tipo LIKE CONCAT('%',?,'%'))""",
                                                        [params.tipo]);
            }
            return [comercios:resultado, busqueda:true];
        }
    }

    public Object mostrar() {
        if(params && params.id) {
            params.max = params.max ?: 10;//por omision se muestran los primeros 10 comentarios
            params.offset = params.offset ?: 0;//por omision siempre se toma desde los primeros
            Object listaComentarios = Comentario.executeQuery("""FROM Comentario AS comentario WHERE
                                                                 comentario.comercio.id = ? ORDER BY comentario.fecha desc""",
                                                                [Long.parseLong(params.id)] ,
                                                                [max:params.max, offset:params.offset]);
            return [comercio:Comercio.get(params.id), comentarios:listaComentarios];
        }
        render view: '/error', model: [status:500, exception: "Error comercio incorrecto"];
    }

    public Object calificar() {
        if(!session || !session.usuario) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        response.setContentType("application/json");
        Comercio comercio = Comercio.get(params.id);
        Usuario usuario = Usuario.get(session.usuario.id);
        if(usuario == null || comercio == null) {
            render '{"error":true,"message":"No se ha podido encontrar el comercio o usuario"}';
            return;
        }
        int total = Calificacion.executeQuery("SELECT COUNT(*) FROM Calificacion as calificaciones WHERE calificacion.comercio.id=?",
                                              [comercio.id])[0];
        Calificacion calificacion = new Calificacion(calificacion: Integer.parseInt(params.calificacion));
        comercio.setCalificacion(((comercio.getCalificacion() * total) + Integer.parseInt(params.calificacion)) / (total + 1));
        comercio.addToCalificaciones(calificacion);
        usuario.addToCalificaciones(calificacion);
        if (!comercio.save(flush:true, failOnError:true) || !usuario.save(flush:true, failOnError:true)) {
            render '{"error":true,"message":"No es posible guardar la informacion"}';
            return;
        }
        render '{"success":true,"message":"Calificacion otorgada"}';
    }

    public Object eliminarEstacion() {
        if(!session || !(session.usuario instanceof Administrador)) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        response.setContentType("application/json");
        Comercio comercio = Comercio.get(params.comercio);
        Estacion estacion = Estacion.get(params.estacion);
        if(comercio == null || estacion == null) {
            render '{"error":true,"message":"No se ha podido encontrar la estacion o el comercio"}';
            return;
        }
        comercio.removeFromEstaciones(estacion);
        estacion.delete();
        if (!comercio.save(flush:true, failOnError:true)) {
             render '{"error":true,"message":"No se ha podido eliminar la estacion"}';
             return
        }
        render '{"success":true,"message":"estacion eliminada"}';
    }

    public Object eliminarComida() {
        if(!session || !(session.usuario instanceof Administrador)) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        response.setContentType("application/json");
        Comercio comercio = Comercio.get(params.comercio);
        Comida comida = Comida.get(params.comida);
        if(comercio == null || comida == null) {
            render '{"error":true,"message":"No se ha podido encontrar la comida o el comercio"}';
            return;
        }
        comercio.removeFromComidas(comida);
        comida.delete();
        if (!comercio.save(flush:true, failOnError:true)) {
             render '{"error":true,"message":"No se ha podido eliminar la comida"}';
             return
        }
        render '{"success":true,"message":"Comida eliminada"}';
    }

    public Object eliminarComentario() {
        if(!session || !(session.usuario instanceof Administrador)) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        response.setContentType("application/json");
        Comercio comercio = Comercio.get(params.comercio);
        Comentario comentario = Comentario.get(params.comentario);
        Usuario usuario = comentario.usuario;
        if(comercio == null || comentario == null || usuario == null) {
            render '{"error":true,"message":"No se ha podido encontrar la comida o el comercio"}';
            return;
        }
        comercio.removeFromComentarios(comentario);
        usuario.removeFromComentarios(comentario);
        comentario.delete();
        if (!comercio.save(flush:true, failOnError:true) || !usuario.save(flush:true, failOnError:true)) {
             render '{"error":true,"message":"No se ha podido eliminar el comentario"}';
             return
        }
        render '{"success":true,"message":"Comentario eliminado"}';
    }

    public Object comentar() {
        if(!session || !session.usuario) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        response.setContentType("application/json");
        Comercio comercio = Comercio.get(params.id);
        Usuario usuario = Usuario.get(session.usuario.id);
        if(usuario == null || comercio == null) {
            render '{"error":true,"message":"No se ha encontrado el local o el usuario"}';
            return;
        }
        Comentario comentario = new Comentario(comentario:params.comentario, fecha: new Date());
        comercio.addToComentarios(comentario);
        usuario.addToComentarios(comentario);
        if (!comercio.save(flush:true, failOnError:true) || !usuario.save(flush:true, failOnError:true)) {
             render '{"error":true,"message":"No se ha podido agregar el comentario"}';
             return;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        render '{"success":true,"message":"Comentario agregado", "nombre":"' + usuario.nombre + '", "fecha":"' + format.format(comentario.getFecha()) +'", "comentario":"'+ comentario.getComentario() +'" }';
    }

    public Object imagen() {
        if(!params.id) {    
            return;
	}
	Comercio comercio = Comercio.get(params.id);
	response.setContentType('image/jpeg');
	byte[] foto = comercio?.foto;
	response.outputStream << foto;
    }

    public Object editar() {
        if(!session || !(session.usuario instanceof Administrador)) {
            render view: '/error', model: [status:405, exception: "Permiso denegado"];
            return;
        }
        Comercio comercio = Comercio.get(params.id);
        if(!comercio) {
            render view: '/error', model: [status:500, exception: "Comercio no encontrado"];
            return;
        }
        response.setContentType("application/json");
        if(params.recomendada) {
            comercio.getRecomendada().setNombre(params.recomendada);
        }
        if(params.recomendadaTipo) {
            comercio.getRecomendada().setTipo(params.recomendadaTipo);
        }
        if(params.recomendadaPrecio) {
            comercio.getRecomendada().setPrecio(Double.parseDouble(params.recomendadaPrecio));
        }
        if(params.nombre) {
            comercio.setNombre(params.nombre);
        }
        if(params.direccion) {
            comercio.setDireccion(params.direccion);
        }
        if(params.comedor) {
            comercio.setComedor(Boolean.parseBoolean(params.comedor));
        }
        if(params.bano) {
            comercio.setBano(Boolean.parseBoolean(params.bano));
        }
        if(params.pagina) {
            comercio.setPagina(params.pagina);
        }
        if(params.imagen) {
            comercio.setImagen(params.imagen.bytes);
        }
        if(params.longitud) {
            comercio.setLongitud(Double.parseDouble(params.longitud));
        }
        if(params.latitud) {
            comercio.setLatitud(Double.parseDouble(params.latitud));
        }
        if(params.mayorPrecio) {
            comercio.setMayorPrecio(Double.parseDouble(params.mayorPrecio));
        }
        if(params.menorPrecio) {
            comercio.setMenorPrecio(Double.parseDouble(params.menorPrecio));
        }
        int totalTransporte = Integer.parseInt(params.totalTransporte);
        String nombreEstacion;
        String nombreTransporte;
        for(int i = 0; i < totalTransporte; i++) {
            nombreEstacion = params['estacion' + i];
            nombreTransporte = params['transporte' + i];
            Transporte transporte = new Transporte(nombre: nombreTransporte);
            Estacion estacion = new Estacion(nombre:nombreEstacion);
            transporte.addToEstaciones(estacion);
            transporte.save();
            comercio.addToEstaciones(estacion);
        }
        int totalComida = Integer.parseInt(params.totalComida);
        for(int i = 0; i < totalComida; i++) {
            Comida comida = new Comida(nombre: params['comida' + i],
                                       tipo:params['tipo' + i],
                                       precio:params['precio' + i]);
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
        render '{"success":true,"message":"Comercio creado"}';
        return;
     }

    public Object comerciosMovil() {
        params.max = params.max ?: 5;
        params.offset =  params.offset ?: 0;
        Object listaComercios =  Comercio.executeQuery("FROM Comercio as comercio ORDER BY comercio.calificacion DESC",
                                                        [max:params.max, offset:params.offset]);
        response.setContentType("application/json");
        render '{"comercios":' + (listaComercios as JSON) + ', "total":' + Comercio.count() + '}';
    }

    public Object buscarMovil() {
        Object listaComercios = Comercio.findByNombreLike("%${params.buscar}%");
        response.setContentType("application/json");
        render '{"comercios":' + (listaComercios as JSON)'}';
    }

    public Object busquedaAvanzadaMovil() {
        if(params && params.busqueda) {
            Object resultado = [];
            if(params.nombre) {
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                        comercio.nombre LIKE CONCAT('%',?,'%')""",
                                                        [params.nombre]);
            }
            if(params.recomendada) {
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                        comercio.recomendada.nombre LIKE CONCAT('%',?,'%')""",
                                                        [params.recomendada]);
            }
            if(params.menorprecio && params.mayorprecio) {
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                        comercio.menorPrecio < ? AND
                                                        comercio.mayorPrecio > ?""",
                                                        [params.menorprecio, params.mayorprecio]);
            }
            if(params.estacion) {
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                        comercio.estaciones.nombre LIKE CONCAT('%',?,'%')""",
                                                        [params.estacion]);
            }
            if(params.latitud && params.longitud) {
                Double latitud = Double.parseDouble(params.latitud);
                Double longitud = Double.parseDouble(params.longitud);
                resultado += Comercio.executeQuery("""FROM Comercio as comercio WHERE
                                                        SQRT(POWER(comercio.latitud-?,2)
                                                        + POWER(comercio.longitud-?,2)) < 0.01 """,
                                                        [latitud, longitud]);
            }
            if(params.comida) {
                resultado += Comercio.executeQuery("""FROM Comercio comercio WHERE
                                                      comercio.id IN (SELECT comercio.id From Comida comida WHERE
                                                      comida.nombre LIKE CONCAT('%',?,'%'))""",
                                                      [params.comida]);
            }
            if(params.tipo) {
                resultado += Comercio.executeQuery("""FROM Comercio comercio WHERE
                                                      comercio.id IN (SELECT comercio.id From Comida comida WHERE
                                                      comida.tipo LIKE CONCAT('%',?,'%'))""",
                                                        [params.tipo]);
            }
            response.setContentType("application/json");
            render '{"comercios":' + (resultado as JSON) + ', "total":"busqueda":true}';
        }
    }

    public Object mostrarMovil() {
        if(params && params.id) {
            params.max = params.max ?: 10;//por omision se muestran los primeros 10 comentarios
            params.offset = params.offset ?: 0;//por omision siempre se toma desde los primeros
            Object listaComentarios = Comentario.executeQuery("""FROM Comentario AS comentario WHERE
                                                                 comentario.comercio.id = ? ORDER BY comentario.fecha desc""",
                                                                [Long.parseLong(params.id)] ,
                                                                [max:params.max, offset:params.offset]);
            response.setContentType("application/json");
            render '{"comercios":' + (Comercio.get(params.id) as JSON) + ', "comentarios":' + (listaComentarios as JSON) +  '}';
        }
    }
}

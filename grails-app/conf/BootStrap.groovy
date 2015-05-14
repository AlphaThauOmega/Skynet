import skynet.Usuario;
import skynet.Administrador;

class BootStrap {

    def init = { servletContext ->
            Administrador admin = new Administrador(nombreUsuario:'mijail',
                                                    nombre: 'mijail',
                                                    correo: 'mijail@mijail.com',
                                                    contrasena:'318672fb86ed60eb2a230a782d53f93c243d199f6f6972fee17a0ce8591ec803f0abf83335b2777b1c44792f98cf66567109c843a1c0deaa2a26b85825ca5ee7');
            admin.save(flus:true);
    }
    def destroy = {
    }
}

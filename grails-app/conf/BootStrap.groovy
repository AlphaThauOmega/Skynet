import skynet.Usuario;
import skynet.Administrador;
import java.text.SimpleDateFormat;

class BootStrap {

    def init = { servletContext ->
            Administrador admin;
            admin = Administrador.findByNombreUsuario('admin');
            if(!admin) {
                SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                Date fecha = new Date();
                String codigo = (format.format(fecha) + 'admin').encodeAsSHA1Hex();
                admin = new Administrador(nombreUsuario:'admin',
                                                    nombre: 'administrador',
                                                    correo: 'admin@empresa.com',
                                                    apellidoP: 'admin',
                                                    apellidoM: 'admin',
                                                    codigo: codigo,
                                                    validado: true,
                                                    contrasena:'7407946a7a90b037ba5e825040f184a142161e4c61d81feb83ec8c7f011a99b0d77f39c9170c3231e1003c5cf859c69bd93043b095feff5cce6f6d45ec513764');//admin
                admin.save(failOnError:true);
            }
    }

    def destroy = {
    }
}

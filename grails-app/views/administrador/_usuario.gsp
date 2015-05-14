<div id="board">
    <div id="edit-table">
        <g:form controller="usuario" action="editar" name="editar-usuario" params="[id:editar?.id, editar:true]">
            <br />
         <p> Editar datos del usuario: </p>
            <hr/>
            <table>
                <tr><td class="table-inf">Nombre de usuario: </td><td class="table-input"><input type="text" name="nombreUsuario" maxlength="100" placeholder="nombre de usuario" value="${editar?.nombreUsuario}" disabled/></td></tr>
                <tr><td class="table-inf">Nombre: </td><td class="table-input"><input type="text" name="nombre" maxlength="100" placeholder="${editar?.nombre}" value=""/></td></tr>
                <tr><td class="table-inf">Apellido paterno: </td><td class="table-input"><input type="text" name="apellidoP" maxlength="100" placeholder="${editar?.apellidoP}" value=""/></td></tr>
                <tr><td class="table-inf">Apellido materno: </td><td class="table-input"><input type="text" name="apellidoM" maxlength="100" placeholder="${editar?.apellidoM}" value=""/></td></tr>
                <tr><td class="table-inf">Correo: </td><td class="table-input"><input type="email" name="correo" placeholder="${editar?.correo}" maxlength="100" value=""/></td></tr>
                <tr><td class="table-inf">Contraseña: </td><td class="table-input"><input type="password" name="contrasena" maxlength="100" placeholder="Contrase&ntilde;a" value="" /></td></tr>
                <tr><td class="table-inf">Repetir Contraseña: </td><td class="table-input"><input type="password" name="recontrasena" maxlength="100" placeholder="Re-Contrase&ntilde;a"  value="" id="repassword" /></td></tr>
            </table>
            <table id ="send-edit-form">
                <tr><td></td><td colspan="2"><input type="submit" name="send" value="Editar" /></td></tr>
            </table>
        </g:form>
        <g:form controller="administrador" action="eliminar" name="eliminar-usuario" params="[id:editar?.id, tipo:'usuario']">
            <table id ="send-delete-form">
                <tr><td></td><td colspan="2"><input type="submit" name="delete" value="Eliminar" /></td></tr>
            </table>
        </g:form>
    </div>
</div>
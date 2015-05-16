<html>
	<head>
		<meta name="layout" content="principal"/>
                <script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/sha3.js"></script>
                <script type="text/javascript" src="${createLinkTo(dir:'js',file:'sesion.js')}" ></script>
        </head>
	<body>
            <div id="board">
                <div id="description">
                    <p>Acceso a usuarios</p>
                </div>
                <div id="login">
                    <g:form name="login-form" controller="sesion" action="iniciar">
                    <table>
                        <tr><td class="table-inf">Nombre usuario: </td><td class="table_input"><input type="text" name="nombreUsuario" placeholder="Nombre de usuario" value="" id="login-username-input" required="" /></td></tr>
                        <tr><td class="table-inf">Contraseña: </td><td class="table_input"><input type="password" name="contrasena" maxlength="100" value="" placeholder="Contrase&ntilde;a usuario" id="login-password-input" required="" /></td></tr>
                        <tr><td></td><td id="forget-password">Recuperar contraseña</td></tr>
                        <tr><td colspan="2"><input type="submit" name="access-login" value="Acceder" id="access-login" /></td></tr>
                    </table>
                    </g:form>
                </div>
            </div>
            <div id="overlay"></div>
            <div id="overlay-forget-password">
            <div class="close-overlay" href="#">[x] Close</div>
                <g:form name="send-email" url="[controller:'usuario', action:'enviar']">
                    <p> Para reiniciar tu password debes introducir nombre de usuario y correo </p>
                    <table>
                        <tr><td class="table-inf">Nombre usuario: </td><td class="table_input"><input type="text" name="nombreUsuario" placeholder="Nombre de usuario" value="" required="" /></td></tr>
                        <tr><td class="table-inf">Correo: </td><td class="table_input"><input type="text" name="correo" value="" placeholder="Correo" required="" /></td></tr>
                        <tr><td colspan="2"><input type="submit" value="Recuparar" id="send-password" /></td></tr>
                    </table>
                </g:form>
                <br/>
            </div>
            
	</body>
</html>

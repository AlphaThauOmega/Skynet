<html>
	<head>
		<meta name="layout" content="principal"/>
                <script src="http://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/sha3.js"></script>
                <script type="text/javascript" src="${createLinkTo(dir:'js',file:'usuario.js')}" ></script>
        </head>
	<body>
            <div id="description">
                <p>Recuperar contrase&ntilde;a</p>
            </div>
            <div id="board">
                <div id="edit-table">
                <g:form controller="usuario" action="recuperar" name="recuperar-contrasena" params="[id:editar?.id, codigo:editar?.codigo]">
                    <br />
                    <p> Reiniciar contraseña: </p>
                    <hr/>
                    <table>
                        <tr><td class="table-inf">Contraseña: </td><td class="table-input"><input type="password" name="contrasena" maxlength="100" placeholder="Contrase&ntilde;a" value="" required=""/></td></tr>
                        <tr><td class="table-inf">Repetir Contraseña: </td><td class="table-input"><input type="password" name="recontrasena" maxlength="100" placeholder="Re-Contrase&ntilde;a"  value="" id="repassword" required=""/></td></tr>
                    </table>
                    <table id ="send-edit-form">
                        <tr><td></td><td colspan="2"><input type="submit" name="send" value="Restablecer" /></td></tr>
                    </table>
                </g:form>
                </div>
            </div>
	</body>
</html>

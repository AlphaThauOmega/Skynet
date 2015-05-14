<!DOCTYPE html>
    <html xmlns="http://www.w3.org/1999/xhtml" xml:lang="es" lang="es">
        <head>
            <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
            <meta name="author" content="Mijail Guti&amp;eacute;rrez"/>
            <meta name="author" content="Skynet"/>
            <title><g:layoutTitle default="Skynet"/></title>
            <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'icon.ico')}" />
            <link rel="stylesheet" type="text/css" href="${createLinkTo(dir:'css',file:'skynet.css')}" />
            <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true"></script>
            <script type="text/javascript" src="${createLinkTo(dir:'js',file:'jquery-1.11.0.min.js')}"></script>
            <script type="text/javascript" src="${createLinkTo(dir:'js',file:'skynet.js')}"></script>
            <script type="text/javascript">
			var home = "${createLink(uri: '/', absolute : 'true')}";
            </script>
            <g:layoutHead/>
	</head>
	<body>
            <div id="header">
                <p id="program-name" class="shadow-text">Skynet</p>
                <a id="close-user" class="hidden" href="${createLink(action:'cerrar', controller:'sesion', absolute="true")}">Cerrar Sesion</a>
                <a id="access-user" class="hidden" href="${createLink(action:'iniciar', controller:'sesion', absolute="true")}">Iniciar Sesion</a>
                <g:form controller="comercio" action="buscar">
                    <input type="text" name="buscar" placeholder="Buscar" value="" id="search-input" />
                    <input type="submit" value="send" class="hidden" />
                </g:form>
                <a id="advance-search" href="${createLink(action:'busquedaAvanzada', controller:'comercio', absolute="true")}">Busqueda avanzada</a>
                <a id="register" href="${createLink(action:'registrar', controller:'usuario', absolute="true")}">Registrar</a>
            </div>
            <div id="sesion-start" class="hidden">
                <div id="session-name">
                    <p id="greeting">Bienvenido</p>
                    <p id="greeting-name-user" class="shadow-text">${usuario?.nombre}</p>
                </div>
                <br/>
                <br/>
                <div id="admin-edit-user" class="admin-panel hidden">
                    <a href="${createLink(action:'editarUsuario', controller:'administrador', absolute="true")}">Editar registro Usuario</a>
                </div>
                <br/>
                <br/>
                <div id="admin-edit-store" class="admin-panel hidden">
                    <a href="${createLink(action:'editarComercio', controller:'administrador', absolute="true")}">Editar registro establecimiento</a>
                </div>
                <br/>
                <br/>
                <div id="admin-new-store" class="admin-panel hidden">
                    <a href="${createLink(action:'nuevoComercio', controller:'administrador', absolute="true")}">Registrar comercio</a>
                </div>
                <div id="user-edit-panel" class="user-panel hidden">
                    <a href="${createLink(action:'editar', controller:'usuario', absolute="true")}">Editar registro</a>
                </div>
                <br/>
            </div>
            <div id="container">
                <g:layoutBody/>
            </div>
            <div id="footer" >
                <i>Skynet</i><br/>
                &#xa9; <i>ATO</i> - 2015<br/>
                <h1 id="optimized-for" >&Eacute;sta p&aacute;gina se visualiza mejor con<i>Google Chrome</i></h1>
            </div>
	</body>
    </html>
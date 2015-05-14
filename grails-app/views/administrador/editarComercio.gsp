<html>
	<head>
		<meta name="layout" content="principal"/>
                <script src="//cdn.ckeditor.com/4.4.7/standard/ckeditor.js"></script>
                <script type="text/javascript" src="${createLinkTo(dir:'js',file:'comercio.js')}"></script>
        </head>
	<body>
            <div id="description">
                <g:if test="${editar != null}">
                    <p>Editar:</p>
                </g:if>
                <g:else>
                    <p>Selecciona un comercio</p>
                </g:else>
            </div>
            <div id="board">
                <g:if test="${editar != null}">
                    <g:render template="comercio"/>
                </g:if>
                <g:else>
                    <g:render template="comercios"/>
                </g:else>
            </div>
        </body>
</html>

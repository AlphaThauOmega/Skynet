<html>
	<head>
		<meta name="layout" content="principal"/>
                <script type="text/javascript" src="${createLinkTo(dir:'js',file:'comercio.js')}" ></script>
        </head>
	<body>
            <g:if test="${busqueda}">
                <div id="description">
                    <p>Busqueda Avanzada <br/> resultados:</p>
		</div>
                <g:render template="comercios" />
            </g:if>
            <g:else>
                <div id="description">
                    <p>Busqueda Avanzada</p>
		</div>
                <div id="board">
                    <div id="search-table">
                        <g:form  controller="comercio" action="busquedaAvanzada" params="[busqueda:true]">
                            <br/>
                            <p>Buscar por: </p>
                            <hr/>
                            <table>
                                <tr><td class="table-inf">Nombre: </td><td colspan="3" class="table-input"><input type="text" name="nombre" maxlength="100" placeholder="Nombre del local" value="" id="nombre" /></td></tr>
                                <tr><td class="table-inf">Tipo de comida: </td><td colspan="3" class="table-input"><input type="text" name="tipo" maxlength="100" placeholder="Tipo de comida" value="" id="tipo" /></td></tr>
                                <tr><td class="table-inf">Transporte cercano: </td><td colspan="3" class="table-input"><input type="text" name="estacion" maxlength="100" placeholder="Estacion" value="" id="estacion" /></td></tr>
                                <tr><td class="table-inf">Especialidad </td><td colspan="3" class="table-input"><input type="text" name="recomendada" maxlength="100" placeholder="Especialidad" value="" id="recomendada" /></td></tr>
                                <tr><td class="table-inf">Nombre de alguna comida: </td><td colspan="3" class="table-input"><input type="text" name="comida" maxlength="100" placeholder="Comida" value="" id="comida" /></td></tr>
                                <tr><td colspan="4" class="table-inf">Precios de : </td></tr>
                                <tr><td class="table-input-min"><input type="number" name="menorPrecio" placeholder="precio minimo" value="" id="menorPrecio" /></td><td style="padding:1%;">A</td><td class="table-input-min"><input type="number" name="mayorPrecio" placeholder="precio maximo" value="" id="mayorPrecio" /></td></tr>
                                <tr><td colspan="4" class="table-inf">Ubicacion: </td></tr>
                                <tr><td colspan="4"><div id="map" style="width:500px;height:400px"></div></td></tr>
                            </table>
                            <input id="search-longitude" type="number" name="longitud" class="hidden"  step="any" />
                            <input id="search-latitude" type="number" name="latitud" class="hidden"   step="any"/>
                            <script type="text/javascript">
                                getMap();
                            </script>
                            <table id ="send-advance">
                                <tr><td></td><td colspan="4"><input type="submit" name="search" value="Buscar" /></td></tr>
                            </table>
                        </g:form>
                    </div>
                </div>
            </g:else>
	</body>
</html>

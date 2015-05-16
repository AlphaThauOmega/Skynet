<div id="edit-new-store">
    <g:set var="url" value="${createLink(action:'nuevoComercio', controller:'administrador', params: [create:'true'], absolute:"true")}" />
    <g:set var="name" value="create-new-store"/>
    <g:set var="action" value="Registrar"/>
    <g:if test="${editar}">
        <img class="image" src="${createLink(action:'imagen', controller:'comercio', params: [id:editar?.id], absolute:"true")}" />
        <g:set var="url" value="${createLink(action:'editar', controller:'comercio', params: [id:editar?.id], absolute:"true")}" />
        <g:set var="name" value="edit-store"/>
        <g:set var="action" value="Editar"/>
    </g:if>
    <br/>
    <br/>
    <g:form name="${name}" url="${url}">
        <table>
            <tr><td class="table-inf">Nombre: </td><td colspan="3" class="table-input"><input type="text" name="nombre" maxlength="100" placeholder="${editar?.nombre}"/></td></tr>
            <tr><td colspan="4" class="table-inf">Especialidad(comida): </td></tr>
            <tr><td class="table-inf"><td class="table-input"><input type="text" name="recomendada" maxlength="30" placeholder="${(editar?.recomendada?.nombre) ?: 'nombre'}" style="margin-left:-70%"/></td><td class="table-input"><input type="text" name="recomendadaTipo" maxlength="30"  placeholder="${(editar?.recomendada?.tipo) ?: 'tipo'}" style="margin-left:-100%"/></td><td class="table-input"><input type="number" name="recomendadaPrecio"  placeholder="${(editar?.recomendada?.precio) ?: 'precio'}" style="margin-left:-360%;width:50px"/></td></tr>
            <tr><td colspan="4" class="table-inf">Rango precios: </td></tr>
            <tr><td class="table-inf"></td><td class="table-input"><input type="number" step="any" name="menorPrecio" placeholder="${(editar?.menorPrecio) ?: 'menor precio'}" id="menorPrecio" /></td><td class="table-input"><input type="number" step="any" name="mayorPrecio" placeholder="${(editar?.mayorPrecio) ?: 'mayor precio'}" id="mayorPrecio"  style="margin-left:-20%"/></td></tr>
            <tr><td class="table-inf" >Comedor: </td><td colspan="4" class="table-input" style="color:grey"><g:if test="${editar?.comedor == true}"><input type="checkbox" name="comedor" checked></g:if><g:else><input type="checkbox" name="comedor"></g:else></td></tr>
            <tr><td class="table-inf" >Ba&#241o: </td><td colspan="4" class="table-input" style="color:grey"><g:if test="${editar?.bano == true}"><input type="checkbox" name="bano" checked></g:if><g:else><input type="checkbox" name="bano"></g:else></td></tr>
            <tr><td class="table-inf" >Direccion: </td><td colspan="4" class="table-input" style="color:grey"><input type="text" name="direccion" maxlength="100" placeholder="${(editar?.direccion) ?: 'direccion'}"/></td></tr>
            <tr><td class="table-inf" >Pagina web: </td><td colspan="4" class="table-input"><input type="text" name="pagina" maxlength="100" placeholder="${(editar?.pagina) ?: 'pagina web'}"/></td></tr>
            <tr><td class="table-inf" >Imagen: </td><td colspan="4" class="table-input"><input type="file" name="imagen" value="nueva"/></td></tr>
            <tr><td class="table-inf" colspan="5">Ubicacion: </td></tr>
            <tr><td colspan="5"><div id="map" style="width:500px;height:400px"></div></td></tr>
        </table>
    </g:form>
    <script type="text/javascript">
        <g:if test="${editar?.latitud && editar?.longitud}">
            getMap(${editar?.latitud},${editar?.longitud}, true);
        </g:if>
        <g:else>
            getMap();
        </g:else>
    </script>
    <p> Agregar comida:</p>
    <g:form name="add-food">
        <table id="add-meal">  
            <tr><th>Nombre:</th><th>Tipo:</th><th>precio:</th></tr>
        <g:if test="${editar}">
            <g:each var="comida" in="${editar?.comidas}">
                <g:if test="${comida.id != editar.recomendada.id}">
                    <tr id="comida-${comida.id}"><td class="table-inf" >${comida?.nombre}</td><td class="table-inf" >${comida?.tipo}</td><td class="table-inf"> ${comida?.precio}</td><td class="table-inf"><div class="delete-row" onclick="deleteItem('comida', ${comida?.id}, '${createLink(action:'eliminarComida', controller:'comercio', params: [comercio:editar?.id], absolute:"true")}')"></div></td></tr>
                </g:if>
            </g:each>
        </g:if>
        </table>
    </g:form>
    <div>
        <input type="button" id="add-new-meal" value="Agregar"/>
    </div>
    <p> Agregar transporte:</p>
    <g:form name="add-transports">
        <table id="add-station">
            <tr><th>Estacion:</th><th>Transporte:</th></tr>
            <g:if test="${editar}">
                <g:each var="estacion" in="${editar?.estaciones}">
                    <tr id="estacion-${estacion.id}"><td class="table-inf" >${estacion?.nombre}</td><td class="table-inf"> ${estacion?.transporte?.nombre}</td><td class="table-inf"><div class="delete-row" onclick="deleteItem('estacion', ${estacion?.id}, '${createLink(action:'eliminarEstacion', controller:'comercio', params: [comercio:editar?.id], absolute:"true")}')"></div></td></tr>
                </g:each>
            </g:if>
        </table>
    </g:form>
    <div>
        <input type="button" id="add-new-station" value="Agregar"/>
    </div>
    <br/>
    <button type="submit" form="${name}" value="${action}" style="margin-left:50%;margin-bottom:10%">${action}</button>
    <br/>
    <g:if test="${editar}">
        <g:form controller="administrador" action="eliminar" name="eliminar-comercio" params="[id:editar?.id, tipo:'comercio']">
            <table id ="send-delete-form">
                <tr><td><input type="submit" name="delete" value="Eliminar" /></td></tr>
            </table>
        </g:form>
    </g:if>
    <br/>
    <g:if test="${editar}">
        <p> comentarios </p>
        <div id="add-comment">
            <g:each var="comentario" in="${comentarios}">
                <div id="comentario-${comentario.id}" class="wrapper-comment" onclick="deleteItem('comentario', ${comentario?.id}, '${createLink(action:'eliminarComentario', controller:'comercio', params: [comercio:editar?.id], absolute:"true")}')">
                    <div class="comment-user"> <div class="comment-user-name">${comentario?.usuario?.nombre}</div><div class="comment-user-date"><g:formatDate date="${comentario?.fecha}" type="datetime" style="LONG" timeStyle="SHORT"/></div></div>
                    <div class="comment-user-text">${raw(comentario?.comentario)}</div>
                </div>
            </g:each>
        </div>
        <br/>
        <br/>
    </g:if>
</div>
<div id="description">
    <p>${comercio.nombre}</p>
</div>
<div id="board">
    <div id="${comercio?.id}" class="in-store" style="cursor:default">
        <img class="image" src="${createLink(action:'imagen', controller:'comercio', params: [id:comercio?.id], absolute:"true")}" />
        <br/>
        <table>
            <tr><td class="table-inf">Especialidad: </td><td class="table-input">${comercio?.recomendada?.nombre}</td></tr>
            <tr><td class="table-inf">Rango de precios: </td><td class="table-input">&#36;${comercio?.menorPrecio} - &#36;${comercio?.mayorPrecio}</td></tr>
            <tr><td class="table-inf">Comedor: </td><td class="table-input"><g:if test="${comercio?.comedor == true}"><input type="checkbox" name="comedor" checked disabled></g:if><g:else><input type="checkbox" name="comedor" disabled></g:else></td></tr>
            <tr><td class="table-inf">Ba&#241o: </td><td class="table-input" ><g:if test="${comercio?.bano == true}"><input type="checkbox" name="bano" checked disabled></g:if><g:else><input type="checkbox" name="bano" disabled></g:else></td></tr>
            <tr><td class="table-inf">Direccion: </td><td class="table-input"> ${comercio?.direccion}</td></tr>
            <tr><td class="table-inf">Pagina web: </td><td class="table-input"> ${comercio?.pagina} </td></tr>
            <tr><td class="table-inf">Ubicacion: </td></tr>
            <tr><td colspan="3"><div id="map"></div></td></tr>
        </table>
        <script type="text/javascript">
            <g:if test="${comercio?.latitud && comercio?.longitud}">
                getMap(${comercio?.latitud},${comercio?.longitud}, true);
            </g:if>
            <g:else>
                getMap();
            </g:else>
        </script>
        <br/>
        <g:form name="rate" controller="comercio" action="calificar" params="[id:comercio?.id]">
        <table id="set-rate" class="hidden">
            <tr><td class="table-inf">Calificar: </td><td class="table-input"><g:each in="[1,2,3,4,5]" var="i"> <g:if test="${i <= comercio?.calificacion}"><div class="rating-star-on rating-star"><div id="rating-start-${i}" onclick="setRating(this, ${i})"></div></div></g:if><g:else><div class="rating-star-off rating-star"><div id="rating-start-${i}" onclick="setRating(this, ${i})"></div></div></g:else></g:each></td></tr>
        </table>
        </g:form>
        <table id="view-rate" class="rating">
            <tr><td class="table-inf">Calificacion: </td><td class="table-input"><g:each in="[1,2,3,4,5]" var="i"> <g:if test="${i <= comercio?.calificacion}"><div class="rating-star-on"><div></div></div></g:if><g:else><div class="rating-star-off"><div></div></div></g:else></g:each></td></tr>
        </table>
        <p> comidas:</p>
        <table id="add-meal">  
            <tr><th>Nombre:</th><th>Tipo:</th><th>precio:</th></tr>
            <g:if test="${comercio}">
                <g:each var="comida" in="${comercio.comidas}">
                    <g:if test="${comida.id != comercio.recomendada.id}">
                        <tr><td class="table-inf" >${comida.nombre}</td><td class="table-inf" >${comida.tipo}</td><td class="table-inf"> ${comida.precio}</td></tr>
                    </g:if>
                </g:each>
            </g:if>
        </table>
        <br/>
        <p> Transporte:</p>
        <table id="add-station">
            <tr><th>Estacion:</th><th>Transporte:</th></tr>
            <g:if test="${comercio}">
                <g:each var="estacion" in="${comercio?.estaciones}">
                    <tr><td class="table-inf" >${estacion?.nombre}</td><td class="table-inf"> ${estacion?.transporte?.nombre}</td></tr>
                </g:each>
            </g:if>
        </table>
        <br/>
        <br/>
        <div id="share" class="hidden">
        <p> Compartir: </p>
        <ul>
            <li><div id="facebook">facebook</div></li>
            <li><div id="twitter">twitter</div></li>
        </ul>
        </div>
        <br/>
        <div id="new-comment" class="hidden">
            <p>Agregar Comentario: </p>
            <g:form name="comment" controller="comercio" action="comentar" params="[id:comercio?.id]">
                <textarea class="ckeditor" id="editor" name="editor" rows="4" cols="50" form="comentar"></textarea>
                <input type="submit" name="new_wannabie" value="Comentar" id="new-comment" />
            </g:form>
        </div>
        <br/>
        <div id="comment-users">
            <p> Comentarios: </p>
            <div id="add-comment">
                <g:each var="comentario" in="${comentarios}">
                    <div class="comment-user"> <div class="comment-user-name">${comentario?.usuario?.nombre}</div><div class="comment-user-date"><g:formatDate date="${comentario?.fecha}" type="datetime" style="LONG" timeStyle="SHORT"/></div></div>
                    <div class="comment-user-text">${raw(comentario?.comentario)}</div>
                </g:each>
            </div>
        </div>
    </div>
</div>
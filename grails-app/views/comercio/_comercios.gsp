<div id="board">
    <g:each var="comercio" in="${comercios}">
        <div id="${comercio?.id}" class="store" >
            <img class="image" src="${createLink(action:'imagen', controller:'comercio', params: [id:comercio?.id], absolute:"true")}" />
            <br/>
            <p class="store-name">${comercio?.nombre}</p>
            <table>
                <tr><td class="table-inf">Especialidad: </td><td class="table-input" style="color:grey">${comercio?.recomendada?.nombre}</td></tr>
                <tr><td class="table-inf">Rango de precios: </td><td class="table-input" style="color:grey">&#36;${comercio?.menorPrecio} - &#36;${comercio?.mayorPrecio}</td></tr>
                <tr><td class="table-inf">Comedor: </td><td class="table-input" style="color:grey"><g:if test="${comercio?.comedor == true}"> Si cuenta con uno </g:if><g:else>No cuenta con uno</g:else></td></tr>
                <tr><td class="table-inf">Baño: </td><td class="table-input" style="color:grey"> <g:if test="${comercio?.bano == true}"> Si tiene </g:if><g:else>No hay</g:else></td></tr>
                <tr><td class="table-inf">Direccion: </td><td class="table-input" style="color:grey">${comercio?.direccion}</td></tr>
                <tr><td class="table-inf">Pagina web: </td><td class="table-input"><a style="color:grey">${comercio?.pagina}</a></td></tr>
                <tr><td class="table-inf">Calificacion: </td><td class="table-input"><g:each in="[1,2,3,4,5]" var="i"> <g:if test="${i <= comercio?.calificacion}"><div class="rating-star-on"><div></div></div></g:if><g:else><div class="rating-star-off"><div></div></div></g:else></g:each></td></tr>
            </table>
            <br/>
            <hr/>
        </div>
    </g:each>
</div>
<div class="pagination">
    <g:paginate total="${total ?: 0}" />
</div>
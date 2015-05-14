<div id="board">
    <g:each var="usuario" in="${usuarios}">
        <div id="${comercio?.id}" class="user" onclick="goTo('${createLink(action:'editarUsuario', controller:'administrador', params: [id:usuario?.id])}')" >
            <br/>
            <table>
                <tr><td class="table-inf">Nombre usuario: </td><td class="table-input">${usuario?.nombreUsuario}</td></tr>
                <tr><td class="table-inf">Nombre: </td><td class="table-input">${usuario?.nombre}</td></tr>
                <tr><td class="table_inf">Correo: </td><td class="table-input">${usuario?.correo}</td></tr>
            </table>
            <br/>
            <hr>
        </div>
    </g:each>
</div>
<div class="pagination">
    <g:paginate total="${total ?: 0}" />
</div>

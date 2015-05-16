import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_skynet_administrador_comercio_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/administrador/_comercio.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
invokeTag('set','g',2,['var':("url"),'value':(createLink(action:'nuevoComercio', controller:'administrador', params: [create:'true'], absolute:"true"))],-1)
printHtmlPart(1)
invokeTag('set','g',3,['var':("name"),'value':("create-new-store")],-1)
printHtmlPart(1)
invokeTag('set','g',4,['var':("action"),'value':("Registrar")],-1)
printHtmlPart(1)
if(true && (editar)) {
printHtmlPart(2)
expressionOut.print(createLink(action:'imagen', controller:'comercio', params: [id:editar?.id], absolute:"true"))
printHtmlPart(3)
invokeTag('set','g',7,['var':("url"),'value':(createLink(action:'editar', controller:'comercio', params: [id:editar?.id], absolute:"true"))],-1)
printHtmlPart(4)
invokeTag('set','g',8,['var':("name"),'value':("edit-store")],-1)
printHtmlPart(4)
invokeTag('set','g',9,['var':("action"),'value':("Editar")],-1)
printHtmlPart(1)
}
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
expressionOut.print(editar?.nombre)
printHtmlPart(7)
expressionOut.print((editar?.recomendada?.nombre) ?: 'nombre')
printHtmlPart(8)
expressionOut.print((editar?.recomendada?.tipo) ?: 'tipo')
printHtmlPart(9)
expressionOut.print((editar?.recomendada?.precio) ?: 'precio')
printHtmlPart(10)
expressionOut.print((editar?.menorPrecio) ?: 'menor precio')
printHtmlPart(11)
expressionOut.print((editar?.mayorPrecio) ?: 'mayor precio')
printHtmlPart(12)
if(true && (editar?.comedor == true)) {
printHtmlPart(13)
}
else {
printHtmlPart(14)
}
printHtmlPart(15)
if(true && (editar?.bano == true)) {
printHtmlPart(16)
}
else {
printHtmlPart(17)
}
printHtmlPart(18)
expressionOut.print((editar?.direccion) ?: 'direccion')
printHtmlPart(19)
expressionOut.print((editar?.pagina) ?: 'pagina web')
printHtmlPart(20)
})
invokeTag('form','g',28,['name':(name),'url':(url)],1)
printHtmlPart(21)
if(true && (editar?.latitud && editar?.longitud)) {
printHtmlPart(22)
expressionOut.print(editar?.latitud)
printHtmlPart(23)
expressionOut.print(editar?.longitud)
printHtmlPart(24)
}
else {
printHtmlPart(25)
}
printHtmlPart(26)
createTagBody(1, {->
printHtmlPart(27)
if(true && (editar)) {
printHtmlPart(28)
for( comida in (editar?.comidas) ) {
printHtmlPart(29)
if(true && (comida.id != editar.recomendada.id)) {
printHtmlPart(30)
expressionOut.print(comida.id)
printHtmlPart(31)
expressionOut.print(comida?.nombre)
printHtmlPart(32)
expressionOut.print(comida?.tipo)
printHtmlPart(33)
expressionOut.print(comida?.precio)
printHtmlPart(34)
expressionOut.print(comida?.id)
printHtmlPart(35)
expressionOut.print(createLink(action:'eliminarComida', controller:'comercio', params: [comercio:editar?.id], absolute:"true"))
printHtmlPart(36)
}
printHtmlPart(28)
}
printHtmlPart(4)
}
printHtmlPart(37)
})
invokeTag('form','g',49,['name':("add-food")],1)
printHtmlPart(38)
createTagBody(1, {->
printHtmlPart(39)
if(true && (editar)) {
printHtmlPart(29)
for( estacion in (editar?.estaciones) ) {
printHtmlPart(40)
expressionOut.print(estacion.id)
printHtmlPart(31)
expressionOut.print(estacion?.nombre)
printHtmlPart(33)
expressionOut.print(estacion?.transporte?.nombre)
printHtmlPart(41)
expressionOut.print(estacion?.id)
printHtmlPart(35)
expressionOut.print(createLink(action:'eliminarEstacion', controller:'comercio', params: [comercio:editar?.id], absolute:"true"))
printHtmlPart(36)
}
printHtmlPart(28)
}
printHtmlPart(37)
})
invokeTag('form','g',63,['name':("add-transports")],1)
printHtmlPart(42)
expressionOut.print(name)
printHtmlPart(43)
expressionOut.print(action)
printHtmlPart(44)
expressionOut.print(action)
printHtmlPart(45)
if(true && (editar)) {
printHtmlPart(4)
createClosureForHtmlPart(46, 2)
invokeTag('form','g',75,['controller':("administrador"),'action':("eliminar"),'name':("eliminar-comercio"),'params':([id:editar?.id, tipo:'comercio'])],2)
printHtmlPart(1)
}
printHtmlPart(47)
if(true && (editar)) {
printHtmlPart(48)
for( comentario in (comentarios) ) {
printHtmlPart(49)
expressionOut.print(comentario.id)
printHtmlPart(50)
expressionOut.print(comentario?.id)
printHtmlPart(35)
expressionOut.print(createLink(action:'eliminarComentario', controller:'comercio', params: [comercio:editar?.id], absolute:"true"))
printHtmlPart(51)
expressionOut.print(comentario?.usuario?.nombre)
printHtmlPart(52)
invokeTag('formatDate','g',83,['date':(comentario?.fecha),'type':("datetime"),'style':("LONG"),'timeStyle':("SHORT")],-1)
printHtmlPart(53)
expressionOut.print(raw(comentario?.comentario))
printHtmlPart(54)
}
printHtmlPart(55)
}
printHtmlPart(56)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1431687748000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

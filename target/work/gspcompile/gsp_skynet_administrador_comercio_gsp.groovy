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
invokeTag('set','g',5,['var':("required"),'value':("required")],-1)
printHtmlPart(1)
if(true && (editar)) {
printHtmlPart(2)
invokeTag('set','g',7,['var':("required"),'value':("")],-1)
printHtmlPart(3)
expressionOut.print(createLink(action:'imagen', controller:'comercio', params: [id:editar?.id], absolute:"true"))
printHtmlPart(4)
invokeTag('set','g',9,['var':("url"),'value':(createLink(action:'editar', controller:'comercio', params: [id:editar?.id], absolute:"true"))],-1)
printHtmlPart(2)
invokeTag('set','g',10,['var':("name"),'value':("edit-store")],-1)
printHtmlPart(2)
invokeTag('set','g',11,['var':("action"),'value':("Editar")],-1)
printHtmlPart(1)
}
printHtmlPart(5)
createTagBody(1, {->
printHtmlPart(6)
expressionOut.print(editar?.nombre)
printHtmlPart(7)
expressionOut.print(required)
printHtmlPart(8)
expressionOut.print((editar?.recomendada?.nombre) ?: 'nombre')
printHtmlPart(9)
expressionOut.print(required)
printHtmlPart(10)
expressionOut.print((editar?.recomendada?.tipo) ?: 'tipo')
printHtmlPart(11)
expressionOut.print(required)
printHtmlPart(12)
expressionOut.print((editar?.recomendada?.precio) ?: 'precio')
printHtmlPart(13)
expressionOut.print(required)
printHtmlPart(14)
expressionOut.print((editar?.menorPrecio) ?: 'menor precio')
printHtmlPart(15)
expressionOut.print(required)
printHtmlPart(16)
expressionOut.print((editar?.mayorPrecio) ?: 'mayor precio')
printHtmlPart(17)
expressionOut.print(required)
printHtmlPart(18)
if(true && (editar?.comedor == true)) {
printHtmlPart(19)
}
else {
printHtmlPart(20)
}
printHtmlPart(21)
if(true && (editar?.bano == true)) {
printHtmlPart(22)
}
else {
printHtmlPart(23)
}
printHtmlPart(24)
expressionOut.print((editar?.direccion) ?: 'direccion')
printHtmlPart(7)
expressionOut.print(required)
printHtmlPart(25)
expressionOut.print((editar?.pagina) ?: 'pagina web')
printHtmlPart(7)
expressionOut.print(required)
printHtmlPart(26)
expressionOut.print(required)
printHtmlPart(27)
})
invokeTag('form','g',32,['name':(name),'url':(url)],1)
printHtmlPart(28)
if(true && (editar?.latitud && editar?.longitud)) {
printHtmlPart(29)
expressionOut.print(editar?.latitud)
printHtmlPart(30)
expressionOut.print(editar?.longitud)
printHtmlPart(31)
}
else {
printHtmlPart(32)
}
printHtmlPart(33)
createTagBody(1, {->
printHtmlPart(34)
if(true && (editar)) {
printHtmlPart(35)
for( comida in (editar?.comidas) ) {
printHtmlPart(36)
if(true && (comida.id != editar.recomendada.id)) {
printHtmlPart(37)
expressionOut.print(comida.id)
printHtmlPart(38)
expressionOut.print(comida?.nombre)
printHtmlPart(39)
expressionOut.print(comida?.tipo)
printHtmlPart(40)
expressionOut.print(comida?.precio)
printHtmlPart(41)
expressionOut.print(comida?.id)
printHtmlPart(42)
expressionOut.print(createLink(action:'eliminarComida', controller:'comercio', params: [comercio:editar?.id], absolute:"true"))
printHtmlPart(43)
}
printHtmlPart(35)
}
printHtmlPart(2)
}
printHtmlPart(44)
})
invokeTag('form','g',53,['name':("add-food")],1)
printHtmlPart(45)
createTagBody(1, {->
printHtmlPart(46)
if(true && (editar)) {
printHtmlPart(36)
for( estacion in (editar?.estaciones) ) {
printHtmlPart(47)
expressionOut.print(estacion.id)
printHtmlPart(38)
expressionOut.print(estacion?.nombre)
printHtmlPart(40)
expressionOut.print(estacion?.transporte?.nombre)
printHtmlPart(48)
expressionOut.print(estacion?.id)
printHtmlPart(42)
expressionOut.print(createLink(action:'eliminarEstacion', controller:'comercio', params: [comercio:editar?.id], absolute:"true"))
printHtmlPart(43)
}
printHtmlPart(35)
}
printHtmlPart(44)
})
invokeTag('form','g',67,['name':("add-transports")],1)
printHtmlPart(49)
expressionOut.print(name)
printHtmlPart(50)
expressionOut.print(action)
printHtmlPart(51)
expressionOut.print(action)
printHtmlPart(52)
if(true && (editar)) {
printHtmlPart(2)
createClosureForHtmlPart(53, 2)
invokeTag('form','g',79,['controller':("administrador"),'action':("eliminar"),'name':("eliminar-comercio"),'params':([id:editar?.id, tipo:'comercio'])],2)
printHtmlPart(1)
}
printHtmlPart(54)
if(true && (editar)) {
printHtmlPart(55)
for( comentario in (comentarios) ) {
printHtmlPart(56)
expressionOut.print(comentario.id)
printHtmlPart(57)
expressionOut.print(comentario?.id)
printHtmlPart(42)
expressionOut.print(createLink(action:'eliminarComentario', controller:'comercio', params: [comercio:editar?.id], absolute:"true"))
printHtmlPart(58)
expressionOut.print(comentario?.usuario?.nombre)
printHtmlPart(59)
invokeTag('formatDate','g',87,['date':(comentario?.fecha),'type':("datetime"),'style':("LONG"),'timeStyle':("SHORT")],-1)
printHtmlPart(60)
expressionOut.print(raw(comentario?.comentario))
printHtmlPart(61)
}
printHtmlPart(62)
}
printHtmlPart(63)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1431835485000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

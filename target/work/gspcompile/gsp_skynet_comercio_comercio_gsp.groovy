import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_skynet_comercio_comercio_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/comercio/_comercio.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
expressionOut.print(comercio.nombre)
printHtmlPart(1)
expressionOut.print(comercio?.id)
printHtmlPart(2)
expressionOut.print(createLink(action:'imagen', controller:'comercio', params: [id:comercio?.id], absolute:"true"))
printHtmlPart(3)
expressionOut.print(comercio?.recomendada?.nombre)
printHtmlPart(4)
expressionOut.print(comercio?.menorPrecio)
printHtmlPart(5)
expressionOut.print(comercio?.mayorPrecio)
printHtmlPart(6)
if(true && (comercio?.comedor == true)) {
printHtmlPart(7)
}
else {
printHtmlPart(8)
}
printHtmlPart(9)
if(true && (comercio?.bano == true)) {
printHtmlPart(10)
}
else {
printHtmlPart(11)
}
printHtmlPart(12)
expressionOut.print(comercio?.direccion)
printHtmlPart(13)
expressionOut.print(comercio?.pagina)
printHtmlPart(14)
if(true && (comercio?.latitud && comercio?.longitud)) {
printHtmlPart(15)
expressionOut.print(comercio?.latitud)
printHtmlPart(16)
expressionOut.print(comercio?.longitud)
printHtmlPart(17)
}
else {
printHtmlPart(18)
}
printHtmlPart(19)
createTagBody(1, {->
printHtmlPart(20)
for( i in ([1,2,3,4,5]) ) {
printHtmlPart(21)
if(true && (i <= comercio?.calificacion)) {
printHtmlPart(22)
expressionOut.print(i)
printHtmlPart(23)
expressionOut.print(i)
printHtmlPart(24)
}
else {
printHtmlPart(25)
expressionOut.print(i)
printHtmlPart(23)
expressionOut.print(i)
printHtmlPart(24)
}
}
printHtmlPart(26)
})
invokeTag('form','g',31,['name':("rate"),'controller':("comercio"),'action':("calificar"),'params':([id:comercio?.id])],1)
printHtmlPart(27)
for( i in ([1,2,3,4,5]) ) {
printHtmlPart(21)
if(true && (i <= comercio?.calificacion)) {
printHtmlPart(28)
}
else {
printHtmlPart(29)
}
}
printHtmlPart(30)
if(true && (comercio)) {
printHtmlPart(31)
for( comida in (comercio.comidas) ) {
printHtmlPart(32)
if(true && (comida.id != comercio.recomendada.id)) {
printHtmlPart(33)
expressionOut.print(comida.nombre)
printHtmlPart(34)
expressionOut.print(comida.tipo)
printHtmlPart(35)
expressionOut.print(comida.precio)
printHtmlPart(36)
}
printHtmlPart(31)
}
printHtmlPart(37)
}
printHtmlPart(38)
if(true && (comercio)) {
printHtmlPart(31)
for( estacion in (comercio?.estaciones) ) {
printHtmlPart(39)
expressionOut.print(estacion?.nombre)
printHtmlPart(35)
expressionOut.print(estacion?.transporte?.nombre)
printHtmlPart(40)
}
printHtmlPart(37)
}
printHtmlPart(41)
expressionOut.print(createLink(action:'facebook', controller:'comercio', absolute="true"))
printHtmlPart(42)
expressionOut.print(createLink(action:'twitter', controller:'comercio', absolute="true"))
printHtmlPart(43)
createClosureForHtmlPart(44, 1)
invokeTag('form','g',71,['name':("comment"),'controller':("comercio"),'action':("comentar"),'params':([id:comercio?.id])],1)
printHtmlPart(45)
for( comentario in (comentarios) ) {
printHtmlPart(46)
expressionOut.print(comentario?.usuario?.nombre)
printHtmlPart(47)
invokeTag('formatDate','g',78,['date':(comentario?.fecha),'type':("datetime"),'style':("LONG"),'timeStyle':("SHORT")],-1)
printHtmlPart(48)
expressionOut.print(raw(comentario?.comentario))
printHtmlPart(49)
}
printHtmlPart(50)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1431609221000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

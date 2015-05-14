import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_skynet_administrador_comercios_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/administrador/_comercios.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
for( comercio in (comercios) ) {
printHtmlPart(1)
expressionOut.print(comercio?.id)
printHtmlPart(2)
expressionOut.print(createLink(action:'imagen', controller:'comercio', params: [id:comercio?.id], absolute:"true"))
printHtmlPart(3)
expressionOut.print(comercio?.nombre)
printHtmlPart(4)
expressionOut.print(comercio?.recomendada?.nombre)
printHtmlPart(5)
expressionOut.print(comercio?.menorPrecio)
printHtmlPart(6)
expressionOut.print(comercio?.mayorPrecio)
printHtmlPart(7)
if(true && (comercio?.comedor == true)) {
printHtmlPart(8)
}
else {
printHtmlPart(9)
}
printHtmlPart(10)
if(true && (comercio?.bano == true)) {
printHtmlPart(11)
}
else {
printHtmlPart(12)
}
printHtmlPart(13)
expressionOut.print(comercio?.direccion)
printHtmlPart(14)
expressionOut.print(comercio?.pagina)
printHtmlPart(15)
for( i in ([1,2,3,4,5]) ) {
printHtmlPart(16)
if(true && (i <= comercio?.calificacion)) {
printHtmlPart(17)
}
else {
printHtmlPart(18)
}
}
printHtmlPart(19)
}
printHtmlPart(20)
invokeTag('paginate','g',22,['total':(total ?: 0)],-1)
printHtmlPart(21)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1430164544000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

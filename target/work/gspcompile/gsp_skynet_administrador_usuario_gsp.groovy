import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_skynet_administrador_usuario_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/administrador/_usuario.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
expressionOut.print(editar?.nombreUsuario)
printHtmlPart(2)
expressionOut.print(editar?.nombre)
printHtmlPart(3)
expressionOut.print(editar?.apellidoP)
printHtmlPart(4)
expressionOut.print(editar?.apellidoM)
printHtmlPart(5)
expressionOut.print(editar?.correo)
printHtmlPart(6)
})
invokeTag('form','g',19,['controller':("usuario"),'action':("editar"),'name':("editar-usuario"),'params':([id:editar?.id, editar:true])],1)
printHtmlPart(7)
createClosureForHtmlPart(8, 1)
invokeTag('form','g',24,['controller':("administrador"),'action':("eliminar"),'name':("eliminar-usuario"),'params':([id:editar?.id, tipo:'usuario'])],1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1430731854000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_skynet_layoutsprincipal_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/layouts/principal.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("Content-type"),'content':("text/html;charset=UTF-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("author"),'content':("Mijail Guti&amp;eacute;rrez")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("author"),'content':("Skynet")],-1)
printHtmlPart(1)
createTagBody(2, {->
createTagBody(3, {->
invokeTag('layoutTitle','g',7,['default':("Skynet")],-1)
})
invokeTag('captureTitle','sitemesh',7,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',7,[:],2)
printHtmlPart(2)
expressionOut.print(createLinkTo(dir:'images',file:'icon.ico'))
printHtmlPart(3)
expressionOut.print(createLinkTo(dir:'css',file:'skynet.css'))
printHtmlPart(4)
expressionOut.print(createLinkTo(dir:'js',file:'jquery-1.11.0.min.js'))
printHtmlPart(5)
expressionOut.print(createLinkTo(dir:'js',file:'skynet.js'))
printHtmlPart(6)
expressionOut.print(createLink(uri: '/', absolute : 'true'))
printHtmlPart(7)
invokeTag('layoutHead','g',16,[:],-1)
printHtmlPart(8)
})
invokeTag('captureHead','sitemesh',17,[:],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(9)
expressionOut.print(createLink(action:'cerrar', controller:'sesion', absolute="true"))
printHtmlPart(10)
expressionOut.print(createLink(action:'iniciar', controller:'sesion', absolute="true"))
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('form','g',26,['controller':("comercio"),'action':("buscar")],2)
printHtmlPart(13)
expressionOut.print(createLink(action:'busquedaAvanzada', controller:'comercio', absolute="true"))
printHtmlPart(14)
expressionOut.print(createLink(action:'registrar', controller:'usuario', absolute="true"))
printHtmlPart(15)
expressionOut.print(usuario?.nombre)
printHtmlPart(16)
expressionOut.print(createLink(action:'editarUsuario', controller:'administrador', absolute="true"))
printHtmlPart(17)
expressionOut.print(createLink(action:'editarComercio', controller:'administrador', absolute="true"))
printHtmlPart(18)
expressionOut.print(createLink(action:'nuevoComercio', controller:'administrador', absolute="true"))
printHtmlPart(19)
expressionOut.print(createLink(action:'editar', controller:'usuario', absolute="true"))
printHtmlPart(20)
invokeTag('layoutBody','g',56,[:],-1)
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',63,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1430296775000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}

<#-- taglib -->
<#global spring=JspTaglibs["http://www.springframework.org/tags"] />
<#global form=JspTaglibs["http://www.springframework.org/tags/form"] />

<#-- properties -->
<#global basepath>${req.contextPath}</#global>
<#global host="${getWebEnv('server.host.static')}" />
<#global mediaserver="${getWebEnv('server.path.media')}" />
<#global vbamWebServer="${getWebEnv('server.path.vbamWeb')}" />
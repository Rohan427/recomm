/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.business.error.ErrorBean;

/**
 *
 * @author pallen
 */
public class ServletParams
{
    private ErrorBean error = new ErrorBean();
    private String system = null;
    private ServletContext context = null;
    private String uri = null;
    private HttpServletRequest request = null;
    private HttpServletResponse response = null;

    public ServletParams (ErrorBean error,
                          String system,
                          ServletContext context,
                          String uri,
                          HttpServletRequest request,
                          HttpServletResponse response
                         )
    {
        this.context = context;
        this.error = error;
        this.request = request;
        this.response = response;
        this.system = system;
        this.uri = uri;
    }

    public ServletParams (ErrorBean error,
                          ServletContext context,
                          HttpServletRequest request,
                          HttpServletResponse response
                         )
    {
        this.context = context;
        this.error = error;
        this.request = request;
        this.response = response;
        this.uri = request.getRequestURI();
    }

    /**
     * @return the error
     */
    public ErrorBean getError()
    {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError (ErrorBean error)
    {
        this.error = error;
    }

    /**
     * @return the system
     */
    public String getSystem()
    {
        return system;
    }

    /**
     * @param system the system to set
     */
    public void setSystem (String system)
    {
        this.system = system;
    }

    /**
     * @return the context
     */
    public ServletContext getContext()
    {
        return context;
    }

    /**
     * @param context the context to set
     */
    public void setContext (ServletContext context)
    {
        this.context = context;
    }

    /**
     * @return the uri
     */
    public String getUri()
    {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri (String uri)
    {
        this.uri = uri;
    }

    /**
     * @return the request
     */
    public HttpServletRequest getRequest()
    {
        return request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest (HttpServletRequest request)
    {
        this.request = request;
    }

    /**
     * @return the response
     */
    public HttpServletResponse getResponse()
    {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse (HttpServletResponse response)
    {
        this.response = response;
    }
}

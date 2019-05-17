/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public class Tracking implements Filter
{
    private static final boolean DEBUG = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    private FilterConfig filterConfig = null;
    HttpServletRequest myRequest = null;

    public Tracking()
    {
    }

    /**
     * Init method for this filter
     */
    public void init (FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;

        if (filterConfig != null)
        {
            if (DEBUG)
            {
                log ("Tracking: Initializing filter");
                System.out.println ("Tracking: Initializing filter");
            }
            // else do nothing
        }
    }

    private void doBeforeProcessing (ServletRequest request, ServletResponse response)
    throws IOException, ServletException
    {
        myRequest = (HttpServletRequest)request;

        if (DEBUG)
        {
            log ("Tracking: doBeforeProcessing");
            System.out.println ("Tracking: DoBeforeProcessing");
            System.out.println ("\tClient " + getClientIp (myRequest) + " for " + myRequest.getContextPath() + myRequest.getRequestURI());
        }
        // else do nothing

        log (myRequest.getContextPath() + myRequest.getRequestURI());
    }

    private static String getClientIp (HttpServletRequest request)
    {
        String remoteAddr = "";

        if (request != null)
        {
            remoteAddr = request.getHeader ("X-FORWARDED-FOR");

            if (remoteAddr == null || "".equals (remoteAddr))
            {
                remoteAddr = request.getRemoteAddr();
            }
            // else do nothing
        }
        // else do nothing

        return remoteAddr;
    }

    /**
     *
     * @param request  The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain    The filter chain we are processing
     *
     * @exception IOException      if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter (ServletRequest request, ServletResponse response,
                          FilterChain chain)
    throws IOException, ServletException
    {
        Throwable problem = null;

        if (DEBUG)
        {
            log ("Tracking: doFilter()");
            System.out.println ("Tracking: doFilter()");
        }
        // else do nothing

        doBeforeProcessing (request, response);

        try
        {
            chain.doFilter (request, response);
        }
        catch (Throwable t)
        {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace ();
        }

        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null)
        {
            if (problem instanceof ServletException)
            {
                throw (ServletException)problem;
            }
            // else do nothing

            if (problem instanceof IOException)
            {
                throw (IOException)problem;
            }
            // else do nothing

            sendProcessingError (problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig()
    {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig (FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy()
    {
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString()
    {
        if (filterConfig == null)
        {
            return ("Tracking()");
        }

        StringBuffer sb = new StringBuffer ("Tracking(");
        sb.append (filterConfig);
        sb.append (")");
        return (sb.toString());
    }

    private void sendProcessingError (Throwable t, ServletResponse response)
    {
        String stackTrace = getStackTrace (t);

        if (stackTrace != null &&  ! stackTrace.equals (""))
        {
            try
            {
                response.setContentType ("text/html");
                PrintStream ps = new PrintStream (response.getOutputStream());
                PrintWriter pw = new PrintWriter (ps);
                pw.print ("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print ("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print (stackTrace);
                pw.print ("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            }
            catch (Exception ex)
            {
            }
        }
        else
        {
            try
            {
                PrintStream ps = new PrintStream (response.getOutputStream());
                t.printStackTrace (ps);
                ps.close();
                response.getOutputStream().close();
            }
            catch (Exception ex)
            {
            }
        }
    }

    public static String getStackTrace (Throwable t)
    {
        String stackTrace = null;

        try
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter (sw);
            t.printStackTrace (pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        }
        catch (Exception ex)
        {
        }

        return stackTrace;
    }

    public void log (String msg)
    {
        if (filterConfig != null)
        {
            filterConfig.getServletContext().log (msg);
        }
        // else do nothing
    }
}

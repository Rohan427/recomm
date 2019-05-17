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
import javax.servlet.http.HttpServletResponse;


public class Compression implements Filter
{
    private static final boolean DEBUG = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured.
    private FilterConfig filterConfig = null;

    public Compression()
    {
    }

    /**
     * Init method for this filter
     * @param filterConfig
     */
    @Override
    public void init (FilterConfig filterConfig)
    {
        this.filterConfig = filterConfig;

        if (filterConfig != null)
        {
            if (DEBUG)
            {
                log ("Compression: Initializing filter");
                System.out.println ("Compression: Initializing filter");
            }
        }
        else
        {
            if (DEBUG)
            {
                System.out.println ("Compression: filterConfig not initialized");
            }
        }
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
    @Override
    public void doFilter (ServletRequest request,
                          ServletResponse response,
                          FilterChain chain
                         )
                         throws IOException, ServletException
    {
        HttpServletRequest  httpRequest  = (HttpServletRequest)  request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        GZipResponseWrapper gzipResponse;
        Throwable problem = null;

        if (DEBUG)
        {
            log ("Compression: doFilter()");
            System.out.println ("Compression:doFilter()");
        }
        // else do nothing

        if (acceptsGZipEncoding (httpRequest))
        {
            httpResponse.addHeader ("Content-Encoding", "gzip");
            gzipResponse = new GZipResponseWrapper (httpResponse, filterConfig, DEBUG);

            try
            {
                chain.doFilter (request, gzipResponse);
            }
            catch (Throwable t)
            {
                // If an exception is thrown somewhere down the filter chain,
                // we still want to execute our after processing, and then
                // rethrow the problem after that.
                problem = t;

                if (DEBUG)
                {
                    log (t.getLocalizedMessage());
                    t.printStackTrace();
                }
                // else do nothing
            }

            gzipResponse.close();
        }
        else
        {
            chain.doFilter (request, response);
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

    private boolean acceptsGZipEncoding (HttpServletRequest httpRequest)
    {
      String acceptEncoding = httpRequest.getHeader ("Accept-Encoding");

      return acceptEncoding != null && acceptEncoding.indexOf ("gzip") != -1;
  }

    /**
     * Return the filter configuration object for this filter.
     * @return
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
    @Override
    public void destroy()
    {
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString()
    {
        StringBuffer sb;

        if (filterConfig == null)
        {
            sb = new StringBuffer ("Compression()");
        }
        else
        {
            sb = new StringBuffer ("Compression(");
            sb.append (filterConfig);
            sb.append (")");
        }

        return (sb.toString ());
    }

    private void sendProcessingError (Throwable t, ServletResponse response)
    {
        String stackTrace = getStackTrace (t);
        PrintStream ps;
        PrintWriter pw;

        if (stackTrace != null &&  ! stackTrace.equals (""))
        {
            try
            {
                response.setContentType ("text/html");
                ps = new PrintStream (response.getOutputStream());
                pw = new PrintWriter (ps);
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
                ;//TODO: Process it?
            }
        }
        else
        {
            try
            {
                ps = new PrintStream (response.getOutputStream());
                t.printStackTrace (ps);
                ps.close();
                response.getOutputStream().close ();
            }
            catch (Exception ex)
            {
                ;//TODO: Process it?
            }
        }
    }

    public static String getStackTrace (Throwable t)
    {
        String stackTrace = null;
        StringWriter sw;
        PrintWriter pw;

        try
        {
            sw = new StringWriter ();
            pw = new PrintWriter (sw);
            t.printStackTrace (pw);
            pw.close ();
            sw.close ();
            stackTrace = sw.getBuffer ().toString ();
        }
        catch (Exception ex)
        {
            ;//TODO: Process it?
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

package filters;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public class GZipResponseWrapper extends HttpServletResponseWrapper
{
    private GZipOutputStream gzipOutputStream = null;
    private PrintWriter printWriter = null;
    private boolean debug = false;
    FilterConfig filterConfig = null;

    /**
     *
     * @param response
     * @param debug
     * @throws IOException
     */
    public GZipResponseWrapper (HttpServletResponse response, FilterConfig filterConfig, boolean debug)
                               throws IOException
    {
        super (response);
        this.filterConfig = filterConfig;
        this.debug = debug;
    }

    /**
     *
     * @throws IOException
     */
    public void close() throws IOException
    {
        if (this.printWriter != null)
        {
            this.printWriter.close();
        }
        // else do nothing

        if (this.gzipOutputStream != null)
        {
            this.gzipOutputStream.close();
        }
        // else do nothing
    }


    /**
     * Flush OutputStream or PrintWriter
     *
     * @throws IOException
     */

    @Override
    public void flushBuffer() throws IOException
    {
        if (this.printWriter != null)
        {
            this.printWriter.flush();
        }
        // else do nothing

        try
        {
            if (this.gzipOutputStream != null)
            {
                this.gzipOutputStream.flush();
            }
            // else do nothing

            try
            {
                super.flushBuffer();
            }
            catch (IOException e)
            {
                if (debug)
                {
                    System.out.println (e.getLocalizedMessage());
                }
                // else do nothing

                log (e.getLocalizedMessage());
            }
        }
        catch (IOException e)
        {
            if (debug)
            {
                System.out.println (e.getLocalizedMessage());
            }
            // else do nothing

            log (e.getLocalizedMessage());
        }
    }

    /**
     *
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @Override
    public ServletOutputStream getOutputStream() throws IllegalStateException, IOException
    {
        if (this.printWriter != null)
        {
            throw new IllegalStateException ("PrintWriter obtained already - cannot get OutputStream");
        }
        // else do nothing

        if (this.gzipOutputStream == null)
        {
            this.gzipOutputStream = new GZipOutputStream (getResponse().getOutputStream());
        }
        // else do nothing

        return this.gzipOutputStream;
    }

    /**
     *
     * @return
     * @throws IOException
     * @throws IllegalStateException
     */
    @Override
    public PrintWriter getWriter() throws IOException, IllegalStateException
    {
       if (this.printWriter == null && this.gzipOutputStream != null)
       {
            throw new IllegalStateException ("OutputStream obtained already - cannot get PrintWriter");
       }
       // else do nothing

       if (this.printWriter == null)
       {
            this.gzipOutputStream = new GZipOutputStream (getResponse().getOutputStream());
            this.printWriter = new PrintWriter (new OutputStreamWriter (this.gzipOutputStream,
                                                                        getResponse().getCharacterEncoding()
                                                                       )
                                               );
       }
       // else do nothing

       return this.printWriter;
    }

    /**
     *
     * @param len
     */
    @Override
    public void setContentLength (int len)
    {
        //ignore, since content length of zipped content
        //does not match content length of unzipped content.
    }

    private void log (String message)
    {
        filterConfig.getServletContext().log (message);
    }
}

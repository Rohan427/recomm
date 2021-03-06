/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recomm.filters;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public class GZipOutputStream extends ServletOutputStream
{
    private GZIPOutputStream gzipOutputStream = null;
    WriteListener writeListener = null;

    public GZipOutputStream (OutputStream output)
                             throws IOException
    {
        super();
        this.gzipOutputStream = new GZIPOutputStream (output);
    }

    @Override
    public void close() throws IOException
    {
        this.gzipOutputStream.close();
    }

    @Override
    public void flush() throws IOException
    {
        this.gzipOutputStream.flush();
    }

    @Override
    public void setWriteListener (WriteListener writeListener)
    {
        this.writeListener = writeListener;
    }

    @Override
    public boolean isReady()
    {
        return true;
    }

    @Override
    public void write (byte b[]) throws IOException
    {
        this.gzipOutputStream.write (b);
    }

    @Override
    public void write (byte b[], int off, int len) throws IOException
    {
        this.gzipOutputStream.write (b, off, len);
    }

    @Override
    public void write (int b) throws IOException
    {
        this.gzipOutputStream.write (b);
    }
}

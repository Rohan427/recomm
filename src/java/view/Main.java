/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.business.error.ErrorBean;
import model.domain.users.Users;
import model.service.util.ServletParams;

/**
 *
 * @author Paul G. Allen <pgallen@gmail.com>
 */
public class Main extends HttpServlet
{
    private static final long serialVersionUID = 8455437397418363235L;
    
    String userID = "Regis";
    String password = "regisedu";
    String resultPage = "/index.xhtml";
    private Users userInfo = null;
    
    ServletParams params;
        
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest (HttpServletRequest newRequest, HttpServletResponse newResponse) throws ServletException, IOException
    {
        params = new ServletParams (new ErrorBean(), getServletConfig().getServletContext(), newRequest, newResponse);
        params.getResponse().setContentType ("text/html;charset=UTF-8");
        
        // Check if session valid
        if (params.getRequest().getSession (false) == null)
        {
            userInfo = new Users();
            params.getRequest().getSession().setAttribute ("userBean", userInfo);
            params.getRequest().setAttribute ("expired", true);
        }
        // Session valid, so continue
        else
        {
            ;//Do nothing
        }
        
        completeRequest();
    }
    
    private void completeRequest() throws ServletException, IOException
    {
        try
        {
            params.getContext().getRequestDispatcher (resultPage).forward (params.getRequest(), params.getResponse());
        }
        catch (ServletException | IOException ex)
        {
            params.getContext().log ("Authentication: Exception.\n\n", ex);

            if (params.getError() != null)
            {
                params.getRequest().getSession().removeAttribute ("errorMsg");
                params.getRequest().getSession().setAttribute ("errorMsg", params.getError());
                params.getError().generateError (params.getContext(),
                                                 params.getRequest(),
                                                 params.getResponse(),
                                                 "Authentication",
                                                 params.getUri(),
                                                 ErrorBean.ERROR_GENERAL_ERROR,
                                                 "Requested page not found",
                                                 "/genError.xhtml",
                                                 ex.getMessage(),
                                                 ex, null
                                                );
            }
        }
    }
    
    private void badRequest() throws ServletException, IOException
    {
        params.getContext().log ("Authentication: Bad Request.\n\n");
            
        if (params.getError() != null)
        {
            params.getRequest().getSession().removeAttribute ("errorMsg");
            params.getRequest().getSession().setAttribute ("errorMsg", params.getError());
            params.getError().generateError (params.getContext(),
                                             params.getRequest(),
                                             params.getResponse(),
                                             "Authentication",
                                             params.getUri(),
                                             ErrorBean.ERROR_INVALID_URI,
                                             "Requested page not found",
                                             "/genError.xhtml",
                                             null,
                                             null, null
                                            );
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        processRequest (request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
        processRequest (request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo ()
    {
        return "Short description";
    }// </editor-fold>

}

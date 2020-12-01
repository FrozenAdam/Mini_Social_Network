/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.controller;

import org.apache.log4j.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author theFrozenAdam
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);
    private static final String LOGIN = "LoginController";
    private static final String SIGNUP = "RegisterController";
    private static final String LOGOUT = "LogoutController";
    private static final String SEARCH = "SearchArticleController";
    private static final String POST = "PostArticleController";
    private static final String DETAIL = "DetailsController";
    private static final String COMMENT = "PostCommentController";
    private static final String DELETE_COMMENT = "DeleteCommentController";
    private static final String DELETE_ARTICLE = "DeleteArticleController";
    private static final String EMOTE = "EmoteController";
    private static final String DELETE_NOTIFICATION = "DeleteNotificationController";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN;
        try {
            String action = request.getParameter("action");
            if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("Register")) {
                url = SIGNUP;
            } else if (action.equals("Logout")) {
                url = LOGOUT;
            } else if (action.equals("Search Article")) {
                url = SEARCH;
            } else if (action.equals("Post Article")) {
                url = POST;
            } else if (action.equals("Details")) {
                url = DETAIL;
            } else if (action.equals("Comment")) {
                url = COMMENT;
            } else if (action.equals("Delete Comment")) {
                url = DELETE_COMMENT;
            } else if (action.equals("Delete Article")) {
                url = DELETE_ARTICLE;
            } else if (action.equals("Emote")) {
                url = EMOTE;
            } else if (action.equals("Click to Remove")) {
                url = DELETE_NOTIFICATION;
            } else {
                url = LOGIN;
                request.setAttribute("ERROR", "Invalid action");
            }
        } catch (Exception e) {
            LOGGER.error("Error at MainController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

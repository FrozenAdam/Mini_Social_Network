/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import system.daos.CommentDAO;
import system.daos.NotificationDAO;
import system.dtos.CommentDTO;
import system.dtos.NotificationDTO;

/**
 *
 * @author Admin
 */
public class PostCommentController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PostCommentController.class);
    private static final String SUCCESS = "DetailsController";

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
        String url = SUCCESS;
        try {
            int id = Integer.parseInt(request.getParameter("articleID"));
            String postBy = request.getParameter("txtCommentPostBy");
            String comment = request.getParameter("txtComment");
            int status = 1;
            CommentDAO dao = new CommentDAO();
            CommentDTO dto = new CommentDTO(id, postBy, comment, status);
            if (dao.insertComment(dto)) {
                NotificationDAO notiDAO = new NotificationDAO();
                NotificationDTO notiDTO = new NotificationDTO(notiDAO.getTotalNotification(), id, postBy, "COMMENT");
                notiDAO.insertNotification(notiDTO);
                url = SUCCESS;
            }
        } catch (Exception e) {
            LOGGER.error("Error at PostCommentController: " + e.getMessage());
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

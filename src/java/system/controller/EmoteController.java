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
import system.daos.EmoteDAO;
import system.daos.NotificationDAO;
import system.dtos.EmoteDTO;
import system.dtos.NotificationDTO;

/**
 *
 * @author Admin
 */
public class EmoteController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(EmoteController.class);

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
        try {
            EmoteDAO dao = new EmoteDAO();
            NotificationDAO notiDAO = new NotificationDAO();
            int id = dao.getTotalEmoteID();
            int articleID = Integer.parseInt(request.getParameter("articleID"));
            String user = request.getParameter("accountEmail");
            String which = request.getParameter("which");
            EmoteDTO check = dao.checkExistEmote(articleID, user);
            if (which.equals("Like")) {
                if (check == null) { //Haven't react yet
                    EmoteDTO dto = new EmoteDTO(articleID, user, true, false);
                    dao.insertEmote(id, dto);
                    NotificationDTO notiDTO = new NotificationDTO(notiDAO.getTotalNotification(), articleID, user, "LIKE");
                    notiDAO.insertNotification(notiDTO);
                } else if (check.isDislike() == true) { //From Dislike to Like
                    dao.changeEmoteToDeactive(check.getEmoteID());
                    EmoteDTO dto = new EmoteDTO(articleID, user, true, false);
                    dao.insertEmote(id, dto);
                    NotificationDTO notiDTO = new NotificationDTO(notiDAO.getTotalNotification(), articleID, user, "LIKE");
                    notiDAO.insertNotification(notiDTO);
                } else { //Unlike
                    dao.changeEmoteToDeactive(check.getEmoteID());
                }
            } else {
                if (check == null) { //Haven't react yet
                    EmoteDTO dto = new EmoteDTO(articleID, user, false, true);
                    dao.insertEmote(id, dto);
                    NotificationDTO notiDTO = new NotificationDTO(notiDAO.getTotalNotification(), articleID, user, "DISLIKE");
                    notiDAO.insertNotification(notiDTO);
                } else if (check.isLike() == true) { //From Like to Dislike
                    dao.changeEmoteToDeactive(check.getEmoteID());
                    EmoteDTO dto = new EmoteDTO(articleID, user, false, true);
                    dao.insertEmote(id, dto);
                    NotificationDTO notiDTO = new NotificationDTO(notiDAO.getTotalNotification(), articleID, user, "DISLIKE");
                    notiDAO.insertNotification(notiDTO);
                } else { //Unlike
                    dao.changeEmoteToDeactive(check.getEmoteID());
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error at EmoteController: " + e.getMessage());
        } finally {
            request.getRequestDispatcher("DetailsController").forward(request, response);
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

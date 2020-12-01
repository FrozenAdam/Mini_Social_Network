/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.controller;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.daos.ArticleDAO;
import system.daos.NotificationDAO;
import system.daos.RegistrationDAO;
import system.dtos.ArticleDTO;
import system.dtos.NotificationDTO;
import system.dtos.RegistrationDTO;
import system.utility.SHA256_Enryption;

/**
 *
 * @author theFrozenAdam
 */
public class LoginController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private static final String MAIN = "main.jsp";
    private static final String INVALID = "index.jsp";

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
        String url = INVALID;
        try {
            //1. Nhan request tu 1 client hoac tu 1 controller khac
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            password = SHA256_Enryption.sha256(password);
            //2. Goi model xu ly data
            RegistrationDAO dao = new RegistrationDAO();
            RegistrationDTO dto = dao.checkLogin(username, password);
            ArticleDAO article = new ArticleDAO();
            List<ArticleDTO> list = article.getAllArticle();
            List<NotificationDTO> notiList = null;
            NotificationDAO notiDAO = new NotificationDAO();
            notiList = notiDAO.notificationList(username);
            //3. Goi view tuong ung tra ve
            if (dto == null || dto.getStatus() == 3) {
                request.setAttribute("ERRORNOTE", "Invalid username or password");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("FULLNAME", dto.getFullname());
                session.setAttribute("EMAIL", dto.getUsername());
                request.setAttribute("INFO", list);
                request.setAttribute("NOTIFICATION", notiList);
                url = MAIN;
            }
        } catch (Exception e) {
            LOGGER.error("Error at LoginController: " + e.getMessage());
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

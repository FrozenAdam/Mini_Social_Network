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
import system.daos.RegistrationDAO;
import system.dtos.RegistrationDTO;
import system.dtos.RegistrationErrorObj;
import system.utility.SHA256_Enryption;

/**
 *
 * @author theFrozenAdam
 */
public class RegisterController extends HttpServlet {

    private static final String SUCCESS = "index.jsp";
    private static final String INVALID = "signup.jsp";

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
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            String fullname = request.getParameter("txtFullname");
            String confirm = request.getParameter("txtConfirmPassword");
            int status = 1;
            boolean valid = true;
            RegistrationErrorObj error = new RegistrationErrorObj();
            if (username.length() == 0) {
                valid = false;
                error.setUsernameError("Username can't be blank");
            }
            if (!username.matches("([a-zA-Z0-9]{3,30})(@)([a-zA-Z0-9-]{3,30})([.])([a-zA-Z0-9]{2,5})([.][vn]{2})?")) {
                valid = false;
                error.setUsernameError("Username must be example@gmail.com(.vn)");
            }
            if (password.length() == 0) {
                valid = false;
                error.setPasswordError("Password can't be blank");
            }
            if (fullname.length() == 0) {
                valid = false;
                error.setFullnameError("Fullname can't be blank");
            }
            if (!confirm.equals(password)) {
                valid = false;
                error.setConfirmError("Confirm must match password");
            }
            String password_encrypt = SHA256_Enryption.sha256(password);
            RegistrationDTO dto = new RegistrationDTO(username, fullname, password_encrypt, status);
            if (valid) {
                RegistrationDAO dao = new RegistrationDAO();
                if (dao.insertAccount(dto)) {
                    url = SUCCESS;
                    request.setAttribute("SUCCESS", "New account sign up successfully!!");
                }
            } else {
                url = INVALID;
                request.setAttribute("DTO", dto);
                request.setAttribute("INVALID", error);
            }
        } catch (Exception e) {
            request.setAttribute("ERROR", "Duplicate User Email");
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

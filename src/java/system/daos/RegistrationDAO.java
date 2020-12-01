/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import system.dtos.RegistrationDTO;

/**
 *
 * @author theFrozenAdam
 */
public class RegistrationDAO implements Serializable {

    private Connection con;
    private PreparedStatement preStm;
    private ResultSet rs;

    private void openConnection() throws Exception {
        Context ctx = new InitialContext();
        Context envCtx = (Context) ctx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup("DBCon");
        con = ds.getConnection();
    }

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public RegistrationDTO checkLogin(String username, String password) throws Exception {
        RegistrationDTO dto = null;
        try {
            openConnection();
            String sql = "Select accountEmail, accountName, accountStatus from Account where accountEmail = ? and accountPassword = ?";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, username);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String accountEmail = rs.getString("accountEmail");
                String accountName = rs.getString("accountName");
                int status = rs.getInt("accountStatus");
                dto = new RegistrationDTO(accountEmail, accountName, status);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean insertAccount(RegistrationDTO dto) throws Exception {
        boolean check = false;
        try {
            openConnection();
            String sql = "Insert into Account(accountEmail, accountName, accountPassword, accountStatus) values (?, ?, ?, ?)";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getFullname());
            preStm.setString(3, dto.getPassword());
            preStm.setInt(4, dto.getStatus());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}

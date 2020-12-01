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
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import system.dtos.NotificationDTO;

/**
 *
 * @author Admin
 */
public class NotificationDAO implements Serializable {

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

    public int getTotalNotification() throws Exception {
        int total = 0;
        try {
            openConnection();
            String sql = "Select COUNT(ID) as Total from Notifications";
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("Total");
            }
        } finally {
            closeConnection();
        }
        return total;
    }

    public boolean insertNotification(NotificationDTO dto) throws Exception {
        boolean check = false;
        try {
            openConnection();
            String sql = "Insert into Notifications(ID, articleID, accountEmail, notifyDate, notifyStatus, currentStatus) values (?, ?, ?, GETDATE(), ?, 1)";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, dto.getID() + 1);
            preStm.setInt(2, dto.getArticleID());
            preStm.setString(3, dto.getAccountEmail());
            preStm.setString(4, dto.getNotifyStatus());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public List<NotificationDTO> notificationList(String owner) throws Exception {
        List<NotificationDTO> list = null;
        NotificationDTO dto = null;
        try {
            openConnection();
            String sql = "SELECT Notifications.ID, Notifications.articleID, Account.accountName, notifyDate, notifyStatus, Article.articleTittle FROM Notifications, Article, Account WHERE Article.articlePostBy = ? and Notifications.accountEmail != ? and Article.articleID = Notifications.articleID and currentStatus = 1 and Account.accountEmail = Notifications.accountEmail";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, owner);
            preStm.setString(2, owner);
            list = new ArrayList<>();
            rs = preStm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int articleID = rs.getInt("articleID");
                String user = rs.getString("accountName");
                String status = rs.getString("notifyStatus");
                String date = rs.getString("notifyDate");
                String tittle = rs.getString("articleTittle");
                dto = new NotificationDTO(id, articleID, user, date, status, tittle);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public void removeNotification(int id) throws Exception {
        try {
            openConnection();
            String sql = "Update Notifications set currentStatus = 3 where ID = ?";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, id);
            preStm.executeUpdate();
        } finally {
            closeConnection();
        }
    }
}

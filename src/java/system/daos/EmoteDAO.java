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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import system.dtos.EmoteDTO;

/**
 *
 * @author Admin
 */
public class EmoteDAO implements Serializable {

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

    public int getTotalEmoteID() throws Exception {
        int total = 0;
        try {
            openConnection();
            String sql = "Select COUNT(ID) as Total from tblEmote";
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

    public int getLikes(int postid) throws Exception {
        int total = 0;
        try {
            openConnection();
            String sql = "Select COUNT(emoteLike) as Likes from tblEmote where postID = ? and emoteLike = 1 and emoteDislike = 0 and (emoteStatus = 1 or emoteStatus = 2)";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, postid);
            rs = preStm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("Likes");
            }
        } finally {
            closeConnection();
        }
        return total;
    }

    public int getDislikes(int postid) throws Exception {
        int total = 0;
        try {
            openConnection();
            String sql = "Select COUNT(emoteDislike) as Dislikes from tblEmote where postID = ? and emoteLike = 0 and emoteDislike = 1 and (emoteStatus = 1 or emoteStatus = 2)";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, postid);
            rs = preStm.executeQuery();
            if (rs.next()) {
                total = rs.getInt("Dislikes");
            }
        } finally {
            closeConnection();
        }
        return total;
    }

    public boolean insertEmote(int id, EmoteDTO dto) throws Exception {
        boolean check = false;
        try {
            openConnection();
            String sql = "Insert into tblEmote(ID, postID, accountEmail, emoteLike, emoteDislike, emoteDate, emoteStatus) values (?, ?, ?, ?, ?, GETDATE(), 1)";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, (id + 1));
            preStm.setInt(2, dto.getPostID());
            preStm.setString(3, dto.getAccountEmail());
            preStm.setBoolean(4, dto.isLike());
            preStm.setBoolean(5, dto.isDislike());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public EmoteDTO checkExistEmote(int postid, String user) throws Exception {
        EmoteDTO dto = null;
        try {
            openConnection();
            String sql = "Select ID, emoteLike, emoteDislike, emoteDate, emoteStatus from tblEmote where postID = ? and accountEmail = ? and (emoteStatus = 1 or emoteStatus = 2)";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, postid);
            preStm.setString(2, user);
            rs = preStm.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ID");
                boolean like = rs.getBoolean("emoteLike");
                boolean dislike = rs.getBoolean("emoteDislike");
                String date = rs.getString("emoteDate");
                int status = rs.getInt("emoteStatus");
                dto = new EmoteDTO(id, postid, user, like, dislike, date, status);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public void changeEmoteToDeactive(int id) throws Exception {
        try {
            openConnection();
            String sql = "Update tblEmote set emoteStatus = 3 where ID = ?";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, id);
            preStm.executeUpdate();
        } finally {
            closeConnection();
        }
    }
}

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
import system.dtos.CommentDTO;

/**
 *
 * @author Admin
 */
public class CommentDAO implements Serializable {

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

    public List<CommentDTO> getAllComment(int id) throws Exception {
        List<CommentDTO> list = null;
        CommentDTO dto = null;
        try {
            openConnection();
            String sql = "Select commentPostBy, commentContent, commentDate, accountName from Comments, Account where articleID = ? and (commentStatus = 1 or commentStatus = 2) and commentPostBy = accountEmail";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, id);
            list = new ArrayList<>();
            rs = preStm.executeQuery();
            while (rs.next()) {
                String postBy = rs.getString("commentPostBy");
                String content = rs.getString("commentContent");
                String date = rs.getString("commentDate");
                String name = rs.getString("accountName");
                dto = new CommentDTO(postBy, content, date);
                dto.setName(name);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean insertComment(CommentDTO dto) throws Exception {
        boolean check = false;
        try {
            openConnection();
            String sql = "Insert into Comments(articleID, commentPostBy, commentContent, commentDate, commentStatus) values (?, ?, ?, GETDATE(), ?)";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, dto.getArticleID());
            preStm.setString(2, dto.getCommentPostBy());
            preStm.setString(3, dto.getCommentContent());
            preStm.setInt(4, dto.getCommentStatus());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean deleteComment(int articleID, String postBy) throws Exception {
        boolean check = false;
        try{
            openConnection();
            String sql = "Update Comments set commentStatus = 3 where articleID = ? and commentPostBy = ?";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, articleID);
            preStm.setString(2, postBy);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}

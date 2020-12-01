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
import system.dtos.ArticleDTO;

/**
 *
 * @author Admin
 */
public class ArticleDAO implements Serializable {

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

    public List<ArticleDTO> getAllArticle() throws Exception {
        List<ArticleDTO> result = null;
        try {
            openConnection();
            String sql = "Select articleID, articleTittle, articleDescription, articleImage, articleDate from Article where articleStatus = 1 or articleStatus = 2 ORDER BY articleDate DESC";
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int articleID = rs.getInt("articleID");
                String articleTittle = rs.getString("articleTittle");
                String articleDescription = rs.getString("articleDescription");
                String articleImage = rs.getString("articleImage");
                String articleDate = rs.getString("articleDate");
                ArticleDTO dto = new ArticleDTO(articleID, articleTittle, articleDescription, articleImage, articleDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<ArticleDTO> searchArticleByName(String search) throws Exception {
        List<ArticleDTO> result = null;
        try {
            openConnection();
            String sql = "Select articleID, articleTittle, articleDescription, articleImage, articleDate from Article where articleTittle like ? and (articleStatus = 1 or articleStatus = 2) ORDER BY articleDate DESC";
            preStm = con.prepareStatement(sql);
            preStm.setString(1, "%" + search + "%");
            rs = preStm.executeQuery();
            result = new ArrayList<>();
            while (rs.next()) {
                int articleID = rs.getInt("articleID");
                String articleTittle = rs.getString("articleTittle");
                String articleDescription = rs.getString("articleDescription");
                String articleImage = rs.getString("articleImage");
                String articleDate = rs.getString("articleDate");
                ArticleDTO dto = new ArticleDTO(articleID, articleTittle, articleDescription, articleImage, articleDate);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getTotalArticle() throws Exception {
        int result = 0;
        try {
            openConnection();
            String sql = "select COUNT(articleID) as Total from Article";
            preStm = con.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                result = rs.getInt("Total");
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean postNewArticle(ArticleDTO dto) throws Exception {
        boolean check = false;
        try {
            openConnection();
            String sql = "Insert into Article(articleID, articleTittle, articleDescription, articleImage, articleDate, articlePostBy, articleStatus) values (?, ?, ?, ?, GETDATE(), ?, ?)";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, dto.getArticleID());
            preStm.setString(2, dto.getArticleTittle());
            preStm.setString(3, dto.getArticleDescription());
            preStm.setString(4, dto.getArticleImage());
            preStm.setString(5, dto.getArticlePostBy());
            preStm.setInt(6, dto.getArticleStatus());
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public ArticleDTO getArticleDetails(int ID) throws Exception {
        ArticleDTO dto = null;
        try {
            openConnection();
            String sql = "Select articleTittle, articleDescription, articleImage, articleDate, articlePostBy from Article where articleID = ? and (articleStatus = 1 or articleStatus = 2)";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, ID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String tittle = rs.getString("articleTittle");
                String description = rs.getString("articleDescription");
                String image = rs.getString("articleImage");
                String date = rs.getString("articleDate");
                String author = rs.getString("articlePostBy");
                dto = new ArticleDTO(tittle, description, image, date, author);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean deleteArticle(int id, String postBy) throws Exception {
        boolean check = false;
        try {
            openConnection();
            String sql = "Update Article set articleStatus = 3 where articleID = ? and articlePostBy = ?";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, id);
            preStm.setString(2, postBy);
            check = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public int getArticleStatus(int id) throws Exception {
        int check = 0;
        try {
            openConnection();
            String sql = "Select articleStatus from Article where articleID = ?";
            preStm = con.prepareStatement(sql);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            if(rs.next()){
                check = rs.getInt("articleStatus");
            }
        } finally {
            closeConnection();
        }
        return check;
    }
}

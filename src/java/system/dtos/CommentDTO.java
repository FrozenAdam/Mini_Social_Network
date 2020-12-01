/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.dtos;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class CommentDTO implements Serializable {

    private int articleID, commentStatus;
    private String commentPostBy, commentContent, commentDate, name;

    public CommentDTO(String commentPostBy, String commentContent, String commentDate) {
        this.commentPostBy = commentPostBy;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
    }

    public CommentDTO(int articleID, String commentPostBy, String commentContent, int commentStatus) {
        this.articleID = articleID;
        this.commentStatus = commentStatus;
        this.commentPostBy = commentPostBy;
        this.commentContent = commentContent;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public int getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(int commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getCommentPostBy() {
        return commentPostBy;
    }

    public void setCommentPostBy(String commentPostBy) {
        this.commentPostBy = commentPostBy;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

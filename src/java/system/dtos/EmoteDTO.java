/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.dtos;

/**
 *
 * @author Admin
 */
public class EmoteDTO {

    private String accountEmail, date;
    private int emoteID, postID, status;
    private boolean like, dislike;
    
    public EmoteDTO(int emoteID, boolean like, boolean dislike){
        this.emoteID = emoteID;
        this.like = like;
        this.dislike = dislike;
    }

    public EmoteDTO(int postID, String accountEmail, boolean like, boolean dislike) {
        this.postID = postID;
        this.accountEmail = accountEmail;
        this.like = like;
        this.dislike = dislike;
    }

    public EmoteDTO(int emoteID, int postID, String accountEmail, boolean like, boolean dislike, String date, int status) {
        this.emoteID = emoteID;
        this.postID = postID;
        this.accountEmail = accountEmail;
        this.like = like;
        this.dislike = dislike;
        this.date = date;
        this.status = status;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public boolean isDislike() {
        return dislike;
    }

    public void setDislike(boolean dislike) {
        this.dislike = dislike;
    }

    public int getEmoteID() {
        return emoteID;
    }

    public void setEmoteID(int emoteID) {
        this.emoteID = emoteID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}

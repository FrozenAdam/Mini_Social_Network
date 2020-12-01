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
public class NotificationDTO {

    private int ID, articleID;
    private String accountEmail, notifyDate, notifyStatus, tittle;

    public NotificationDTO(int ID, int articleID, String accountEmail, String notifyDate, String notifyStatus, String tittle) {
        this.ID = ID;
        this.articleID = articleID;
        this.accountEmail = accountEmail;
        this.notifyDate = notifyDate;
        this.notifyStatus = notifyStatus;
        this.tittle = tittle;
    }
    
    public NotificationDTO(int ID, int articleID, String accountEmail, String notifyStatus) {
        this.ID = ID;
        this.articleID = articleID;
        this.accountEmail = accountEmail;
        this.notifyStatus = notifyStatus;
    }
    
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getNotifyDate() {
        return notifyDate;
    }

    public void setNotifyDate(String notifyDate) {
        this.notifyDate = notifyDate;
    }

    public String getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(String notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}

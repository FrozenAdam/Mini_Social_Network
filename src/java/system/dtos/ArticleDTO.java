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
public class ArticleDTO {
    private int articleID, articleStatus;
    private String articleTittle, articleDescription, articleImage, articlePostBy, articleDate;

    public ArticleDTO(int articleID, String articleTittle, String articleDescription, String articleImage, String articlePostBy, int articleStatus) {
        this.articleID = articleID;
        this.articleStatus = articleStatus;
        this.articleTittle = articleTittle;
        this.articleDescription = articleDescription;
        this.articleImage = articleImage;
        this.articlePostBy = articlePostBy;
    }
    
    public ArticleDTO(String articleTittle, String articleDescription, String articleImage, String articleDate, String articlePostBy){
        this.articleTittle = articleTittle;
        this.articleDescription = articleDescription;
        this.articleImage = articleImage;
        this.articleDate = articleDate;
        this.articlePostBy = articlePostBy;
    }
    
    public ArticleDTO(int ID, String articleTittle, String articleDescription, String articleImage, String articleDate){
        this.articleID = ID;
        this.articleTittle = articleTittle;
        this.articleDescription = articleDescription;
        this.articleImage = articleImage;
        this.articleDate = articleDate;
    }
    
    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public int getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(int articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getArticleTittle() {
        return articleTittle;
    }

    public void setArticleTittle(String articleTittle) {
        this.articleTittle = articleTittle;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public String getArticlePostBy() {
        return articlePostBy;
    }

    public void setArticlePostBy(String articlePostBy) {
        this.articlePostBy = articlePostBy;
    }

    public String getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(String articleDate) {
        this.articleDate = articleDate;
    }
}

package com.nikesh.restapi.blog.model;

import java.sql.Timestamp;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Nikesh
 */

@XmlRootElement
@XmlType(propOrder = {"commentId", "commentBody", "commentDate", "postId"})
public class Comment {
    
    private int commentId;
    private String commentBody;
    private Timestamp commentDate;
    private int postId;
    
    public Comment(){
        
    }
    
    public Comment(String commentBody){
        this.commentBody = commentBody;
    }
    
    public Comment(String commentBody, int postId){
        this.commentBody = commentBody;
        this.postId = postId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }    
}

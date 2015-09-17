/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nikesh.restapi.blog.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Nikesh
 */
@XmlRootElement
@XmlType(propOrder = {"postId", "postTitle", "postContent", "postCreated", "profileId"})
public class Post {

    private int postId;
    private int profileId;
    private String postTitle;
    private String postContent;
    private Date postCreated;

    public Post() {

    }

    public Post(String title, String content) {
        this.postTitle = title;
        this.postContent = content;
        this.postCreated = new Date();
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public Date getPostCreated() {
        return postCreated;
    }

    public void setPostCreated(Date postCreated) {
        this.postCreated = postCreated;
    }
}

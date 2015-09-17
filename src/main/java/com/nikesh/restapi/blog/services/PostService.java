/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nikesh.restapi.blog.services;

import com.nikesh.restapi.blog.dao.DatabaseObject;
import com.nikesh.restapi.blog.model.Post;
import java.util.List;

/**
 *
 * @author Nikesh
 */
public class PostService {    
    
    public DatabaseObject dao = new DatabaseObject();
    
    public List<Post> getPosts(int profileId){
        return dao.getPosts(profileId);
    }
    
    public Post getPost(int profileId, int postId){
        return dao.getPost(profileId, postId);
    }
    
}

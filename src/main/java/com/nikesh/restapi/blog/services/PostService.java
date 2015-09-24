/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nikesh.restapi.blog.services;

import com.nikesh.restapi.blog.dao.PostDAO;
import com.nikesh.restapi.blog.model.Post;
import java.util.List;

/**
 *
 * @author Nikesh
 */
public class PostService {    
    
    private PostDAO dao = new PostDAO();
    
    public List<Post> getPosts(int profileId){
        return dao.getPosts(profileId);
    }
    
    public Post getPost(int profileId, int postId){
        return dao.getPost(profileId, postId);
    }
    
    public Post addPost(Post post, int profileId){
        int id = dao.getNumberOfPosts() + 1;
        System.out.println("ID OF THE POST " + id);
        post.setPostId(id);
        post.setProfileId(profileId);
        return dao.addPost(post, profileId);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nikesh.restapi.blog.resources;

import com.nikesh.restapi.blog.model.Post;
import com.nikesh.restapi.blog.services.PostService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Nikesh
 */
//@Path("/") // Path annotation in subresource is optional
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})

public class PostResource {

    private final PostService postService = new PostService();

    @GET
    public List<Post> getPosts(@PathParam("profileId") int profileId) {
        return postService.getPosts(profileId);
    }

    @GET
    @Path("/{postId}")
    public Post getPost(@PathParam("postId") int postId, @PathParam("profileId") int profileId) {
        return postService.getPost(profileId, postId);
    }
    
    @POST
    public Post addPost(Post post, @PathParam("profileId") int profileId){
        return postService.addPost(post, profileId);
    }
   

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nikesh.restapi.blog.resources;

import com.nikesh.restapi.blog.model.Comment;
import com.nikesh.restapi.blog.services.CommentService;
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
//@Path("/")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class CommentResource {

    private final CommentService commentService = new CommentService();

    @GET
    public List<Comment> getComments(@PathParam("postId") int postId) {
        return commentService.getComments(postId);
    }
    
    @GET
    @Path("/{commentId}")
    //@Produces(MediaType.TEXT_PLAIN)
    public String test(@PathParam("profileId")int profileId, @PathParam("postId") int postId){
        return "Profile ID " + profileId + " Post ID " + postId;
    }
}

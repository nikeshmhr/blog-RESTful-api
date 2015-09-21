package com.nikesh.restapi.blog.services;

import com.nikesh.restapi.blog.dao.CommentDAO;
import com.nikesh.restapi.blog.model.Comment;
import java.util.List;

/**
 *
 * @author Nikesh
 */
public class CommentService {
    
    public CommentDAO dao = new CommentDAO();
    
    public List<Comment> getComments(int postId){
        return dao.getComments(postId);
    }
    
}

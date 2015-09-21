package com.nikesh.restapi.blog.dao;

import com.nikesh.restapi.blog.model.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nikesh
 */
public class CommentDAO {
    
    private static Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    
    public CommentDAO(){
        connection = DatabaseObject.getConnection();
    }
    
    public List<Comment> getComments(int postId){
        List<Comment> comments = new ArrayList<>();
        
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM comments WHERE post_id=?");
            preparedStatement.setInt(1, postId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Comment comment = new Comment(resultSet.getString("comment_body"), resultSet.getInt("post_id"));
                comment.setCommentDate(resultSet.getTimestamp("comment_date"));
                comment.setCommentId(resultSet.getInt("comment_id"));
                
                comments.add(comment);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }        
        return comments;
    }
    
}

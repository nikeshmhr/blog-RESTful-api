/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nikesh.restapi.blog.dao;

import com.nikesh.restapi.blog.model.Post;
import java.sql.Connection;
import java.sql.Date;
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
public class PostDAO {

    private static Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public PostDAO() {
        connection = DatabaseObject.getConnection();
    }

    public List<Post> getPosts(int profileId) {
        List<Post> posts = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM posts WHERE profile_id=?");
            preparedStatement.setInt(1, profileId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = new Post(resultSet.getString("post_title"), resultSet.getString("post_content"));
                post.setPostCreated(resultSet.getDate("post_date"));
                post.setPostId(resultSet.getInt("post_id"));
                post.setProfileId(resultSet.getInt("profile_id"));
                posts.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return posts;
    }

    public Post getPost(int profileId, int postId) {
        Post post = null;

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM posts WHERE profile_id=? AND post_id=? LIMIT 1");
            preparedStatement.setInt(1, profileId);
            preparedStatement.setInt(2, postId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {    // could remove while because the query only returns 1 row as
                post = new Post(resultSet.getString("post_title"), resultSet.getString("post_content"));
                post.setPostCreated(resultSet.getDate("post_date"));
                post.setPostId(resultSet.getInt("post_id"));
                post.setProfileId(resultSet.getInt("profile_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return post;
    }

    public Post addPost(Post post, int profileId) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO posts VALUES(?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, post.getPostId());
            preparedStatement.setString(2, post.getPostTitle());
            preparedStatement.setString(3, post.getPostContent());
            preparedStatement.setDate(4, (Date) post.getPostCreated());
            preparedStatement.setInt(5, profileId);

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return post;
    }

    public int getNumberOfPosts() {
        int numOfPosts = 0;
        try {
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM posts");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                numOfPosts = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numOfPosts;
    }
}

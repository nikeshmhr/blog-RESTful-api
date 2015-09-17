package com.nikesh.restapi.blog.dao;

import com.nikesh.restapi.blog.model.Post;
import java.util.ArrayList;
import java.util.List;

import com.nikesh.restapi.blog.model.Profile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseObject {

    private static Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private final String host = "jdbc:mysql://localhost:3306/blog", user = "root", password = "n1k35h";

    public DatabaseObject() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            connection = DriverManager.getConnection(host, user, password);
            System.out.println("Connection Successful.");
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            // ERROR MESSAGE HERE
        }
    }

    public List<Profile> getProfiles() {
        List<Profile> profiles = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM profiles");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Profile profile = new Profile(resultSet.getString("firstname"), resultSet.getString("lastname"),
                        resultSet.getString("username"));
                profile.setProfileId(resultSet.getInt("profile_id"));
                profile.setJoinedDate(resultSet.getDate("date_created"));
                profiles.add(profile);
            }
        } catch (SQLException e) {
            // ERROR MESSAGE HERE
        }
        return profiles;
    }

    public Profile getProfile(int profileId) {
        Profile profile = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM profiles WHERE profile_id=?");
            preparedStatement.setInt(1, profileId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                profile = new Profile(resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("username"));
                profile.setJoinedDate(resultSet.getDate("date_created"));
                profile.setProfileId(profileId);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return profile;
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

    public Profile addProfile(Profile profile) {
        try {
            int profileId = profile.getProfileId();
            String firstName = profile.getFirstName();
            String lastName = profile.getLastName();
            String userName = profile.getUserName();
            Date dateCreated = profile.getJoinedDate();

            preparedStatement = connection.prepareStatement("INSERT INTO profiles VALUES(?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, profileId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, userName);
            preparedStatement.setDate(5, dateCreated);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            profile = null;
        }
        return profile;
    }

    public int getNumberOfProfiles() {
        int numOfProfiles = 0;
        try {
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM profiles");
            
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                numOfProfiles = resultSet.getInt(1);
            }
        } catch (SQLException ex) {

        }
        return numOfProfiles;
    }

}

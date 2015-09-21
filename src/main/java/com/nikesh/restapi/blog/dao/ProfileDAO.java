package com.nikesh.restapi.blog.dao;

import com.nikesh.restapi.blog.model.Profile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProfileDAO {

    private static Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ProfileDAO() {
        connection = DatabaseObject.getConnection();
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
            preparedStatement.setDate(5, (java.sql.Date) dateCreated);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            profile = null;
        }
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        Profile old = getProfile(profile.getProfileId());
        Profile updated = profile;
        updated.setProfileId(old.getProfileId());
        updated.setJoinedDate(old.getJoinedDate());
        try{
            preparedStatement = connection.prepareStatement("UPDATE profiles SET firstname=?, lastname=?, username=? WHERE profile_id=?");
            preparedStatement.setString(1, updated.getFirstName());
            preparedStatement.setString(2, updated.getLastName());
            preparedStatement.setString(3, updated.getUserName());
            preparedStatement.setInt(4, updated.getProfileId());
            
            preparedStatement.executeUpdate();
        }catch (SQLException ex){
            profile = null;
        }
        return updated;
    }
    
    public Profile deleteProfile(int profileId){
        Profile p = getProfile(profileId);
        
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM profiles WHERE profile_id=?");
            preparedStatement.setInt(1, profileId);
            preparedStatement.executeUpdate();
        }catch(SQLException ex){
            p = null;
        }
        return p;
    }

    public int getNumberOfProfiles() {
        int numOfProfiles = 0;
        try {
            preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM profiles");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                numOfProfiles = resultSet.getInt(1);
            }
        } catch (SQLException ex) {

        }
        return numOfProfiles;
    }

}

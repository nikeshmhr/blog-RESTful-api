package com.nikesh.restapi.blog.services;

import com.nikesh.restapi.blog.dao.DatabaseObject;
import java.util.List;

import com.nikesh.restapi.blog.model.Profile;
import java.sql.Date;

public class ProfileService {

    public DatabaseObject dao = new DatabaseObject();

    public List<Profile> getProfiles() {
        return dao.getProfiles();
    }

    public Profile getProfile(int profileId) {
        return dao.getProfile(profileId);
    }

    public Profile addProfile(Profile profile) {
        int profileId = dao.getNumberOfProfiles() + 1;
        profile.setProfileId(profileId);
        return dao.addProfile(profile);
    }
}

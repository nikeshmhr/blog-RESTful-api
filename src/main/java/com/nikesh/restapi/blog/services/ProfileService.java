package com.nikesh.restapi.blog.services;

import com.nikesh.restapi.blog.dao.ProfileDAO;
import java.util.List;

import com.nikesh.restapi.blog.model.Profile;

public class ProfileService {

    private ProfileDAO dao = new ProfileDAO();

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
    
    public Profile updateProfile(Profile profile){
        return dao.updateProfile(profile);
    }
    
    public Profile deleteProfile(int profileId){
        return dao.deleteProfile(profileId);
    }
}

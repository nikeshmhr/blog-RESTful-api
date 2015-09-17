package com.nikesh.restapi.blog.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nikesh.restapi.blog.model.Profile;
import com.nikesh.restapi.blog.services.ProfileService;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

@Path("/profiles")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes(MediaType.APPLICATION_JSON)
public class ProfileResource {

    private final ProfileService profileService = new ProfileService();

    @GET
    public List<Profile> getProfiles() {
        return profileService.getProfiles();
    }

    @POST
    public Profile addProfile(Profile profile) {
        return profileService.addProfile(profile);
    }

    @GET
    @Path("/{profileId}")
    public Profile getProfile(@PathParam("profileId") int profileId) {
        return profileService.getProfile(profileId);
    }

    @Path("/{profileId}/posts")
    public PostResource getPost(@PathParam(value = "profileId") int profileId) {
        System.out.println("ProfileID:" + profileId);
        return new PostResource();
    }
}

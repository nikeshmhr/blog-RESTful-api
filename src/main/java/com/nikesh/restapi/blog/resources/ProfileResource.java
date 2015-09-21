package com.nikesh.restapi.blog.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nikesh.restapi.blog.model.Profile;
import com.nikesh.restapi.blog.services.ProfileService;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

@Path("/profiles")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ProfileResource {

    private final ProfileService profileService = new ProfileService();

    @GET
    public List<Profile> getProfiles() {
        return profileService.getProfiles();
    }

    @GET
    @Path("/{profileId}")
    public Profile getProfile(@PathParam("profileId") int profileId) {
        return profileService.getProfile(profileId);
    }

    @POST
    public Profile addProfile(Profile profile) {
        return profileService.addProfile(profile);
    }
    
    @PUT
    @Path("/{profileId}")
    //@Produces(MediaType.TEXT_PLAIN)
    public Profile updateProfile(Profile profile, @PathParam("profileId") int profileId){
        profile.setProfileId(profileId);
        return profileService.updateProfile(profile);
        //return profile.getFirstName() + ", " + profile.getLastName() + ", " + profile.getUserName() + ", " + profileId;
    }
    
    @DELETE
    @Path("/{profileId}")
    public Profile deleteProfile(@PathParam("profileId") int profileId){
        return profileService.deleteProfile(profileId);
    }

    @Path("/{profileId}/posts")
    public PostResource getPostResource() {
        return new PostResource();
    }

    @Path("/{profileId}/posts/{postId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }
}

package com.nikesh.restapi.blog;


import java.util.List;

import com.nikesh.restapi.blog.dao.DatabaseObject;
import com.nikesh.restapi.blog.model.Profile;

public class Test {

	public static void main(String[] args){
		DatabaseObject obj = new DatabaseObject();
		
		List<Profile> p = obj.getProfiles();
		
		for(Profile profile : p){
			System.out.println("Profile name:" + profile.getUserName());
		}		
	}
}

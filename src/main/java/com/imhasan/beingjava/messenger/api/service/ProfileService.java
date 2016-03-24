/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imhasan.beingjava.messenger.api.service;

import com.imhasan.beingjava.messenger.api.database.DatabaseClass;
import com.imhasan.beingjava.messenger.api.model.Profile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Uzzal
 */
public class ProfileService {

    private Map<String, Profile> profiles = DatabaseClass.getProfiles();

    public ProfileService() {
        profiles.put("Hasan", new Profile(1L, "Hasan", "Robiul", "Hasan"));
    }

    public List<Profile> getAllProfiles() {
        return new ArrayList<Profile>(profiles.values());
    }

    public Profile getProfile(String profileName) {
        return profiles.get(profileName);
    }

    public Profile addProfile(Profile profile) {
        profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if (profile.getProfileName().isEmpty()) {
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile removeProfile(String profileName) {
        return profiles.remove(profileName);
    }

}

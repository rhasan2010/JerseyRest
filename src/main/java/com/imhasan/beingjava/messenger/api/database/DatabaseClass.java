/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imhasan.beingjava.messenger.api.database;

import com.imhasan.beingjava.messenger.api.model.Message;
import com.imhasan.beingjava.messenger.api.model.Profile;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Uzzal
 */
public class DatabaseClass {

    private static Map<Long, Message> messages = new HashMap<>();
    private static Map<String, Profile> profiles = new HashMap<>();

    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static void setMessages(Map<Long, Message> messages) {
        DatabaseClass.messages = messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }

    public static void setProfiles(Map<String, Profile> profiles) {
        DatabaseClass.profiles = profiles;
    }

}

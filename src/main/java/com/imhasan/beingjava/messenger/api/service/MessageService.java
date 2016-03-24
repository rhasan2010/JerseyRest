/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imhasan.beingjava.messenger.api.service;

import com.imhasan.beingjava.messenger.api.exception.DataNotFoundException;
import com.imhasan.beingjava.messenger.api.database.DatabaseClass;
import com.imhasan.beingjava.messenger.api.model.Message;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Uzzal
 */
public class MessageService {

    private Map<Long, Message> messages = DatabaseClass.getMessages();

    public MessageService() {
        messages.put(1L, new Message(1, "Hello World", "Hasan"));
        messages.put(2L, new Message(2, "Hello Jersey", "Robiul"));
    }

    public List<Message> getAllMessages() {
        List<Message> list = new ArrayList<>(messages.values());
        return list;
    }

    public List<Message> getLAllMessagesForYear(int year) {
        List<Message> messageForYear = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Message message : messages.values()) {
            cal.setTime(message.getCreated());
            if (cal.get(Calendar.YEAR) == year) {
                messageForYear.add(message);
            }
        }
        return messageForYear;
    }

    public List<Message> getAllMessagesPaginated(int start, int size) {
        List<Message> list = new ArrayList<>(messages.values());
        if (start + size > list.size()) {
            return new ArrayList<Message>();
        }
        return list.subList(start, size);
    }

    public Message getMessage(long id) {
        Message message = messages.get(id);
        if (message == null) {
            throw new DataNotFoundException("Message with id: " + id + " not found");
        }
        return message;
    }

    public Message addMessage(Message message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);

        return message;
    }

    public Message updateMessage(Message message) {
        if (message.getId() <= 0) {
            return null;
        }

        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(long id) {
        return messages.remove(id);
    }

}

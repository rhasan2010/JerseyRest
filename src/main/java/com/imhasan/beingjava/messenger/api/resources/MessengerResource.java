/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imhasan.beingjava.messenger.api.resources;

import com.imhasan.beingjava.messenger.api.model.Message;
import com.imhasan.beingjava.messenger.api.resources.beans.MessageFilterBean;
import com.imhasan.beingjava.messenger.api.service.MessageService;
import java.net.URI;
import java.util.List;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilderException;
import javax.ws.rs.core.UriInfo;
import org.glassfish.jersey.server.Uri;

/**
 *
 * @author Uzzal
 */
@Path("/messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessengerResource {

    MessageService messageService = new MessageService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
//    public List<Message> getMessages(
//            @QueryParam("year") int year,
//            @QueryParam("start") int start,
//            @QueryParam("size") int size) {

    public List<Message> getJSONMessages(@BeanParam MessageFilterBean filterBean) { //Using Bean Param; Creating Bean class
        System.out.println("JSON REQUEST METHOD");
        if (filterBean.getYear() > 0) {
            return messageService.getLAllMessagesForYear(filterBean.getYear());
        }

        if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
            return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
        }
        return messageService.getAllMessages();
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Message> getXMLMessages(@BeanParam MessageFilterBean filterBean) { //Using Bean Param; Creating Bean class
        System.out.println("XML REQUEST METHOD");
        if (filterBean.getYear() > 0) {
            return messageService.getLAllMessagesForYear(filterBean.getYear());
        }

        if (filterBean.getStart() >= 0 && filterBean.getSize() > 0) {
            return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
        }
        return messageService.getAllMessages();
    }

    @POST
//    public Message addMessage(Message message) {
//        return messageService.addMessage(message);
//    }

    public Response addMessage(Message message, @Context UriInfo uriInfo) {
        Message newMessage = messageService.addMessage(message);
        String newId = String.valueOf(newMessage.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(newMessage).build();
    }

    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long id, Message message) {
        message.setId(id);
        return messageService.updateMessage(message);
    }

    @DELETE
    @Path("/{messageId}")
    public void removeMessage(@PathParam("messageId") long id) {
        messageService.removeMessage(id);
    }

    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long messageId, @Context UriInfo uriInfo) {
        Message message = messageService.getMessage(messageId);
//        String uri = getUriForSelf(uriInfo, message);
        message.addLink(getUriForSelf(uriInfo, message), "self");
        message.addLink(getUriForProfile(uriInfo, message), "profile");
        message.addLink(getUriForComments(uriInfo, message), "comment");
        return message;
    }

    @Path("/{messageId}/comments")
    public CommentResource getCommentResource() {
        return new CommentResource();
    }

    private String getUriForSelf(UriInfo uriInfo, Message message) throws IllegalArgumentException, UriBuilderException {
        String uri = uriInfo.getBaseUriBuilder()
                .path(MessengerResource.class)
                .path(Long.toString(message.getId())).build().toString();
        return uri;
    }

    private String getUriForProfile(UriInfo uriInfo, Message message) {
        URI uri = uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor()).build();
        return uri.toString();
    }

    private String getUriForComments(UriInfo uriInfo, Message message) {
        URI uri = uriInfo.getBaseUriBuilder()
                .path(MessengerResource.class)
                .path(MessengerResource.class, "getCommentResource")
                .resolveTemplate("messageId", message.getId())
                .path(CommentResource.class).build();
        return uri.toString();
    }
}

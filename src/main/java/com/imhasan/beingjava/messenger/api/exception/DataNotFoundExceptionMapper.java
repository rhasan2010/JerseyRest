/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imhasan.beingjava.messenger.api.exception;

import com.imhasan.beingjava.messenger.api.model.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Uzzal
 */
@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

    @Override
    public Response toResponse(DataNotFoundException ex){
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "http://www.google.com");
        return Response.status(Status.NOT_FOUND).entity(errorMessage).build();
    }

}

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
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable ex) {
        ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500, "Internel Server Error");
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorMessage).build();
    }

}

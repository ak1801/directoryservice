package com.profactus.directory.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;

@Provider
public class UnrecognizedPropertyExceptionMapper implements ExceptionMapper<UnrecognizedPropertyException>{

	public Response toResponse(UnrecognizedPropertyException exception) {
		return Response
                .status(Response.Status.BAD_REQUEST)
                .entity( "'" + exception.getUnrecognizedPropertyName() + "' attribute value is invalid/unrecognized.")
                .type(MediaType.APPLICATION_JSON)
                .build();
	}
}

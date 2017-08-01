package com.profactus.directory.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.codehaus.jackson.map.exc.UnrecognizedPropertyException;

/**
 * 
 * UnrecognizedPropertyExceptionMapper is a mapper to UnrecognizedPropertyException, can be thrown when:
 * Invalid or unrecognized input is encountered while bean/entity validations
 * 
 * @author Akshit Mahajan
 *
 */
@Provider
public class UnrecognizedPropertyExceptionMapper implements ExceptionMapper<UnrecognizedPropertyException>{

	/**
	 * Maps the response based on exception occurred.
	 * 
	 * @param UnrecognizedPropertyException
	 * @return Response
	 */
	public Response toResponse(UnrecognizedPropertyException exception) {
		return Response
                .status(Response.Status.BAD_REQUEST)
                .entity( "'" + exception.getUnrecognizedPropertyName() + "' attribute value is invalid/unrecognized.")
                .type(MediaType.TEXT_PLAIN)
                .build();
	}
}

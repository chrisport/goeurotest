package ch.chrisport.goeuro.message;

import lombok.Value;

import javax.ws.rs.core.Response;

@Value
public class ErrorResponse {
    private final Response.Status status;
    private final String message;

    public Response toResponse() {
        return Response.status(status).entity(this).build();
    }
}

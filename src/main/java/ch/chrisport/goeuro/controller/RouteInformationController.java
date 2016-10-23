package ch.chrisport.goeuro.controller;

import ch.chrisport.goeuro.message.DirectConnectionResponse;
import ch.chrisport.goeuro.message.ErrorResponse;
import ch.chrisport.goeuro.service.RouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class RouteInformationController {

    @Autowired
    public RouteInformationController(RouteService directRouteService) {
        this.directRouteService = directRouteService;
    }

    private RouteService directRouteService;

    @GET
    @Path(value = "/direct")
    public DirectConnectionResponse hasDirectConnection(@NotNull @QueryParam("dep_sid") String depSID,
                                                        @NotNull @QueryParam("arr_sid") String arrSID) {
        Integer departureStationId = parseInt(depSID, "dep_sid");
        Integer arrivalStationId = parseInt(arrSID, "arr_sid");
        boolean hasDirectRoute = directRouteService.hasDirectConnection(departureStationId, arrivalStationId);

        log.info("api/direct requested from {} to {}, respond with {}", departureStationId, arrivalStationId, hasDirectRoute);
        return new DirectConnectionResponse(departureStationId, arrivalStationId, hasDirectRoute);
    }

    // workaround for https://issues.apache.org/jira/browse/CXF-4976
    private Integer parseInt(String inputString, String fieldName) {
        try {
            return Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            String msg = String.format("Could not parse value to Integer for parameter \"%1s\" with value \"%2s\"", fieldName, inputString);
            ErrorResponse errorResponse = new ErrorResponse(Response.Status.BAD_REQUEST, msg);
            throw new WebApplicationException(errorResponse.toResponse());
        }
    }

}

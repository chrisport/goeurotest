package ch.chrisport.goeuro.message;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class DirectConnectionResponse {

    @JsonProperty("dep_sid")
    private final int depSId;

    @JsonProperty("arr_sid")
    private final int arrSId;

    @JsonProperty("direct_bus_route")
    private final boolean directBusRoute;
}

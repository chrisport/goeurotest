package ch.chrisport.goeuro.service;

/**
 * RouteService provides an interface to access route information.
 */
public interface RouteService {

    /**
     * checks if there is a direct route from departureStation to arrivalStation
     *
     * @param departureStationId the id of the departure station
     * @param arrivalStationId   the id of the arrival station
     * @return true if direct connection exists, false otherwise
     */
    boolean hasDirectConnection(int departureStationId, int arrivalStationId);

}

package ch.chrisport.goeuro.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;

/**
 * RouteUtil provides functionality to process route information.
 */
@Slf4j
public class RouteUtil {

    /**
     * Extracts direct connections from a list of route information strings in format according to goeuro task description.
     *
     * @param routes the list of route informations, one route per list entry
     * @return a map containing all stations mapping to a list of their directly connected stations
     * @throws IOException in case an Exception occurs while reading the file
     */
    public static Map<Integer, Set<Integer>> extractDirectConnections(List<String> routes) throws IOException {
        Map<Integer, Set<Integer>> connectionMap = new HashMap<>();
        routes.stream()
                .map(s -> s.split(" "))
                .map(RouteUtil::extractStationIds)
                .forEach(routeStations -> fillConnectionMapFromRoute(routeStations, connectionMap));
        return connectionMap;
    }

    // converts station IDs (strings) to integers.
    private static List<Integer> extractStationIds(String[] route) {
        List<Integer> stationIDsConverted = new ArrayList<>(route.length - 1);
        for (int i = 1; i < route.length; i++) {
            stationIDsConverted.add(Integer.valueOf(route[i]));
        }
        return stationIDsConverted;
    }

    // fills the connectionMap by adding all stations of this route to every station of this route.
    private static void fillConnectionMapFromRoute(List<Integer> routeStationIds, Map<Integer, Set<Integer>> connectionMap) {
        for (Integer stationId : routeStationIds) {
            Set<Integer> connectionsForStation = connectionMap.get(stationId);
            if (connectionsForStation == null) {
                connectionsForStation = new HashSet<>();
                connectionMap.put(stationId, connectionsForStation);
            }
            connectionsForStation.addAll(routeStationIds);
        }
    }

}
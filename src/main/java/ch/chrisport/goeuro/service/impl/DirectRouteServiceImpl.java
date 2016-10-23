package ch.chrisport.goeuro.service.impl;

import ch.chrisport.goeuro.service.RouteService;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Slf4j
public class DirectRouteServiceImpl implements RouteService {

    private static final Set<Integer> EMPTY_LIST = Collections.emptySet();

    private final Map<Integer, Set<Integer>> directRoutes;

    public DirectRouteServiceImpl(Map<Integer, Set<Integer>> directRoutes) {
        this.directRoutes = directRoutes;
    }

    @Override
    public boolean hasDirectConnection(int depSID, int arrSID) {
        log.debug("hasDirectConnection from {} to {}", depSID, arrSID);
        return directRoutes.getOrDefault(depSID, EMPTY_LIST).contains(arrSID);
    }
}

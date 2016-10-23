package ch.chrisport.goeuro.service.impl;

import ch.chrisport.goeuro.service.RouteService;
import jersey.repackaged.com.google.common.collect.Sets;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(BlockJUnit4ClassRunner.class)
public class DirectRouteServiceTests {

    @Test
    public void givenDirectConnections_whenQueryExistingDirectConnection_thenReturnTRUE() {
        Map<Integer, Set<Integer>> connectionMap = new HashMap<>();
        connectionMap.put(1, Sets.newHashSet(2, 3, 4, 5));
        RouteService directRouteService = new DirectRouteServiceImpl(connectionMap);

        boolean result = directRouteService.hasDirectConnection(1, 2);

        assertTrue("existing direct connection must return true", result);
    }

    @Test
    public void givenDirectConnections_whenQueryInexistingDirectConnection_thenReturnFALSE() {
        Map<Integer, Set<Integer>> connectionMap = new HashMap<>();
        connectionMap.put(1, Sets.newHashSet(2, 3, 4, 5));
        RouteService directRouteService = new DirectRouteServiceImpl(connectionMap);

        boolean result = directRouteService.hasDirectConnection(2, 4);

        assertFalse("inexisting direct connection must return false", result);
    }

    @Test
    public void givenDirectConnections_whenQueryInexistingStation_thenReturnFALSE() {
        Map<Integer, Set<Integer>> connectionMap = new HashMap<>();
        connectionMap.put(1, Sets.newHashSet(2, 3, 4, 5));
        RouteService directRouteService = new DirectRouteServiceImpl(connectionMap);

        boolean result = directRouteService.hasDirectConnection(12, 4);

        assertFalse("inexisting direct connection must return false", result);
    }

}

package ch.chrisport.goeuro.util;

import jersey.repackaged.com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertTrue;


@RunWith(BlockJUnit4ClassRunner.class)
public class RouteFileUtilTests {

    @Test
    public void givenRouteFromStation0To3_whenExtractDirectRoutes_thenResult0MustContain3() throws IOException {
        List<String> routes = new ArrayList<>();
        routes.add("3 0 1 4 3");

        Map<Integer, Set<Integer>> connectionMap = RouteUtil.extractDirectConnections(routes);

        assertTrue("station 0 must contain station 3 in its connection set", connectionMap.get(0).contains(3));
    }

    // under assumption that routes are bidirectional
    @Test
    public void givenRouteFromStation0To3_whenExtractDirectRoutes_thenResult3MustContain0() throws IOException {
        List<String> routes = new ArrayList<>();
        routes.add("3 0 1 4 3");

        Map<Integer, Set<Integer>> connectionMap = RouteUtil.extractDirectConnections(routes);

        assertTrue("station 3 must contain station 0 in its connection set", connectionMap.get(3).contains(0));
    }

    @Test
    public void givenNoRouteFromStation0To3_whenExtractDirectRoutes_thenResult0MustNotContain3() throws IOException {
        List<String> routes = new ArrayList<>();
        routes.add("3 0 1 4");
        routes.add("1 3 2 5");
        routes.add("0 9 0 4");

        Map<Integer, Set<Integer>> connectionMap = RouteUtil.extractDirectConnections(routes);

        assertTrue("station 0 must not contain station 3 in its connection set", !connectionMap.get(0).contains(3));
    }

    @Test
    public void givenNoRoutes_whenExtractDirectRoutes_returnEmptyMap() throws IOException {
        List<String> routes = Lists.newArrayList();

        Map<Integer, Set<Integer>> connectionMap = RouteUtil.extractDirectConnections(routes);

        assertTrue("connectionMap must be empty", connectionMap.isEmpty());
    }
}

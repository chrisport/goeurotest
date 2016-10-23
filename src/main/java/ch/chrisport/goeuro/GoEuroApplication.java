package ch.chrisport.goeuro;

import ch.chrisport.goeuro.service.RouteService;
import ch.chrisport.goeuro.service.impl.DirectRouteServiceImpl;
import ch.chrisport.goeuro.util.RouteUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SpringBootApplication
public class GoEuroApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoEuroApplication.class, args);
    }

    @Bean
    public RouteService directRouteService(@Value("${routes.filename}") String routesFilename) throws IOException {
        List<String> routes = Files.readAllLines(Paths.get(routesFilename));
        routes = routes.subList(1, routes.size());
        Map<Integer, Set<Integer>> directRoutesMap = RouteUtil.extractDirectConnections(routes);
        return new DirectRouteServiceImpl(directRoutesMap);
    }
}

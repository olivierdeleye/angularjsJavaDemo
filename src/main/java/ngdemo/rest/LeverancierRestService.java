package ngdemo.rest;

import java.util.List;
import ngdemo.domain.*;
import ngdemo.service.LeverancierService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/leveranciers")
public class LeverancierRestService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Leverancier> getLeveranciersInJSON() {
        LeverancierService leverancierService = new LeverancierService();
        return leverancierService.getLeveranciers();
    }
}

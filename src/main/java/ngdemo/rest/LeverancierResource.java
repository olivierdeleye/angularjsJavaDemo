package ngdemo.rest;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import ngdemo.domain.*;


import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ngdemo.dao.LeverancierDAO;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


@Path("/leveranciers")
public class LeverancierResource {

   
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Leverancier> getLeveranciers() {
       
        LeverancierDAO leverancierDao = new LeverancierDAO();
        List <Leverancier>  leveranciers = leverancierDao.findAllLeveranciers();
        
        return leveranciers;
    }
    
  
    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Leverancier saveNewLeverancier(JSONObject formData) throws JSONException{
        JSONObject leverancierObject = formData.getJSONObject("leverancier");
        String lev_naam = (String)leverancierObject.get("lev_naam");
        String leverancierNr = (String)leverancierObject.get("leverancierNr");
        String straat = (String)leverancierObject.get("straat");
        String huisNr = (String)leverancierObject.get("huisNr");
        String bus = (String)leverancierObject.get("bus");
        String gemeente = (String)leverancierObject.get("gemeente");
        String postcode = (String)leverancierObject.get("postcode");
        String telefoon = (String)leverancierObject.get("telefoon");
        String fax = (String)leverancierObject.get("fax");
        String contact = (String)leverancierObject.get("contact");
        String opmerking = (String)leverancierObject.get("opmerking");
        String groepNaam = (String)leverancierObject.get("groepNaam");
        Leverancier leverancier;

        LeverancierDAO leverancierDao = new LeverancierDAO();
   
        leverancier = leverancierDao.createLeverancier(leverancierNr, lev_naam, straat, huisNr, bus, gemeente, postcode, telefoon, fax, contact, opmerking, groepNaam);

        return leverancier;

       
    }
    
    @Path("/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Leverancier updateLeverancier(JSONObject formData) throws JSONException{
        JSONObject leverancierObject = formData.getJSONObject("leverancier");
        String lev_naam = (String)leverancierObject.get("lev_naam");
        String leverancierNr = (String)leverancierObject.get("leverancierNr");
        String straat = (String)leverancierObject.get("straat");
        String huisNr = (String)leverancierObject.get("huisNr");
        String busNr = (String)leverancierObject.get("busNr");
        String gemeente = (String)leverancierObject.get("gemeente");
        String postcode = (String)leverancierObject.get("postcode");
        String telefoon = (String)leverancierObject.get("telefoon");
        String fax = (String)leverancierObject.get("fax");
        String contact = (String)leverancierObject.get("contactpersoon");
        String opmerking = (String)leverancierObject.get("opmerking");
        JSONObject groepObject = leverancierObject.getJSONObject("groep");
        String groepNaam = (String)groepObject.get("groepNaam");
        Leverancier leverancier;

        LeverancierDAO leverancierDao = new LeverancierDAO();
   
        leverancier = leverancierDao.updateLeverancier(leverancierNr, lev_naam, straat, huisNr, busNr, gemeente, postcode, telefoon, fax, contact, opmerking, groepNaam);

        return leverancier;
    }
    
    @Path("/delete")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteLeverancier(JSONObject formData) throws JSONException{
  
        String leverancierNr = (String)formData.get("leverancierId");
     
        LeverancierDAO leverancierDao = new LeverancierDAO();
   
        leverancierDao.deleteLeverancier(leverancierNr);

    }

}

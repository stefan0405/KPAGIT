package studentRest;

import data.Login;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import exceptions.KPAException;
import data.Personalizovanaobavestenja;
import data.PersonalizovanaobavestenjaMarshall;
import data.Prakticnanastava;
import data.Sakt;
import data.Spi;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import service.ObavestenjeService;
import service.PersonalizovanaobavestenjaService;
import service.PrakticnanastavaService;
import service.SaktService;
import service.SpiService;
import util.UtilMethods;

@Path("studentREST")
public class PersonalizovanaobavestenjaREST implements ExceptionMapper<Exception> {

    private final PersonalizovanaobavestenjaService personalizovanaobavestenjaService = PersonalizovanaobavestenjaService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public PersonalizovanaobavestenjaMarshall getAllPersonalizovanaobavestenja(@Context HttpServletRequest request, @Context HttpHeaders header, @Context HttpServletResponse response) {
        
        PersonalizovanaobavestenjaMarshall po= new PersonalizovanaobavestenjaMarshall();
        
        response.setHeader("Authorization", header.getHeaderString("Authorization"));
        response.setStatus(200);
        
        Login s = (Login) request.getSession().getAttribute("korisnik");
                
        String brojIndeksa = s.getStudent().getBrindeksa();
        
        po.setS(s.getStudent());
        po.setfKSkat(SaktService.getInstance().getSaktByIndeks(brojIndeksa));
        po.setfKPrakticnaNastava(PrakticnanastavaService.getInstance().getSaktByIndeks(brojIndeksa));
        po.setfKSpi(SpiService.getInstance().getSpiByIndeks(brojIndeksa));
        po.setfKObavestenja(ObavestenjeService.getInstance().getAll());
        
        return po;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Personalizovanaobavestenja getPersonalizovanaobavestenjaById(@PathParam("id") int id) {
        return personalizovanaobavestenjaService.getPersonalizovanaobavestenjaById(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPersonalizovanaobavestenja(Personalizovanaobavestenja personalizovanaobavestenja) {
        try {
            final Personalizovanaobavestenja newPersonalizovanaobavestenja = personalizovanaobavestenjaService.add(personalizovanaobavestenja);
            return Response.ok().entity(newPersonalizovanaobavestenja).build();
        } catch (KPAException exception) {
            Logger.getLogger(PersonalizovanaobavestenjaREST.class.getName()).log(Level.SEVERE, null, exception);
            return cannotCreatePersonalizovanaobavestenjaMessage(exception);
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePersonalizovanaobavestenja(Personalizovanaobavestenja personalizovanaobavestenja) {
        try {
            final Personalizovanaobavestenja updatedPersonalizovanaobavestenja = personalizovanaobavestenjaService.edit(personalizovanaobavestenja);
            return Response.ok().entity(updatedPersonalizovanaobavestenja).build();
        } catch (KPAException exception) {
            Logger.getLogger(PersonalizovanaobavestenjaREST.class.getName()).log(Level.SEVERE, null, exception);
            return cannotUpdatePersonalizovanaobavestenjaMessage(exception);
        }

    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePersonalizovanaobavestenja(@PathParam("id") int id) {
        try {
            Personalizovanaobavestenja personalizovanaobavestenja = personalizovanaobavestenjaService.getPersonalizovanaobavestenjaById(id);
            personalizovanaobavestenjaService.delete(personalizovanaobavestenja);
            return deletedPersonalizovanaobavestenjaMessage(id);
        } catch (KPAException ex) {
            Logger.getLogger(PersonalizovanaobavestenjaREST.class.getName()).log(Level.SEVERE, null, ex);
            return cannotDeletePersonalizovanaobavestenjaMessage(ex);
        }
    }

    // -------------------------------------------------------------------------
    // Pomoćne metode za rad sa porukuama
    //
    private Response cannotCreatePersonalizovanaobavestenjaMessage(KPAException exception) {
        final String errorMessage = "Novi član nije kreiran: " + exception.getMessage();
        final String jsonErroMessage = UtilMethods.createJsonErrorMessage(errorMessage);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(jsonErroMessage)
                .build();
    }

    private Response cannotUpdatePersonalizovanaobavestenjaMessage(KPAException exception) {
        final String errorMessage = "Novi član nije kreiran: " + exception.getMessage();
        final String jsonErroMessage = UtilMethods.createJsonErrorMessage(errorMessage);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(jsonErroMessage)
                .build();
    }

    private Response deletedPersonalizovanaobavestenjaMessage(int id) {
        final String message = "Uspešno obrisan član sa id-om: " + id + ".";
        final String jsonMessage = UtilMethods.createJsonMessage(message);

        return Response.ok()
                .entity(jsonMessage)
                .build();
    }

    private Response cannotDeletePersonalizovanaobavestenjaMessage(KPAException ex) {
        final String errorMessage = "Brisanje člana nije uspelo: " + ex.getMessage();
        final String jsonErrorMessage = UtilMethods.createJsonErrorMessage(errorMessage);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(jsonErrorMessage)
                .build();
    }

    @Override
    public Response toResponse(Exception exception) {
        exception.printStackTrace();
        return Response
                .status(Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity(exception.getCause())
                .build();
    }

}

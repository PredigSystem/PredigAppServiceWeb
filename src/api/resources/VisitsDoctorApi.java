package api.resources;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controllers.VisitsDoctorController;
import domain.VisitsDoctor;

@RequestScoped
@Path("/visitsDoctor")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VisitsDoctorApi {
	
	@GET
	public Response getAllVisitsList() {
		return Response.ok("Visits").build();
	}
	
    @GET
    @Path("/{patient}")
    public Response getVisitsDoctorList(@PathParam("patient") String id) {
        List<VisitsDoctor> visitDoctorList = VisitsDoctorController.getVisitsDoctorById(id);

        if(visitDoctorList.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(visitDoctorList).build();
    }

}

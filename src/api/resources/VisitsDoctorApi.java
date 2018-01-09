package api.resources;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controllers.BloodPressureController;
import controllers.UserController;
import controllers.VisitsDoctorController;
import domain.BloodPressure;
import domain.User;
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
    
    @GET
    @Path("/{patient}/last")
    public Response getLastVisitsDoctor(@PathParam("patient") String id) {
        VisitsDoctor visitsDoctor = VisitsDoctorController.getLastVisitsDoctorByUserId(id);

        if(visitsDoctor == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(visitsDoctor).build();
    }
    
    @POST
    @Path("/insertVisit")
    @Consumes (MediaType.APPLICATION_FORM_URLENCODED)
    public void insertVisit(@FormParam("id") String id,
							@FormParam("doctor") String doctor,
							@FormParam("date") String date,
							@FormParam("reason") String reason,
							@FormParam("time") String pstime,
							@Context HttpServletResponse response,
							@Context HttpServletRequest request) throws IOException 
    {
    		Date pdDate = new Date(date);
    		
    		VisitsDoctor visitsDoctor = new VisitsDoctor(id, doctor, pdDate.getTime(), pstime, reason);
				
		visitsDoctor = VisitsDoctorController.insertVisit(visitsDoctor);
		
        if (visitsDoctor != null) {
            response.sendRedirect("/PredigAppServiceWeb/table.jsp");
        } else {
            request.setAttribute("insertVisit", "Creation visit failed");
        }
    }

}

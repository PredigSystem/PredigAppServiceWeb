package api.resources;

import controllers.BloodPressureController;
import domain.BloodPressure;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/bloodPressure")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BloodPressureAPI {

    @GET
    public Response getBloodPressureList(){
        return Response.ok("Blabla").build();
    }

    @GET
    @Path("/{patient}")
    public Response getBloodPressureList(@PathParam("patient") String id) {
        List<BloodPressure> bloodPressureList = BloodPressureController.getBloodPressureByUserId(id);

        if(bloodPressureList.isEmpty())
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(bloodPressureList).build();
    }

}

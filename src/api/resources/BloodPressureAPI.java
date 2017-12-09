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

    @GET
    @Path("/{patient}/last")
    public Response getLastBloodPressure(@PathParam("patient") String id) {
        BloodPressure bloodPressure = BloodPressureController.getLastBloodPressureByUserId(id);

        if(bloodPressure == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(bloodPressure).build();
    }

    @POST
    public Response create(final BloodPressure bloodPressure) {
        if (BloodPressureController.create(bloodPressure) == null) {
            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } else {
            return Response.ok(bloodPressure).build();
        }
    }

}

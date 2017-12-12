package api.resources;

import controllers.UserController;
import domain.LogIn;
import domain.User;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@RequestScoped
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserAPI {

    @POST
    @Path("/logIn")
    public Response logIn(LogIn logIn) {
        String userId = UserController.logIn(logIn);

        if (userId != null) {
            return Response.ok(userId).build();
        } else {
            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/create")
    public Response create(User user) {
        user = UserController.createUser(user);
        if (user == null) {
            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } else {
            return Response.ok(user).build();
        }
    }


}
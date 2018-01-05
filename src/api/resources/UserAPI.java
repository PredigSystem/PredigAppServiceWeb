package api.resources;

import controllers.UserController;
import domain.LogIn;
import domain.User;
import domain.UserID;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
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
            return Response.ok(new UserID(userId)).build();
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

    @POST
    @Path("/update")
    public Response update(User user) {
        user = UserController.updateUser(user);
        if (user == null) {
            return Response.serverError().status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } else {
            return Response.ok(user).build();
        }
    }
    
    @POST
    @Path("/WebLogIn")
    @Consumes (MediaType.APPLICATION_FORM_URLENCODED)
    public void authenticate(@FormParam("username") String username,
            					@FormParam("password") String password,
            					@Context HttpServletResponse response,
							@Context HttpServletRequest request) throws IOException 
    {	
    		LogIn login  = new LogIn(username, password);
    		String userId = UserController.logIn(login);
    		
        if (userId != null) {
            request.getSession().setAttribute("loggedInUser", userId);
            response.sendRedirect("/PredigAppServiceWeb/table.jsp");
        } else {
            request.setAttribute("authenticationError", "Invalid email/password.");
        }
    	
    }
    
    @POST
    @Path("/createUser")
    @Consumes (MediaType.APPLICATION_FORM_URLENCODED)
    public void createUserWeb(@FormParam("name") String name,
							@FormParam("nif") String nif,
							@FormParam("email") String email,
							@FormParam("password") String password,
							@FormParam("address") String address,
							@FormParam("phone") String phone,
							@FormParam("role") String role,
							@Context HttpServletResponse response,
							@Context HttpServletRequest request) throws IOException 
    {	
		
		User user = new User(name, nif, password, email, Integer.parseInt(phone), address, null, role);
		
		user = UserController.createUser(user);
		
        if (user != null) {
            response.sendRedirect("/PredigAppServiceWeb/table.jsp");
        } else {
            request.setAttribute("creationUser", "Creation user failed");
        }
    }


}

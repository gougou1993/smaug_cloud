package smaug.cloud.api.interfaces;

import smaug.cloud.api.vos.TestResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by Allen on 17/10/10.
 */
@Path("test")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({"application/json;charset=UTF-8", "text/xml;charset=UTF-8"})
public interface TestService {

    @POST
    @Path("test")
    String test();

    @POST
    @Path("getUser")
    TestResponse getUser();
}

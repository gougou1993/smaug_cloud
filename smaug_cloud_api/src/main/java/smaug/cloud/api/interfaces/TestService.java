package smaug.cloud.api.interfaces;

import smaug.cloud.api.vos.article.ArticleResponse;
import smaug.cloud.api.vos.queue.Queuing;
import smaug.cloud.api.vos.test.TestResponse;
import smaug.cloud.api.vos.user.UserResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Allen on 17/10/10.
 */
@Path("test")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({"application/json;charset=UTF-8", "text/xml;charset=UTF-8"})
public interface TestService {

    @GET
    @Path("test")
    String test();


    @GET
    @Path("test2")
    String test2();

    @POST
    @Path("userList")
    List<UserResponse> userList();

    @POST
    @Path("articleList")
    List<ArticleResponse> articleList();

    @POST
    @Path("queue")
    List<Queuing> queue();
}

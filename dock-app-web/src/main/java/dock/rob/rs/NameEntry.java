package dock.rob.rs;

import java.util.logging.Logger;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;


/**
 * <code>NameEntry</code> is a JAX-RS class serving rest methods that
 * provide CRUD operations on a {@ NameRequest} instance.
 *
 * @author Rob Benton
 */
@Path("/")
public class NameEntry
{
  private static final Logger log = Logger.getLogger(NameEntry.class.getPackage().getName());
  
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public String get(@PathParam("id") String id)
  {
    log.info("GET request for id: " + id);
    return "{ name: \"test\" }";
  }
}

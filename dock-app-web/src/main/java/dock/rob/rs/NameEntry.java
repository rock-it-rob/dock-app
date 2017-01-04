package dock.rob.rs;

import javax.ws.rs.Path;
import javax.ws.rs.GET;


/**
 * <code>NameEntry</code> is a JAX-RS class serving rest methods that
 * provide CRUD operations on a {@ NameRequest} instance.
 *
 * @author Rob Benton
 */
@Path("/")
public class NameEntry
{
  @GET
  public String get()
  {
    return "test";
  }
}

package dock.rob.rs;

import java.util.logging.Logger;

import javax.ejb.EJB;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dock.rob.app.NameEntry;

import dock.rob.app.dblayer.TableAccessBean;
import dock.rob.app.dblayer.NameRequest;


/**
 * <code>NameEntry</code> is a JAX-RS class serving rest methods that
 * provide CRUD operations on a {@ NameRequest} instance.
 *
 * @author Rob Benton
 */
@Path("/")
public class NameEntryService
{
  private static final Logger log = Logger.getLogger(NameEntry.class.getPackage().getName());
  
  /**
   */
  public NameEntryService() {}
  
  /**
   * Retrives a {@ NameRequest} by its name property.
   *
   * @param name <code>String</code> the name property of the
   * {@ NameRequest}
   * @return the {@ NameRequest} matching the name parameter or null
   * if none was found.
   */
  @GET
  @Path("{name}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(@PathParam("name") String name)
  {
    log.info("GET request for name: " + name);
    
    TableAccessBean tableAccess = this.application.getTableAccessBean();
    if (tableAccess == null) { throw new RuntimeException("SHIT"); }
    NameRequest nr = tableAccess.getNameRequest(name);
    if (nr == null) { return null; }
    NameEntry ne = new NameEntry(nr);
    
    return Response.ok(ne).build();
  }
}

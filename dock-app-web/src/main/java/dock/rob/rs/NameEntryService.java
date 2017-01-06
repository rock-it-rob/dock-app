package dock.rob.rs;

import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;

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
@RequestScoped
public class NameEntryService
{
  private static final Logger log = Logger.getLogger(NameEntry.class.getPackage().getName());
  
  /**
   */
  public NameEntryService() {}
  
  @EJB
  private TableAccessBean tableAccess;
  
  /**
   * Retrives a {@ NameEntry} by its name property.
   *
   * @param name <code>String</code> the name property of the
   * {@ NameEntry}
   * @return json <code>Response</code> of a single {@ NameEntry}.
   */
  @GET
  @Path("{name}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response get(@PathParam("name") String name)
  {
    log.info("GET request for name: " + name);
    
    NameRequest nr = this.tableAccess.getNameRequest(name);
    if (nr == null)
    {
      log.warning("No NameRequest found for name: " + name);
      return null;
    }
    NameEntry ne = new NameEntry(nr);
    
    return Response.ok(ne).build();
  }
}

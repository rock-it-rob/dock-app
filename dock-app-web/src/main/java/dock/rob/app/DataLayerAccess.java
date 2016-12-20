package dock.rob.app;

import java.util.List;

import java.io.Serializable;

import javax.inject.Named;

import javax.enterprise.context.SessionScoped;

import javax.ejb.EJB;

import dock.rob.app.dblayer.TableAccessBean;
import dock.rob.app.dblayer.NameRequest;


/**
 * <code>DataLayerAccess</code> is a session-scoped CDI bean for calling
 * the {@ TableAccessBean} ejb and interacting with the data from the
 * data layer.
 *
 * @author Rob Benton
 */
@SessionScoped
@Named
public class DataLayerAccess implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  @EJB
  private TableAccessBean tableAccess;
  
  /**
   * Gets all the names from the database.
   */
  public List<NameRequest> getAllNameRequests()
  {
    return this.tableAccess.allNameRequests();
  }
}

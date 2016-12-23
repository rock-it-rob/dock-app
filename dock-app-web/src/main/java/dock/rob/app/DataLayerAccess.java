package dock.rob.app;

import java.util.List;
import java.util.ArrayList;

import java.util.logging.Logger;

import java.io.Serializable;

import javax.annotation.PostConstruct;

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
  
  private static final Logger log = Logger.getLogger(DataLayerAccess.class.getPackage().getName());
  
  @EJB
  private TableAccessBean tableAccess;
  
  /**
   * Property that holds all the name requests.
   */
  private ArrayList<NameRequest> nameRequests;
  
  /**
   */
  @PostConstruct
  private void init()
  {
    this.nameRequests = new ArrayList<NameRequest>();
    this.nameRequests.addAll(this.tableAccess.allNameRequests());
  }
  
  /**
   * Gets all the names from the database.
   */
  public List<NameRequest> getNameRequests() { return this.nameRequests; }
  
  /**
   * Updates any name requests that have been modified.
   */
  public void updateNameRequest(NameRequest nr)
  {
    NameRequest updated = this.tableAccess.update(nr);
    for (int i = 0; i < this.nameRequests.size(); ++i)
    {
      if (this.nameRequests.get(i).getId() == updated.getId())
      {
        this.nameRequests.set(i, updated);
      }
    }
  }
}

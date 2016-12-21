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
  private ArrayList<NameRequestImpl> nameRequests;
  
  /**
   */
  @PostConstruct
  private void init()
  {
    this.nameRequests = new ArrayList<NameRequestImpl>();
    for (NameRequest nr : this.tableAccess.allNameRequests())
    {
      this.nameRequests.add(new NameRequestImpl(nr));
    }
  }
  
  /**
   * Gets all the names from the database.
   */
  public List<NameRequestImpl> getNameRequests() { return this.nameRequests; }
  
  /**
   * Updates any name requests that have been modified.
   */
  public void updateNameRequest(NameRequestImpl nr)
  {
    NameRequest updated = this.tableAccess.update(nr);
    for (int i = 0; i < this.nameRequests.size(); ++i)
    {
      if (this.nameRequests.get(i).getId() == updated.getId())
      {
        this.nameRequests.set(i, new NameRequestImpl(updated));
      }
    }
  }
}

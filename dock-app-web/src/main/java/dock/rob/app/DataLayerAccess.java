package dock.rob.app;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

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
  
  @EJB
  private TableAccessBean tableAccess;
  
  /**
   * Property that holds all the name request entries.
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
  public List<NameRequestImpl> getAllNameRequests()
  {
    return this.nameRequests;
  }
  
  /**
   * Updates the name request.
   */
  public void updateNameRequest(NameRequestImpl nameRequest)
  {
    this.tableAccess.updateName(nameRequest.getOldName(), nameRequest.getName());
  }
}

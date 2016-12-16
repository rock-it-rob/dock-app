package dock.rob.app;

import java.io.Serializable;

import javax.inject.Named;

import javax.enterprise.context.SessionScoped;

import javax.ejb.EJB;

import dock.rob.app.dblayer.TableAccessBean;


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
   */
  public String getTheString()
  {
    return this.tableAccess.test();
  }
}

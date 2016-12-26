package dock.rob.app;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.Date;

import java.util.logging.Logger;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import javax.inject.Named;
import javax.inject.Inject;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.New;

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
   * Property that holds all the names. Since names cannot be duplicated on the
   * database we will use a map of names to update times.
   */
  private HashSet<NameEntry> names;
  
  /**
   */
  @PostConstruct
  private void init()
  {
    this.names = new HashSet<NameEntry>();
    for (NameRequest nr : this.tableAccess.allNameRequests())
    {
      NameEntry ne = this.nameEntryGenerator.get();
      ne.init(nr.getId(), nr.getName(), nr.getUpdated());
      this.names.add(ne);
    }
  }
  
  /**
   */
  public Set<NameEntry> getNames() { return this.names; }
  
  /**
   * Producer object which creates new instances of {@ NameEntry}.
   */
  /*
  @Produces
  @Name
  private NameEntry nameEntryProducer { return new NameEntry(); }
  */
  
  @Inject
  private Instance<NameEntry> nameEntryGenerator;
}

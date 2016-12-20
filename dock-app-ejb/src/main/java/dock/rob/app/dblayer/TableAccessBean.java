package dock.rob.app.dblayer;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;


/**
 * <code>TableAccessBean</code> is a simple EJB for accessing persistence
 * objects.
 *
 * @author Rob Benton
 */
@Stateless
public class TableAccessBean
{
  @PersistenceContext(unitName="dblayer-unit")
  private EntityManager entityManager;
  
  /**
   * Gets all records from the name request table.
   *
   * @return a List of {@ NameRequest}s
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public List<NameRequest> allNameRequests()
  {
    ArrayList<NameRequest> names = new ArrayList<NameRequest>();
    TypedQuery<dock.rob.app.dblayer.orm.NameRequest> query = this.entityManager.createNamedQuery("allNameRequests", dock.rob.app.dblayer.orm.NameRequest.class);
    for (dock.rob.app.dblayer.orm.NameRequest nr : query.getResultList())
    {
      names.add(nr);
    }
    
    return names;
  }
}

package dock.rob.app.dblayer;

import java.util.List;
import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dock.rob.app.dblayer.orm.NameRequest;


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
   * Gets all names from the name request table.
   *
   * @return <code>List<String></code>
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public List<String> allNames()
  {
    ArrayList<String> names = new ArrayList<String>();
    TypedQuery<NameRequest> query = this.entityManager.createNamedQuery("allNameRequests", NameRequest.class);
    for (NameRequest nr : query.getResultList())
    {
      names.add(nr.getName());
    }
    
    return names;
  }
}

package dock.rob.app.dblayer;

import java.util.List;

import javax.ejb.Stateless;

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
   * Test method.
   */
  public String test()
  {
    String value = null;
    TypedQuery<NameRequest> query = this.entityManager.createNamedQuery("allNameRequests", NameRequest.class);
    List<NameRequest> result = query.getResultList();
    // Just get the first result for now.
    if (result != null && result.size() > 0)
    {
      value = result.get(0).getName();
    }
    
    return value;
  }
}

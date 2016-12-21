package dock.rob.app.dblayer;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import dock.rob.app.dblayer.orm.NameRequestEntity;


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
    TypedQuery<NameRequestEntity> query = this.entityManager.createNamedQuery("allNameRequests", NameRequestEntity.class);
    for (NameRequestEntity nr : query.getResultList())
    {
      names.add(nr);
    }
    
    return names;
  }
  
  /**
   * Updates the name on a {@ NameRequest}.
   *
   * <p>
   * The provided {@ NameRequest} is looked up by its id and its name, and
   * update timestamp are updated.
   * 
   * @param nameRequest {@ NameRequest}
   * @return the update {@ NameRequest}
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public NameRequest update(NameRequest nameRequest)
  {
    NameRequestEntity nr = this.entityManager.find(NameRequestEntity.class, nameRequest.getId());
    nr.setName(nameRequest.getName());
    nr.setUpdated(new Date());
    this.entityManager.persist(nr);
    
    return nr;
  }
}

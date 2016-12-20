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
   * Updates the name on a {@ NameRequest}
   *
   * @param old <code>String</code> Old name value
   * @param update <code>String</code> new name value
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public void updateName(String old, String update)
  {
    NameRequestEntity nr = this.entityManager.find(NameRequestEntity.class, old);
    nr.setName(update);
    nr.setUpdated(new Date());
    nr.setAmount(nr.getAmount().add(new BigDecimal(1)));
    this.entityManager.persist(nr);
  }
}

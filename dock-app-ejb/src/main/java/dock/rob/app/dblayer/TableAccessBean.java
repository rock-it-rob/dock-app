package dock.rob.app.dblayer;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import java.math.BigDecimal;

import javax.annotation.Resource;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.EJBContext;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceException;

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
  
  @Resource
  private EJBContext ejbContext;
  
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
   * @param id <code>BigDecimal</code> primary key of the {@ NameRequest}
   * @param name <code>String</code> new value for the name.
   * @return the updated {@ NameRequest}
   * @throws {@ UpdateException} if the name could not be updated.
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public NameRequest updateName(BigDecimal id, String name) throws UpdateException
  {
    NameRequestEntity nr = null;
    
    try
    {
      nr = this.entityManager.find(NameRequestEntity.class, id);
      nr.setName(name);
      nr.setUpdated(new Date());
      this.entityManager.flush();  
    }
    catch (PersistenceException e)
    {
      this.ejbContext.setRollbackOnly();
      throw new UpdateException(e);
    }
    
    return nr;
  }
}

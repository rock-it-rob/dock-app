package dock.rob.app.dblayer;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import dock.rob.app.dblayer.orm.NameRequestEntity;


/**
 * <code>TableAccessBean</code> is a simple EJB for accessing data objects.
 *
 * @author Rob Benton
 */
@Singleton
public class TableAccessBean
{
  /**
   * This collection holds a collection of data objects. It simulates a table
   * on a database.
   */
  private HashMap<BigDecimal, NameRequestEntity> nameRequests;
  
  /**
   * Inititalize
   */
  @PostConstruct
  private void init()
  {
    this.nameRequests = new HashMap<BigDecimal, NameRequestEntity>();
    this.nameRequests.put(new BigDecimal(1), new NameRequestEntity(new BigDecimal(1), "Tom", new Date()));
    this.nameRequests.put(new BigDecimal(2), new NameRequestEntity(new BigDecimal(2), "Dick", new Date()));
    this.nameRequests.put(new BigDecimal(3), new NameRequestEntity(new BigDecimal(3), "Harry", new Date()));
  }
  
  /**
   * Gets all records.
   *
   * @return a List of {@ NameRequest}s
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public List<NameRequest> allNameRequests()
  {
    ArrayList<NameRequest> names = new ArrayList<NameRequest>();
    
    for (NameRequestEntity nr : this.nameRequests.values())
    {
      names.add(nr);
    }
    
    return names;
  }
  
  /**
   * Updates the name on a {@ NameRequest}.
   *
   * <p>
   * The provided {@ NameRequest} is looked up by its id and then its name and
   * update timestamp are updated.
   *
   * @param id <code>BigDecimal</code> primary key of the {@ NameRequest}
   * @param name <code>String</code> new value for the name.
   * @return the updated {@ NameRequest}
   * @throws {@ UpdateException}
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public NameRequest updateName(BigDecimal id, String name) throws UpdateException
  {
    // Prevent duplicate names.
    for (NameRequestEntity ne : this.nameRequests.values())
    {
      if (ne.getId().equals(id)) { continue; }
      if (ne.getName().equals(name))
      {
        throw new UpdateException("Duplicate name entry.");
      }
    }
    
    NameRequestEntity nr = this.nameRequests.get(id);
    
    if (nr == null)
    {
      throw new UpdateException(id.toString() + " id not found.");
    }
    
    nr.setName(name);
    nr.setUpdated(new Date());
    
    return nr;
  }
  
  /**
   * Gets a {@ NameRequest} by its name
   *
   * @param name <code>String</code>
   * @return the matching {@ NameRequest} or null if none was found.
   */
  @TransactionAttribute(TransactionAttributeType.REQUIRED)
  public NameRequest getNameRequest(String name)
  {
    NameRequest result = null;
    
    for (NameRequestEntity ne : this.nameRequests.values())
    {
      if (ne.getName().equals(name))
      {
        result = ne;
        break;
      }
    }
    
    return result;
  }
}

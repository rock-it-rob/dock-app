package dock.rob.rs;

import javax.ws.rs.ext.Provider;

import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;


/**
 * <code>CorsFilter</code> allows Cross-Origin requests for the REST JAX-RS
 * service classes.
 *
 * <p>
 * Since this application is not a real, live production app I am not implementing
 * security or restricting the domain.
 *
 * author Rob Benton
 */
@Provider
public class CorsFilter implements ContainerResponseFilter
{
  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
  {
    responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
    responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
    responseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type");
    responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
  }
}
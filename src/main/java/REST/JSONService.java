package REST;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/json")
public class JSONService {

	@GET
	@Path("/get")
	@Produces("application/json")
	public jsonData getProductInJSON() {

		jsonData jsonData = new jsonData();
		jsonData.setName("iPad 3");
		jsonData.setQty(999);
		
		return jsonData;

	}

	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response createProductInJSON(jsonData jsonData) {

		String result = "jsonData created : " + jsonData;
		return Response.status(201).entity(result).build();
		
	}
	
}
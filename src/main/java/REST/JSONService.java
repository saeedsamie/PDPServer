package REST;

import IO.CSVHandler;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/json")
public class JSONService {

    @GET
    @Path("/get")
    @Produces("application/json")
    public jsonData getProductInJSON() {

        jsonData jsonData = new jsonData();
//		jsonData.se("iPad 3");
//		jsonData.setQty(999);

        return jsonData;

    }

    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response createProductInJSON(jsonData jsonData) {

        String result = "jsonData created : " + jsonData.getDay() + " - " + jsonData.getDate() + " - " + jsonData.getPeriod() + " - " + jsonData.getStart_time() + " - " + jsonData.isPresence();
        System.out.println(result);
        if (jsonData.getStart_time().equals("7")) {
            CSVHandler.time_write("time_train.csv", "\n" + jsonData.getDay());
            switch (jsonData.getDay()) {
                case "Saturday":
                    CSVHandler.time_write("time_train.csv", ",0,TRUE,FALSE,FALSE,TRUE,FALSE,TRUE,TRUE");
                    break;
                case "Sunday":
                    CSVHandler.time_write("time_train.csv", ",0,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE");
                    break;
                case "Monday":
                    CSVHandler.time_write("time_train.csv", ",0,TRUE,FALSE,FALSE,TRUE,FALSE,TRUE,TRUE,FALSE");
                    break;
                case "Tuesday":
                    CSVHandler.time_write("time_train.csv", ",0,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE,FALSE");
                    break;
                case "Wednesday":
                    CSVHandler.time_write("time_train.csv", ",0,TRUE,TRUE,FALSE,FALSE,FALSE,FALSE,FALSE");
                    break;

            }
        }
        CSVHandler.time_write("time_train.csv", "," + String.valueOf(jsonData.isPresence()));
        return Response.status(201).entity(result).build();

    }

}
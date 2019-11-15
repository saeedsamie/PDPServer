package REST;

import IO.CSVHandler;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/rest")
public class JSONService {
//    @GET
//    @Path("/get/time")
//    @Produces("application/json")
//    public Response getTime() {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        System.out.println(dtf.format(now));
//        JSONObject json = new JSONObject();
//        json.put("time", dtf.format(now));
//        System.out.println(json.toString());
////        json.put("name", "student");
//        return Response.status(201).entity(json.toMap()).build();
//    }

    @POST
    @Path("/post")
    @Consumes("application/json")
    public Response createProductInJSON(jsonData jsonData) {

        String result = "jsonData created : " + jsonData.getDay()  + " - " + jsonData.getStart_time() + " - " + jsonData.isPresence();
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
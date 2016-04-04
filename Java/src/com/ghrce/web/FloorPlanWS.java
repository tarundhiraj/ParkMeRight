package com.ghrce.web;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import com.ghrce.dao.impl.ParkingDAOImpl;

@Path("/floor")
public class FloorPlanWS {

	@GET
	@Path("/get")
	@Produces("application/json")
	public String displayFloorPlan(String json)
	{
		String slotsJson;
		ParkingDAOImpl dao = new ParkingDAOImpl();
		slotsJson = dao.getAllSlots();
		return slotsJson;
		
	}
}

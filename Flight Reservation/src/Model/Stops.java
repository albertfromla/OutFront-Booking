package Model;

import java.sql.Timestamp;

public class Stops {
	private Timestamp arrTime;
	private int stopNo;
	private Timestamp deptTime;
	private String airportId;
	private String airlineId;
	private String airportName;
	private String airportCity;
	private int flightId;
	private int s_d_id;
	private String onTime;

	public String getOnTime() {
		return onTime;
	}

	public void setOnTime(int onTime) {
		if (onTime == 1)
			this.onTime = "On-Time";
		else
			this.onTime = "Delayed";
	}

	public int getStopNo() {
		return stopNo;
	}

	public void setStopNo(int stopNo) {
		this.stopNo = stopNo;
	}

	public String getAirportId() {
		return airportId;
	}

	public void setAirportId(String airportId) {
		this.airportId = airportId;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int fllightId) {
		this.flightId = fllightId;
	}

	public Timestamp getArrTime() {
		return arrTime;
	}

	public void setArrTime(Timestamp arrTime) {
		this.arrTime = arrTime;
	}

	public Timestamp getDeptTime() {
		return deptTime;
	}

	public void setDeptTime(Timestamp deptTime) {
		this.deptTime = deptTime;
	}

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public String getAirportCity() {
		return airportCity;
	}

	public void setAirportCity(String airportCity) {
		this.airportCity = airportCity;
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public int getS_d_id() {
		return s_d_id;
	}

	public void setS_d_id(int s_d_id) {
		this.s_d_id = s_d_id;
	}

}

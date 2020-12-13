package Model;

import java.sql.Timestamp;

public class TripDetail {
	private Timestamp departDate;
	private Timestamp returnDate;
	private String flyinfFromAirport;
	private String destinationAirport;
	private boolean isNonStop;
	private boolean isOneWay;

	private int flightId;
	private String airlineId;
	private int stops;
	private Timestamp arrTime;
	private Timestamp deptTime;
	private int s_d_id;
	private int seats;
	private int totalFare;
	private int startStop;

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public Timestamp getDepartDate() {
		return departDate;
	}

	public void setDepartDate(Timestamp departDate) {
		this.departDate = departDate;
	}

	public Timestamp getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Timestamp returnDate) {
		this.returnDate = returnDate;
	}

	public String getFlyinfFromAirport() {
		return flyinfFromAirport;
	}

	public void setFlyinfFromAirport(String flyinfFromAirport) {
		this.flyinfFromAirport = flyinfFromAirport;
	}

	public String getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public boolean isNonStop() {
		return isNonStop;
	}

	public void setNonStop(boolean isNonStop) {
		this.isNonStop = isNonStop;
	}

	public boolean isOneWay() {
		return isOneWay;
	}

	public void setOneWay(boolean isOneWay) {
		this.isOneWay = isOneWay;
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public int getStops() {
		return stops;
	}

	public void setStops(int stops) {
		this.stops = stops;
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

	public int getS_d_id() {
		return s_d_id;
	}

	public void setS_d_id(int s_d_id) {
		this.s_d_id = s_d_id;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(int totalFare) {
		this.totalFare = totalFare;
	}

	public int getStartStop() {
		return startStop;
	}

	public void setStartStop(int startStop) {
		this.startStop = startStop;
	}

}

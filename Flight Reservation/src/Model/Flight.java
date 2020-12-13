package Model;

public class Flight {
	private int id;
	private int daysOfOperating;
	private int totalSeats;
	private String airlineId;
	private double fare;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDaysOfOperating() {
		return daysOfOperating;
	}

	public void setDaysOfOperating(int daysOfOperating) {
		this.daysOfOperating = daysOfOperating;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

}

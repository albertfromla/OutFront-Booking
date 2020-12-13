package Model;

import java.sql.Timestamp;

public class Reservation {
	private int resrId;
	private Timestamp deptDate;
	private String seatNo;
	private String meal;
	private String classType;
	private int legNo;
	private int fromStopNo;
	private int flightNo;
	private int passId;
	private String airlineId;
	private String dept;
	private String arri;
	private String domestic;

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getArri() {
		return arri;
	}

	public void setArri(String arri) {
		this.arri = arri;
	}

	public int getResrId() {
		return resrId;
	}

	public void setResrId(int resrId) {
		this.resrId = resrId;
	}

	public Timestamp getDeptDate() {
		return deptDate;
	}

	public void setDeptDate(Timestamp deptDate) {
		this.deptDate = deptDate;
	}

	public String getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}

	public String getMeal() {
		return meal;
	}

	public void setMeal(String meal) {
		this.meal = meal;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public int getLegNo() {
		return legNo;
	}

	public void setLegNo(int legNo) {
		this.legNo = legNo;
	}

	public int getFromStopNo() {
		return fromStopNo;
	}

	public void setFromStopNo(int fromStopNo) {
		this.fromStopNo = fromStopNo;
	}

	public int getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(int flightNo) {
		this.flightNo = flightNo;
	}

	public int getPassId() {
		return passId;
	}

	public void setPassId(int passId) {
		this.passId = passId;
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public String getDomestic() {
		return domestic;
	}

	public void setDomestic(String domestic) {
		this.domestic = domestic;
	}

}

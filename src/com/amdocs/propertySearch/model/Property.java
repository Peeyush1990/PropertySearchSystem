package com.amdocs.propertySearch.model;

public class Property {

	private final int propertyId;
    private final String noOfRooms;
    private final double areaInSqft;
    private final int floorNo;
    private final String city;
    private final String state;
    private double cost;
    private String ownerName;
	private String ownerContactNo;
	
	static int id=1;
	
	public Property(String noOfRooms, double areaInSqft, int floorNo, String city, String state,
			double cost, String ownerName, String ownerContactNo) {
		this.propertyId = id++;
		this.noOfRooms = noOfRooms;
		this.areaInSqft = areaInSqft;
		this.floorNo = floorNo;
		this.city = city;
		this.state = state;
		this.cost = cost;
		this.ownerName = ownerName;
		this.ownerContactNo = ownerContactNo;
	}

	public Property(int id2, String room, double area, int floor, String city, String state, double cost,
			String oName, String oNumber) {
		this.propertyId = id2;
		this.noOfRooms = room;
		this.areaInSqft = area;
		this.floorNo = floor;
		this.city = city;
		this.state = state;
		this.cost = cost;
		this.ownerName = oName;
		this.ownerContactNo = oNumber;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerContactNo() {
		return ownerContactNo;
	}

	public void setOwnerContactNo(String ownerContactNo) {
		this.ownerContactNo = ownerContactNo;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public String getNoOfRooms() {
		return noOfRooms;
	}

	public double getAreaInSqft() {
		return areaInSqft;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}
	
	@Override
    public String toString() {
        return "Property{" +
                "propertyId=" + propertyId +
                ", noOfRooms='" + noOfRooms + '\'' +
                ", areaInSqft='" + areaInSqft + '\'' +
                ", floorNo='" + floorNo + '\'' +
                ", city=" + city +
                ", state=" + state +
                ", cost=" + cost +
                ", ownerName='" + ownerName + '\'' +
                ", ownerContactNo="+ ownerContactNo + '\''+
                '}';
    }

}
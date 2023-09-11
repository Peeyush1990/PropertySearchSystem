package com.amdocs.propertySearch.dao;
import com.amdocs.propertySearch.model.Property;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class PropertyDAO {
	static Connection connect=DBconnection.getConnection();
	private static final String property_ADD="insert into Properties values(?,?,?,?,?,?,?,?,?,?)";
	private static final String property_DELETE="delete from Properties where id=?";
	private static final String property_UPDATE="update Properties set cost=? where id=?";
	private static final String property_SEARCH="select * from Properties";
	private static final String property_SEARCH_CITY="select * from Properties where city=?";
	private static final String property_SEARCH_COST="select * from Properties where price between ? and ?";
	private static final String property_SEARCH_ROOMSCITY="select * from Properties where city=? and no_rooms=?";
	
	private final PreparedStatement addStatement;
	private final PreparedStatement deleteStatement;
	private final PreparedStatement updateStatement;
	private final PreparedStatement searchAllStatement;
	private final PreparedStatement searchCityStatement;
	private final PreparedStatement searchCostStatement;
	private final PreparedStatement searchRoomCityStatement;
	
	
	//constructor to initialize final variables
	public PropertyDAO() throws SQLException {
		addStatement=connect.prepareStatement(property_ADD);
		deleteStatement=connect.prepareStatement(property_DELETE);
		updateStatement=connect.prepareStatement(property_UPDATE);
		searchAllStatement=connect.prepareStatement(property_SEARCH);
		searchCityStatement=connect.prepareStatement(property_SEARCH_CITY);
		searchCostStatement=connect.prepareStatement(property_SEARCH_COST);
		searchRoomCityStatement=connect.prepareStatement(property_SEARCH_ROOMSCITY);

	}
     
	//functions
	//adding new
	public int addProperty(Property property) throws SQLException{	
		int count=0;
		
		addStatement.setInt(1,property.getPropertyId());
		addStatement.setString(2,property.getNoOfRooms());
		addStatement.setDouble(3,property.getAreaInSqft());
		addStatement.setInt(4,property.getFloorNo());
		addStatement.setString(5,property.getCity());
		addStatement.setString(6,property.getState());
		addStatement.setDouble(7,property.getCost());
		addStatement.setString(8,property.getOwnerName());
		addStatement.setString(9,property.getOwnerContactNo());
		
        count=addStatement.executeUpdate();
		return count;
	}
	
	//deleting already existing
	public int deleteProperty(int id) throws SQLException {
		int count=0;
		
		deleteStatement.setInt(1,id);
		
		count=deleteStatement.executeUpdate();
		return count;
	}
	
	//updating already existing
	public boolean updateProperty(int id, double cost) throws SQLException {
	    boolean status=false;
	    
	    updateStatement.setDouble(1,cost);
	    updateStatement.setInt(2,id);
	    status = updateStatement.executeUpdate() > 0;
	    
	    return status;
	}
	
	//search by city
	public List<Property> searchByCity(String city) throws SQLException{
		List<Property> list=new ArrayList<Property>();
		searchCityStatement.setString(1,city);
		ResultSet rs=searchCityStatement.executeQuery();
		while(rs.next()) {
			int id=rs.getInt(1);
			String room=rs.getString(2);
			double area=rs.getDouble(3);
			int floor=rs.getInt(4);
			String city2=rs.getString(5);
			String state=rs.getString(6);
			double cost=rs.getDouble(7);
			String oName=rs.getString(8);
			String oNumber=rs.getString(9);
			
            Property prop=new Property(id,room,area,floor,city2,state,cost,oName,oNumber);
            list.add(prop);
		}
		
		return list;
	}
	
	//search all
	public List<Property> showAllProperties() throws SQLException{
		List<Property> list=new ArrayList<Property>();
		ResultSet rs=searchAllStatement.executeQuery();
		while(rs.next()) {
			int id=rs.getInt(1);
			String room=rs.getString(2);
			double area=rs.getDouble(3);
			int floor=rs.getInt(4);
			String city=rs.getString(5);
			String state=rs.getString(6);
			double cost=rs.getDouble(7);
			String oName=rs.getString(8);
			String oNumber=rs.getString(9);
			
            Property prop=new Property(id,room,area,floor,city,state,cost,oName,oNumber);
            list.add(prop);
		}	
		return list;
	}
	
	//search by cost
	public List<Property> searchByCost(double low, double high) throws SQLException{
		List<Property> list=new ArrayList<Property>();
		searchCostStatement.setDouble(1,low);
		searchCostStatement.setDouble(2,high);
		ResultSet rs=searchCostStatement.executeQuery();
		while(rs.next()) {
			int id=rs.getInt(1);
			String room=rs.getString(2);
			double area=rs.getDouble(3);
			int floor=rs.getInt(4);
			String city=rs.getString(5);
			String state=rs.getString(6);
			double cost=rs.getDouble(7);
			String oName=rs.getString(8);
			String oNumber=rs.getString(9);
			
            Property prop=new Property(id,room,area,floor,city,state,cost,oName,oNumber);
            list.add(prop);
		}	
		return list;
	}
	
	//search by rooms and city
	public List<Property> searchByNoOfRoomsAndCity(String rooms, String city) throws SQLException{
		List<Property> list=new ArrayList<Property>();
		searchRoomCityStatement.setString(1,city);
		searchRoomCityStatement.setString(2,rooms);
		ResultSet rs=searchCostStatement.executeQuery();
		while(rs.next()) {
			int id=rs.getInt(1);
			String room=rs.getString(2);
			double area=rs.getDouble(3);
			int floor=rs.getInt(4);
			String city2=rs.getString(5);
			String state=rs.getString(6);
			double cost=rs.getDouble(7);
			String oName=rs.getString(8);
			String oNumber=rs.getString(9);
			
            Property prop=new Property(id,room,area,floor,city2,state,cost,oName,oNumber);
            list.add(prop);
		}	
		return list;
	}
	
	
	
}
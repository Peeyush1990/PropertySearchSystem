package com.amdocs.propertySearch.main;
import java.sql.SQLException;
import java.util.Scanner;
import com.amdocs.propertySearch.dao.PropertyDAO;
import com.amdocs.propertySearch.exception.PropertyException;
import com.amdocs.propertySearch.model.Property;
import java.util.List;

public class PropertyMain {
	static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) throws SQLException {
           System.out.println("Welcome to Property Search System. Please select an operation you want ot perform: \n1. Add new property");
           System.out.println("2. Update property cost \n3. Delete Prperty \n4. Find by city \n5. View all properties \n6. Find by cost" );
           System.out.println("7. Find by no of rooms and city \n8. Exit");
    
           int n=sc.nextInt();
         loop:  while(true) {
           switch (n) {
           
           case 1 -> addNewProperty();
             
           case 2 -> deleteExistingProperty();
             
           case 3 -> updateExisitingProperty();
           
           case 4 -> searchCity();
           
           case 5 -> searchAll();
           
           case 6 -> searchCost();
           
           case 7 -> searchRoomsCity();
           
           case 8 -> { break loop; } 
            
           default ->  System.out.println("Please select specified options only");
         }
           }
           sc.close();
	}
	
	
	//functions
	//to add
	static void addNewProperty() {
		System.out.print("Please enter following details. \nNo of rooms: ");
		String rooms=sc.nextLine();
		System.out.print("Area in square feet: ");
		double area=sc.nextDouble();
		System.out.print("Floor no: ");
		int floor=sc.nextInt();
		System.out.print("City name: ");
		String city=sc.nextLine();
		System.out.print("State name: ");
		String state=sc.nextLine();
		System.out.print("Cost: ");
		double cost=sc.nextDouble();
		System.out.print("Owner name: ");
		String name=sc.nextLine();
		System.out.print("Owner contact no: ");
		String contact=sc.nextLine();
		
   try {
	Property property=new Property(rooms,area,floor,city,state,cost,name,contact);	
	PropertyDAO p = new PropertyDAO();
	
	System.out.println(p.addProperty(property)+" row added");	
	}catch(Exception e) {
		try {
		throw new PropertyException("Row not added");
	    }catch(PropertyException e1) {	}
	}  
  }
	
	//to delete
	static void deleteExistingProperty() {
		System.out.print("Enter id of property you want to delete: ");
		int id=sc.nextInt();
		
		try {
		PropertyDAO p = new PropertyDAO();
		
        System.out.println(p.deleteProperty(id)+" row deleted");   
		}catch(Exception e){
			try {
				throw new PropertyException("Row not deleted");
			}catch(PropertyException e1) { }
		}
	}
	
	//to update
	static void updateExisitingProperty() {
		System.out.print("Enter id of property: ");
		int id=sc.nextInt();
		System.out.print("Enter new cost of property: ");
		double cost=sc.nextDouble();
             
		try {
			PropertyDAO p = new PropertyDAO();
			p.updateProperty(id, cost);
				System.out.println("Cost of property updated");
			}catch (SQLException e) {
				try {
				throw new PropertyException("Cost of property not updated");
				}catch(PropertyException e1) { }
		}	
	}
	
	//search by city
	static void searchCity(){
		System.out.print("Enter name of city you want to search properties in: ");
	    String city=sc.nextLine();
	    try {
			PropertyDAO p = new PropertyDAO();
			List<Property> list=p.searchByCity(city);
			if(list.size()<1) throw new SQLException();
			System.out.println(list);			
			
			}catch (SQLException e) {
				try {
				throw new PropertyException("No properties to show");
				}catch(PropertyException e1) { }
		}
	}
	
	//show all properties
	static void searchAll(){
		try {
			PropertyDAO p = new PropertyDAO();
			List<Property> list=p.showAllProperties();
			if(list.size()<1) throw new SQLException();
			System.out.println(list);			
			
			}catch (SQLException e) {
				try {
				throw new PropertyException("No properties to show");
				}catch(PropertyException e1) { }
		}
	}
	
	//search by cost
	static void searchCost(){
		System.out.print("Enter lowest cost: ");
		double low=sc.nextDouble();
		System.out.print("Enter highest cost: ");
		double high=sc.nextDouble();
		try {
			PropertyDAO p = new PropertyDAO();
			List<Property> list=p.searchByCost(low, high);
			if(list.size()<1) throw new SQLException();
			System.out.println(list);			
			
			}catch (SQLException e) {
				try {
				throw new PropertyException("No properties to show");
				}catch(PropertyException e1) { }
		}

	}
	
	//search by number of rooms and city
    static void searchRoomsCity(){
    	System.out.print("Enter number of rooms: ");
		String rooms =sc.nextLine();
		System.out.print("Enter name of city: ");
		String city =sc.nextLine();
		try {
			PropertyDAO p = new PropertyDAO();
			List<Property> list=p.searchByNoOfRoomsAndCity(rooms,city);
			if(list.size()<1) throw new SQLException();
			System.out.println(list);			

		}catch (SQLException e) {
			try {
			throw new PropertyException("No properties to show");
			}catch(PropertyException e1) { }
	     }
       }
}
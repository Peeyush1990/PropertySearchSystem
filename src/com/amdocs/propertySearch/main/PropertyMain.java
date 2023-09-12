package com.amdocs.propertySearch.main;
import java.sql.SQLException;
import java.util.Scanner;
import com.amdocs.propertySearch.dao.PropertyDAO;
import com.amdocs.propertySearch.exception.PropertyException;
import com.amdocs.propertySearch.model.Property;

import java.util.InputMismatchException;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PropertyMain {
	static Scanner sc=new Scanner(System.in);
	static BufferedReader input = new BufferedReader (new InputStreamReader (System.in));
	public static void main(String[] args) throws SQLException {
     
         loop:  while(true) {
        	 System.out.println("Welcome to Property Search System. Please select an operation you want to perform: \n1. Add new property");
             System.out.println("2. Update property cost \n3. Delete Prperty \n4. Find by city \n5. View all properties \n6. Find by cost" );
             System.out.println("7. Find by no of rooms and city \n8. Exit\n");
        	 int n=sc.nextInt();
           switch (n) {
           
           case 1 -> addNewProperty();
             
           case 2 -> updateExisitingProperty();
             
           case 3 -> deleteExistingProperty();
           
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
		String rooms=null;
		try {
			rooms = input.readLine();
			if(Integer.parseInt(rooms)<0) {
				System.out.println("Number of rooms cannot be nagative");
				return;
			}
		} catch (Exception e2) {
			System.out.println("Error in input");
			return;
		}
		
		System.out.print("Area in square feet: ");
		double area=0;
		try{
			area=sc.nextDouble();
			if(area<0) throw new PropertyException(""); 
			}
		catch(Exception e1) {
			System.out.println("Please enter correct data");
			if(sc.hasNext()) sc.next();
			return;
		}
		
		System.out.print("Floor no: ");
		int floor=0;
		try{
			floor=sc.nextInt();
			if(floor<0) throw new PropertyException(""); 
			}catch(Exception e) { 
				System.out.println("Please enter correct data");
				if(sc.hasNext()) sc.next();
				return; }
		
		System.out.print("City name: ");
		String city=null;
		try {
			city = input.readLine();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		System.out.print("State name: ");
		String state=null;
		try {
			state = input.readLine();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		System.out.print("Cost: ");
		double cost=0;
		try{
			cost=sc.nextDouble();
			if(cost<0) throw new PropertyException("Please enter correct data"); 
			}catch(Exception e) { 
				System.out.println("Please enter correct data");
				if(sc.hasNext()) sc.next();
				return; }
		
		System.out.print("Owner name: ");
		String name=null;
		try {
			name = input.readLine();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		System.out.print("Owner contact no: ");
		String contact=null;
		try {
			contact = input.readLine();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
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
		try{
			if(cost<0) throw new PropertyException("Cost of property cannot be negative"); 
			}catch(PropertyException e) { return; }
		  
		try {
			PropertyDAO p = new PropertyDAO();
			if(p.updateProperty(id, cost))
				System.out.println("Cost of property updated");
			else System.out.println("Id not found");
			}catch (SQLException e) {
				try {
				throw new PropertyException("Cost of property not updated");
				}catch(PropertyException e1) { }
		}	
	}
	
	//search by city
	static void searchCity(){
		System.out.print("Enter name of city you want to search properties in: ");
		String city=null;
		try {
			city = input.readLine();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	    try {
			PropertyDAO p = new PropertyDAO();
			List<Property> list=p.searchByCity(city);
			if(list.size()<1) throw new SQLException();
			//System.out.println(list);
			list.forEach(System.out::println);
			
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
			//System.out.println(list);	
			list.forEach(System.out::println);
			
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
			if(low>high||high<0||low<0) throw new PropertyException("Enter correct data");
		}catch(PropertyException e) { return; }
		try {
			PropertyDAO p = new PropertyDAO();
			List<Property> list=p.searchByCost(low, high);
			if(list.size()<1) throw new SQLException();
			//System.out.println(list);
			list.forEach(System.out::println);

			
			}catch (SQLException e) {
				try {
				throw new PropertyException("No properties to show");
				}catch(PropertyException e1) { }
		}

	}
	
	//search by number of rooms and city
    static void searchRoomsCity(){
    	System.out.print("Enter number of rooms: ");
    	String rooms=null;
		try {
			rooms = input.readLine();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		System.out.print("Enter name of city: ");
		String city=null;
		try {
			city = input.readLine();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		try {
			PropertyDAO p = new PropertyDAO();
			List<Property> list=p.searchByNoOfRoomsAndCity(rooms,city);
			if(list.size()<1) throw new SQLException();
			//System.out.println(list);
			list.forEach(System.out::println);


		}catch (SQLException e) {
			try {
			throw new PropertyException("No properties to show");
			}catch(PropertyException e1) { }
	     }
       }
}

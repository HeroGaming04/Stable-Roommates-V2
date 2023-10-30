import java.util.ArrayList;

public class Room extends Project1{

	public Person roommate1;
	public Person roommate2;
	
	public Room(Person roommate1, Person roommate2)
	{
		this.roommate1 = roommate1;
		this.roommate2 = roommate2;
	}
	
	public Person getRoommate1() {
		return roommate1;
	}
	
	public Person getRoommate2() {
		return roommate2;
	}
	
	public void printRoom()
	{
		System.out.print("Popped Room: ");
		System.out.print("Person " + roommate1.getNumber());
		System.out.print(" Person " + roommate2.getNumber());
		System.out.println();
	}
	
	//Checks if a room is stable (WORKING!!! YAY)
	public boolean checkStable(ArrayList<Room> rooms) {
		
		boolean stable = true;
		
		if (rooms.size() == 0)
		{
			return true;
		}
		
		for (int i = 0; i < rooms.size(); ++i)
		{
			//Finds each person in the two rooms
			//Person 0 and 1 are in room 1
			//Person 2 and 3 are in room 2
			Person person0 = rooms.get(i).getRoommate1(); 
			Person person1 = rooms.get(i).getRoommate2();  
			Person person2 = roommate1; 
			Person person3 = roommate2; 
			
			//Check if person2 prefers person0 over person3
			if (person2.getRank(person3) > person2.getRank(person0)) {
				//Checks if person0 prefers person2 over person1
				if (person0.getRank(person2) < person0.getRank(person1)) {
					stable = false;
				}
			}
			
			//Checks if person2 prefers person1 over person3
			if (person2.getRank(person3) > person2.getRank(person1)) {
				//Checks if person1 prefers person2 over person0
				if (person1.getRank(person2) < person1.getRank(person0)) {
					stable = false;
				}
			}
			
			//Checks if person3 prefers person0 over person2
			if (person3.getRank(person2) > person3.getRank(person0)) {
				//Checks if person0 prefers person3 over person1
				if (person0.getRank(person3) < person0.getRank(person1)) {
					stable = false;
				}
			}
			
			//Checks if person3 prefers person1 over person2
			if (person3.getRank(person2) > person3.getRank(person1)) {
				//Checks if person1 prefers person3 over person0
				if (person1.getRank(person3) < person1.getRank(person0)) {
					stable = false;
				}
			}
		}
		
		return stable;
	}

}

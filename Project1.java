import java.util.ArrayList;
import java.util.Scanner;

public class Project1{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numPeople;
		int numRooms;
		
		Scanner input = new Scanner(System.in);
		
		numPeople = input.nextInt();
		numRooms = numPeople / 2;
		
		//Makes an array of people with preferences and number
		ArrayList<Person> people = new ArrayList<Person>();
				
		for (int i = 0; i < numPeople; ++i) {
			int[] preferences = new int[numPeople - 1];
			for (int j = 0; j < numPeople - 1; ++j) {
				preferences[j] = input.nextInt();		
			}	
			people.add(new Person(preferences, i, false));
		}
		
		StackInterface<Room> stack = new Stack<Room>(numRooms);
		ArrayList<Room> createdRooms = new ArrayList<Room>();
		Person roommate1, roommate2;
		int lastUsed = 0;
		boolean stable = false;
		
		
		//Runs while the stack is not full
		while (stack.isFull() == false)
		{
			
			//Creates the first and second roommates
			roommate1 = getAvailablePerson(people, -1);
			roommate2 = getAvailablePerson(people, lastUsed);
			lastUsed = roommate2.getNumber();
			
			Room newRoom = new Room(roommate1, roommate2);
			
			//Checks if the room is stable against all other created rooms
			stable = newRoom.checkStable(createdRooms);
			
			//Runs if the room is stable
			//Adds the room to the created rooms list and pushes it to the stack
			if (stable == true)
			{
				createdRooms.add(newRoom);
				stack.push(newRoom);
				lastUsed = 0;	
			}
			//Runs if the room is not stable
			//There are two separate possibilities
			if (stable == false)
			{
				
				//Sets the roommates to unused
				roommate1.setUsed(false);
				roommate2.setUsed(false);
				
				//Runs if the last roommate used is also the last available person in the person list
				if (roommate2.getNumber() == lastAvailablePerson(people).getNumber())
				{
					
					//Pops the stack
					Room poppedRoom = stack.pop();
					//Checks if the popped stack is null, if it is, the program will end as there isn't a possible arrangement
					if (poppedRoom == null)
					{
						System.out.println("There is no stable arrangement");
						System.exit(0);
					}
					//Sets the people in the popped stack to unused so they can be tried again
					people.get(poppedRoom.getRoommate1().getNumber()).setUsed(false);
					people.get(poppedRoom.getRoommate2().getNumber()).setUsed(false);
					//Removes the room that was popped from the created rooms list
					createdRooms.remove(poppedRoom);
					//This while loop does the exact same sequence as above 
					while (poppedRoom.getRoommate2().getNumber() == lastAvailablePerson(people).getNumber())
					{
						
						poppedRoom = stack.pop();
						if (poppedRoom == null)
						{
							System.out.println("There is no stable arrangement");
							System.exit(0);
						}
						poppedRoom.getRoommate1().setUsed(false);
						poppedRoom.getRoommate2().setUsed(false);
						createdRooms.remove(poppedRoom);
					}
					
					//Sets the lastUsed variable to the second roommate of the popped stack this way it will start trying the people after it first
					lastUsed = poppedRoom.getRoommate2().getNumber();
				}
				//This runs if the last roommate tried isn't the last available person
				//It will just try to check the stability of the next person in the person list
				else
				{
					createdRooms.remove(newRoom);
				}
			}
			
		}
		//If the stack is full then it prints out the results
		if(stack.isFull() == true)
		{
			for (int i = 0; i < numRooms; ++i)
			{
				Room poppedStack = stack.pop();
				System.out.println("Room " + (i + 1) + ": Person " + poppedStack.getRoommate1().getNumber() + " and Person " + poppedStack.getRoommate2().getNumber());
			}
		}
		input.close();
		System.exit(0);
		
	}	
	
	//Method for finding the next available person in the list
	public static Person getAvailablePerson(ArrayList<Person> people, int lastUsed)
	{
		//If it is the first person in the person list then it is returned
		if (lastUsed == -1 && people.get(0).getUsed() == false)
		{
			people.get(0).setUsed(true);
			return people.get(0);
		}
		else
		{
			//Checks for the next available person with used == false
			for (int i = lastUsed + 1; i < people.size(); ++ i)
			{
				if (people.get(i).getUsed() == false)
				{
					people.get(i).setUsed(true);
					return people.get(i);
				}
				
			}
		}
		return null;
	}
	
	//Finds the last available person in the person list
	public static Person lastAvailablePerson(ArrayList<Person> people)
	{
		for (int i = people.size() - 1;i > 0; --i)
		{
			if (people.get(i).getUsed() == false)
			{
				return people.get(i);
			}
		}
		return null;
	}
}


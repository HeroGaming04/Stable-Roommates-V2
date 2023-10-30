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
		
		
		
		while (stack.isFull() == false)
		{
			roommate1 = getAvailablePerson(people, -1);
			System.out.println("Roommate1 = " + roommate1.getNumber());
			roommate2 = getAvailablePerson(people, lastUsed);
			lastUsed = roommate2.getNumber();
			System.out.println("Roommate2 = " + roommate2.getNumber());
			Room newRoom = new Room(roommate1, roommate2);
			
			stable = newRoom.checkStable(createdRooms);
			
			if (stable == true)
			{
				createdRooms.add(newRoom);
				stack.push(newRoom);
				System.out.println("Pushed Room");
				
			}
			if (stable == false)
			{
				roommate1.setUsed(false);
				roommate2.setUsed(false);
				
				if (roommate2.getNumber() == lastAvailablePerson(people).getNumber())
				{
					Room poppedRoom = stack.pop();
					poppedRoom.printRoom();
					
					while (poppedRoom.getRoommate2().getNumber() == lastAvailablePerson(people).getNumber())
					{
						poppedRoom = stack.pop();
						poppedRoom.getRoommate1().setUsed(false);
						poppedRoom.getRoommate2().setUsed(false);
					}
					createdRooms.remove(poppedRoom);
					people.get(poppedRoom.getRoommate1().getNumber()).setUsed(false);
					people.get(poppedRoom.getRoommate2().getNumber()).setUsed(false);
					System.out.println("Person " + poppedRoom.getRoommate1().getNumber() + " is " + poppedRoom.getRoommate1().getUsed());
					System.out.println("Person " + poppedRoom.getRoommate2().getNumber() + " is " + poppedRoom.getRoommate1().getUsed());
					System.out.println("Person " + roommate1.getNumber() + " is " + roommate1.getUsed());
					System.out.println("Person " + roommate2.getNumber() + " is " + roommate2.getUsed());
					
				}
				else
				{
					createdRooms.remove(newRoom);
					lastUsed += 1;
				}
			}
			
		}	
	}	
	
	public static Person getAvailablePerson(ArrayList<Person> people, int lastUsed)
	{
		
		if (lastUsed == -1 && people.get(0).getUsed() == false)
		{
			people.get(0).setUsed(true);
			return people.get(0);
		}
		else
		{
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


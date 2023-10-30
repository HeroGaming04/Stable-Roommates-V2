import java.util.ArrayList;

public class Person extends Project1{

	protected int[] preferences;
	protected int personNumber;
	protected boolean used;
	
	public Person()
	{
		
	}
	
	//Creates a person with preferences and a number
	public Person(int[] preferences, int personNumber, boolean used) {
		this.preferences = preferences;
		this.personNumber = personNumber;
		this.used = used;
	}
	
	//returns a list of preferences
	public int[] getPreferences() {
		return preferences;
	}
	
	//Returns the person number
	public int getNumber() {
		return personNumber;
	}
	
	//Returns the used variable
	public boolean getUsed()
	{
		return used;
	}
	
	//Sets the used variable
	public void setUsed(boolean used)
	{
		this.used = used;
	}
	
	//Returns spot in preference list
	public int getRank(Person person) {
		int rank = -1;
		
		if (person == null)
		{
			return -1;
		}
		
		for (int i = 0; i < preferences.length; ++i) {
			if (person.getNumber() == preferences[i]) {
				rank = i;
				break;
			}
		}
		return rank;
	}
	
	//Test command used to print the preferences
	protected void printPreferences() {
		for (int i = 0; i < preferences.length; ++i) {
			System.out.print(preferences[i] + " ");
		}
	}
	
	//Test command to print the person number
	protected void printNumber() {
		System.out.print(personNumber);
	}

}

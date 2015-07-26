package org.scalaby.fjava;

import static org.scalaby.fjava.Lenses.lens;

public class Person {

    public static Lens<Person, Address> addressLens = lens(
    		Person::getAddress,            
    		(person, address)  -> 
    			person(person.getEmailAddress(), person.getFirstName(), person.getLastName(), address));

    public static Lens<Person, String> firstNameLens = lens(
    		Person::getFirstName,            
    		(person, firstName)  -> 
    			person(person.getEmailAddress(), firstName, person.getLastName(), person.getAddress()));

	
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final Address address;

    public static Person person(String email, String firstName, String lastName, Address address) {
    	return new Person(email, firstName, lastName, address);
    }
    
    public Person(String email, String firstName, String lastName, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.emailAddress = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmailAddress() {
		return emailAddress;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", address=" + address
				+ "]";
	}
    
}

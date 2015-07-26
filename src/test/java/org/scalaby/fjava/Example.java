package org.scalaby.fjava;
import static org.scalaby.fjava.Company.company;
import static org.scalaby.fjava.Department.department;
import static org.scalaby.fjava.Person.person;

import java.util.function.Function;

public class Example {

    public static void main(String[] args) {
    	
    	Company company = company("hoopinc", 
	    	department("accounts", 
	    			person("bob@hoopinc.com", "Bob", "Smith", new Address("Default", "Default", "Default", 0)),
	    			person("jane@hoopinc.com", "Jane", "Walker", new Address("Default2", "Default2", "Default2", 1))),
	    	department("finance", 
	    			person("kelly@hoopinc.com", "Kelly", "Lee", new Address("Default3", "Default3", "Default3", 2))));

    	Lens<Company, String> janesFirstNameLens = 
    			Company.departmentWithNameLens("accounts")
    				.andThen(Department.employeeWithEmailLens("jane@hoopinc.com")
    				.andThen(Person.firstNameLens));
    	
    	Lens<Company, Integer> kellysZipCodeLens = 
    			Company.departmentWithNameLens("finance")
    				.andThen(Department.employeeWithEmailLens("kelly@hoopinc.com")
    				.andThen(Person.addressLens
    				.andThen(Address.zipCodeLens)));
    	
    	Function<Company, Company> correctKellysZipCode = c -> kellysZipCodeLens.set(c, kellysZipCodeLens.get(c) + 1);
       	Function<Company, Company> correctJanesName= c -> janesFirstNameLens.set(c, "Janet");
           
        System.out.println(correctJanesName.andThen(correctKellysZipCode).apply(company));
    }

}

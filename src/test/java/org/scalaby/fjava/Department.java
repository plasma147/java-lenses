package org.scalaby.fjava;

import static java.util.stream.Collectors.toList;
import static org.scalaby.fjava.Lenses.lens;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Department {

	private static Function<Department, Person> employeeWithEmail(String email) {
		return department -> 
		department.getEmployees().stream().filter(p -> p.getEmailAddress().equals(email)).findFirst().get();
	}
	
	public static Lens<Department, Person> employeeWithEmailLens(String email) {
	    return lens(employeeWithEmail(email),            
	    		(department, person)  -> {
	    			List<Person> employees = department.getEmployees()
	    					.stream().map(p -> 
	    					  p.getEmailAddress().equals(person.getEmailAddress()) ? person : p).collect(toList()); 
	    			return department(department.getName(), employees.toArray(new Person[employees.size()]));
	    		});
	}
	
	private final String name;
	private final List<Person> employees;
	
	public static Department department(String name, Person... employees){
		return new Department(name, Arrays.asList(employees));
	} 
	
	public Department(String name, List<Person> employees) {
		this.name = name;
		this.employees = employees;
	}
	
	public List<Person> getEmployees() {
		return employees;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Department [name=" + name + ", employees=\n\t\t" + String.join("\n\t\t", 
				employees.stream().map(Object::toString).collect(toList())) + "]";
	}
}

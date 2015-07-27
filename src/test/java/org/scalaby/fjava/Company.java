package org.scalaby.fjava;

import static java.util.stream.Collectors.toList;
import static org.scalaby.fjava.Lenses.lens;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Company {

	private static Function<Company, Department> departmentWithName(String name) {
		return company -> company.getDepartments().stream()
						.filter(d -> d.getName().equals(name)).findFirst().get();
	}
	
	public static Lens<Company, Department> departmentWithNameLens(String name) {
		return lens(
				departmentWithName(name),            
				(company, department)  -> {
					List<Department> departments = company.getDepartments()
						.stream().map(d -> 
    					  d.getName().equals(department.getName()) ? department : d).collect(toList()); 
					return company(company.getName(), departments.toArray(new Department[departments.size()]));
				});
	}
	
    private final String name;
    private final List<Department> departments;

    public static Company company(String name, Department... departments){
    	return new Company(name, Arrays.asList(departments));
    }
    
    public Company(String name, List<Department> departments) {
		this.name = name;
		this.departments = departments;
	}

	public List<Department> getDepartments() {
		return departments;
	}
    
    public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Company [name=" + name + ", departments=\n\t" + String.join("\n\t", 
				departments.stream().map(Object::toString).collect(toList())) + "]";
	}

}

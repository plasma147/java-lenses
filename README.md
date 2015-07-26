Implementation of a functional lens in Java, based on this answer [1], given by Daniel Sobral at SO. 

Lense is a convienient way to access and update immutable data structures in a functional world.
Lenses are very useful, when you have a deep hierarchy of objects, and you want to update nested elements in an imperative manner (leaving recreation of the whole object hirarchy behind the scenses). 

Lenses in languages with first-class functions is a quite powerful and elegant tool. In Java, unfortunatelly, you have to use anonymous inner classes to represent function objects, and lenses lose the most of their's glitter.

In some cases, however, lenses can still be useful. E.g., when you want to transform an object, generated by Protobuf builders.

#####################################################

Updated for java 8 and extending the example: 


```java
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

```


1: http://stackoverflow.com/a/5597750/354067
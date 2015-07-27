package org.scalaby.fjava; 

import static org.scalaby.fjava.Lenses.lens;

public class Address {

	public static Lens<Address, Integer> zipCodeLens = lens(
			Address::getZipCode,
            (address, zipcode) -> new Address(address.getStreet(), address.getCity(), address.getState(), zipcode));

    private final String street;
    private final String city;
    private final String state;
    private final Integer zipCode;

    public static Address address(String street, String city, String state, Integer zipCode){
    	return new Address(street, city, state, zipCode);
    }
    
    public Address(String street, String city, String state, Integer zipCode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Integer getZipCode() {
        return zipCode;
    }

	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state="
				+ state + ", zipCode=" + zipCode + "]";
	}

    
}

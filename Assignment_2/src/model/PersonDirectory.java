/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author rahul
 */
public class PersonDirectory {
    private ArrayList<Person> personData;
    
    public PersonDirectory() {
        this.personData = new ArrayList<Person>();
    }
    public ArrayList<Person> getPersonData() {
        return personData;
    }
    public void setPersonData(ArrayList<Person> personData) {
        this.personData = personData;
    }
    public Person addPerson() {
        Person p = new Person();
        personData.add(p);
        return p;
    }
    public void deletePerson(Person person) {
        personData.remove(person);
    }
    public Person searchPerson(String firstName, String lastName, String streetAddress) {
        for (Person ps : personData) {
            if (ps.getFirstName().contains(firstName) || ps.getLastName().contains(lastName) || ps.getHomeAddress().getStreetAddress().contains(streetAddress) ||
                    ps.getWorkAddress().getStreetAddress().contains(streetAddress)) {
                return ps;
            }
        }
        return null;
    }
}

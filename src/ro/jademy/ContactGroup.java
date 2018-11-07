package ro.jademy;

import java.util.*;

public class ContactGroup {
    private Set<Contact> contacts = new TreeSet<>();

    public ContactGroup() {

    }

    public ContactGroup(Contact... contacts) {
        this(Arrays.asList(contacts));
    }


    public ContactGroup(List<Contact> contactList) {
        contacts.addAll(contactList);
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void showContacts() {

        contacts.stream()
                .sorted()
                .forEach(t -> t.showContactDetails());
    }

    public void checkElementExistence(String lastName) {
        contacts.removeIf(c -> c.getLastName().toLowerCase().equals(lastName));
    }

    public void searchContact(String lastName){
        contacts.stream()
                .filter(t-> t.getLastName().toLowerCase().trim().equals(lastName.toLowerCase().trim()))
                .forEach(Contact::showContactDetails);
    }
}

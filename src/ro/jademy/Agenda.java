package ro.jademy;

import java.util.*;
import java.util.stream.Collectors;

public class Agenda {
    private Map<String, ContactGroup> contactMap;
    private Scanner scanner = new Scanner(System.in);

    public Agenda() {
        contactMap = new TreeMap<>();
    }

    public Map<String, ContactGroup> getContactMap() {
        return contactMap;
    }


    public void addContact(String firstName, String lastName, String phoneNumber, String email) {
        addContact(new Contact(firstName, lastName, phoneNumber, email));
    }


    public void addContact(Contact contact) {
        String key = contact.getLastName().substring(0, 1).toUpperCase();
        ContactGroup group = contactMap.getOrDefault(key, new ContactGroup());

        group.addContact(contact);
        contactMap.put(key, group);
    }


    /**
     * it shows the list of contacts
     */
    public void showContacts() {
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Last Name", "First Name", "Phone Number", "Email Address");
        System.out.println("------------------------------------------------------------------");
        Set<Map.Entry<String, ContactGroup>> contacts = contactMap.entrySet();
        contacts.stream()
                .forEach((t) -> t.getValue().showContacts());
        System.out.println("------------------------------------------------------------------");
    }

    public void removeContact(String phoneNumber) {

        Optional<Contact> contactOpt = searchByPhoneNumber(phoneNumber);

        if (contactOpt.isPresent()) {
            Contact contact = contactOpt.get();
            ContactGroup group = contactMap.get(getLastNameFirstLetter(contact.getLastName()));
            group.getContacts().remove(contact);
        }
    }

    public Optional<Contact> searchByPhoneNumber(String phoneNumber) {

        return contactMap.values()
                .stream()
                .map(ContactGroup::getContacts)
                .flatMap(Set::stream)
                .filter(c -> c.getPhoneNumber().equals(phoneNumber))
                .findFirst();
    }

    public List<Contact> searchByLastName(String lastName) {
        List<Contact> foundContacts = new ArrayList<>();

        for (ContactGroup group : contactMap.values()) {
            foundContacts.addAll(group.getContacts()
                    .stream()
                    .filter(c -> c.getLastName().equals(lastName))
                    .collect(Collectors.toList()));
        }

        return foundContacts;
    }


    public void searchForContact(String lastName) {
        String key = getLastNameFirstLetter(lastName);
        ContactGroup contactGroup = contactMap.get(key);
        if (contactGroup != null) {
            System.out.printf("%-15s%-15s%-15s%-15s\n", "Last Name", "First Name", "Phone Number", "Email Address");
            System.out.println("------------------------------------------------------------------");
            contactGroup.searchContact(lastName);
            System.out.println("------------------------------------------------------------------");
        } else {
            System.out.println("There is no such contact in the agenda");
        }
    }

    public String getLastNameFirstLetter(String lastName) {
        return lastName.substring(0, 1).toUpperCase();
    }

    public void editContact() {
        List<Contact> listOfAllContacts = new ArrayList<>();
        for (ContactGroup cg : contactMap.values()) {
            listOfAllContacts.addAll(cg.getContacts());
        }
        System.out.println("Select contact you want to edit");
        int option = scanner.nextInt();
        Contact contactToBeEdited = listOfAllContacts.get(option - 1);

        System.out.println("Last name?");
        String lastName = scanner.next();

        System.out.println("First name?");
        String firstName = scanner.next();

        System.out.println("Phone number");
        String phoneNumber = scanner.next();

        System.out.println("Email address?");
        String emailAddress = scanner.next();

        String result = contactToBeEdited.getLastName().substring(0, 1).toUpperCase();
        ContactGroup contactGroup = contactMap.get(result);


        for (Contact contact : contactGroup.getContacts()) {
            if (contact.equals(contactToBeEdited)) {
                contact.setLastName(lastName);
                contact.setFirstName(firstName);
                contact.setPhoneNumber(phoneNumber);
                contact.setEmailAddress(emailAddress);
            }
        }
    }
}

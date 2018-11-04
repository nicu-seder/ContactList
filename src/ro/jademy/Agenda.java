package ro.jademy;

import java.util.*;

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
        Set<Map.Entry<String, ContactGroup>> contacts = contactMap.entrySet();
        contacts.stream()
                .forEach((t) -> t.getValue().showContacts());
    }

    public void removeContacts(){
        System.out.println("Introduce last name");
        String lastName = scanner.next().toLowerCase();

        List<ContactGroup> contactGroups = new ArrayList<>(contactMap.values());
        for(ContactGroup cg:contactGroups){
            cg.checkElementExistance(lastName);
        }
    }

    public void searchContact(String lastName){
        for(ContactGroup cg:contactMap.values()){
            cg.searchContact(lastName);
        }
    }
}

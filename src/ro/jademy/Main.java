package ro.jademy;

import ro.jademy.exceptions.EmailAddressException;

import java.util.Scanner;
import java.util.Set;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Agenda agenda = new Agenda();

    public static void main(String[] args) {
        agenda.addContact("Nicu", "Seder", "0755806192", "nicu.seder@gmail.com");
        agenda.addContact("Patricia", "Seder", "0744987134", "patricia.seder@gmail.com");
        agenda.addContact("Vasile", "Vasilescu", "07346987133", "vasile.vasilescu@gmail.com");
        agenda.addContact("Alin", "Aioanei", "0725806192", "alin.aioanei@gmail.com");
        agenda.addContact("Bogdan", "Bica", "0753806192", "bogdan.bica@gmail.com");

        openAgenda();

    }

    private static void openAgenda() {
        System.out.println("------------PHONE AGENDA------------");
        System.out.println("1. List contacts");
        System.out.println("2. Add contact");
        System.out.println("3. Search contact");
        System.out.println("4. Edit contact");
        System.out.println("5. Delete contact");
        System.out.println("------------------------------------");
        checkAgendaChosenOption();
    }

    private static void checkAgendaChosenOption() {
        switch (scanner.nextInt()) {
            case 1:
                agenda.showContacts();
                openAgenda();
                break;
            case 2:
                try {
                    agenda.addContact(askContactDetails());
                } catch (EmailAddressException ex) {
                    ex.exceptionMessage();
                }
                openAgenda();
                break;
            case 3:
                searchContacts();
                openAgenda();
                break;
            default:
                System.out.println("Bye bye");
                break;
        }
    }

    private static Contact askContactDetails() throws EmailAddressException {
        System.out.println("Insert First Name");
        String firstName = scanner.next();

        System.out.println("Insert Last Name");
        String lastName = scanner.next();

        System.out.println("Insert Phone Number");
        String phoneNumber = scanner.next();

        System.out.println("Insert Email Address");
        String emailAddress = scanner.next();

        if (!emailAddress.contains("@")) {
            throw new EmailAddressException();
        }
        return new Contact(firstName, lastName, phoneNumber, emailAddress);
    }

    private static void askContactToEdit(){
        System.out.println("Please select which contact to edit");
        agenda.showContacts();

    }

    private static void searchContacts(){
        System.out.println("Insert contact's last name");
        String lastName = scanner.next();
        agenda.searchContact(lastName);
    }


}

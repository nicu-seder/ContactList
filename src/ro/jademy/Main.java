package ro.jademy;

import ro.jademy.exceptions.EmailAddressException;
import ro.jademy.exceptions.NameException;
import ro.jademy.exceptions.PhoneNumberException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Agenda agenda = new Agenda();
    private static final String FIRST_NAME = "First_Name";
    private static final String LAST_NAME = "Last_Name";
    private static final String PHONE_NUMBER = "Phone_Number";
    private static final String EMAIL_ADDRESS = "Email_Address";

    public static void main(String[] args) {
        /*agenda.addContact("Nicu", "Seder", "0755806192", "nicu.seder@gmail.com");
        agenda.addContact("Patricia", "Seder", "0744987134", "patricia.seder@gmail.com");
        agenda.addContact("Vasile", "Vasilescu", "0734698713", "vasile.vasilescu@gmail.com");
        agenda.addContact("Alin", "Aioanei", "0725806192", "alin.aioanei@gmail.com");
        agenda.addContact("Bogdan", "Bica", "0753806192", "bogdan.bica@gmail.com");*/

        Map<String, Integer> table = new TreeMap<>();

        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("contacts2.csv"));
            try {
                boolean isFirstLine = true;
                while (bufferedReader.ready()) {
                    String line = bufferedReader.readLine();
                    String[] lineArr = line.split(",");
                    if(isFirstLine){
                        // first line, we map the columns to an index
                        for(int i = 0;i < lineArr.length;i++) {
                            switch (lineArr[i]){
                                case FIRST_NAME:table.put(FIRST_NAME,i);break;
                                case LAST_NAME:table.put(LAST_NAME,i);break;
                                case PHONE_NUMBER:table.put(PHONE_NUMBER,i);break;
                                case EMAIL_ADDRESS:table.put(EMAIL_ADDRESS,i);break;
                                default:
                                    System.out.println("There is no such column");
                                    break;
                            }
                        }
                        isFirstLine = false;
                        continue;
                    }
                    agenda.addContact(lineArr[table.get(FIRST_NAME)],lineArr[table.get(LAST_NAME)],lineArr[table.get(PHONE_NUMBER)],lineArr[table.get(EMAIL_ADDRESS)]);
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println();
        }

        openAgenda();
    }

    private static void openAgenda() {
        System.out.println("------------PHONE AGENDA------------");
        System.out.println("1. List contacts");
        System.out.println("2. Add contact");
        System.out.println("3. Search contact");
        System.out.println("4. Edit contact");
        System.out.println("5. Delete contact");
        System.out.println("6. Exit");
        System.out.println("------------------------------------");

        int opt = scanner.nextInt();
        if (opt < 1 || opt > 6) {
            System.out.println("Insert option betwen 1-6");
            openAgenda();
        } else {
            checkAgendaChosenOption(opt);
        }
        openAgenda();
    }

    private static void checkAgendaChosenOption(int opt) {
        switch (opt) {
            case 1:
                agenda.showContacts();
                openAgenda();
                break;
            case 2:
                try {
                    agenda.addContact(askContactDetails());
                } catch (EmailAddressException | PhoneNumberException | NameException ex) {
                    System.out.println(ex.getMessage());
                    checkAgendaChosenOption(opt);
                }
                openAgenda();
                break;
            case 3:
                try {
                    searchContacts();
                } catch (NameException ex) {
                    System.out.println(ex.getMessage());
                    checkAgendaChosenOption(opt);
                }

                openAgenda();
                break;
            case 4:
                agenda.editContact();
                openAgenda();
                break;
            case 5:

                System.out.println("Enter phone number");
                String phoneNumber = scanner.next().toLowerCase();

                agenda.removeContact(phoneNumber);
                openAgenda();
                break;
            case 6:
                System.out.println("Agenda is closing...");
                break;
            default:
                System.out.println("Bye bye");
                break;
        }
    }

    private static Contact askContactDetails() throws EmailAddressException, PhoneNumberException, NameException {
        System.out.println("Insert First Name");
        String firstName = scanner.next();
        if (!checkNameValidity(firstName)) {
            throw new NameException("Invalid name");
        }

        System.out.println("Insert Last Name");
        String lastName = scanner.next();
        if (!checkNameValidity(lastName)) {
            throw new NameException("Invalid name");
        }

        System.out.println("Insert Phone Number");
        String phoneNumber = scanner.next();
        if (!checkPhoneNumberValidity(phoneNumber)) {
            throw new PhoneNumberException("Phone number should follow the next format: 07XX XXX XXX\n where X should be a number betwen 0-9");
        }


        System.out.println("Insert Email Address");
        String emailAddress = scanner.next();
        if (!emailAddress.contains("@")) {
            throw new EmailAddressException("Email address is invalid");
        }

        return new Contact(firstName, lastName, phoneNumber, emailAddress);
    }

    private static void editContacts() {
        System.out.println("Please select which contact to edit");
        agenda.showContacts();

    }

    private static void searchContacts() throws NameException {
        System.out.println("Insert contact's last name");
        String lastName = scanner.next();
        if (!checkNameValidity(lastName)) {
            throw new NameException("Please insert a valid name");
        } else {
            agenda.searchForContact(lastName);
        }

    }

    private static boolean checkPhoneNumberValidity(String phoneNumber) {
        int noOfcharacters = phoneNumber.length();
        String mandatoryCharacters = phoneNumber.substring(0, 2);
        boolean allNumbers = true;
        char[] phoneNumberCharacters = phoneNumber.toCharArray();
        for (int i = 0; i < phoneNumberCharacters.length; i++) {
            if (phoneNumberCharacters[i] < 48 || phoneNumberCharacters[i] > 57) {
                allNumbers = false;
            }
        }
        if (allNumbers && noOfcharacters == 10 && mandatoryCharacters.equals("07")) {
            return true;
        }
        return false;
    }

    private static boolean checkNameValidity(String name) {
        char[] nameCharacters = name.toCharArray();
        boolean result = true;
        for (Character ch : nameCharacters) {
            if (ch < 65 || ch > 122) {
                result = false;
            }
        }
        return result;
    }

}

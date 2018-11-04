package ro.jademy;

import java.util.Objects;

public class Contact implements Comparable<Contact> {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String firstName, String lastName, String phoneNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(firstName, contact.firstName) &&
                Objects.equals(lastName, contact.lastName) &&
                Objects.equals(phoneNumber, contact.phoneNumber) &&
                Objects.equals(emailAddress, contact.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, phoneNumber, emailAddress);
    }

    @Override
    public int compareTo(Contact o) {
        int compareLastNameResult = lastName.compareTo(o.lastName);
        int compareFirstNameResult = firstName.compareTo(o.firstName);
        int comparePhoneNumberResult = phoneNumber.compareTo(o.phoneNumber);
        int compareEmailAddressResult = emailAddress.compareTo(o.emailAddress);
        if (compareLastNameResult != 0) {
            return compareLastNameResult;
        } else if (compareFirstNameResult != 0) {
            return compareFirstNameResult;
        } else if (comparePhoneNumberResult != 0) {
            return comparePhoneNumberResult;
        } else if (compareEmailAddressResult != 0) {
            return compareEmailAddressResult;
        }
        return 0;
    }

    public void showContactDetails(){
        System.out.println(firstName + " " + lastName + "(" + phoneNumber + ")" +
                ", " + emailAddress);
    }
}

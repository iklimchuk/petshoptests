package framework.entities;

import framework.pages.FindOwnersPage;
import framework.pages.OwnerDetailsPage;
import framework.pages.OwnerInfoPage;

import java.util.ArrayList;
import java.util.List;

import static test.BaseTest.TEST_ID;

public class Owner {
    private static int ownersCounter = 1;
    private final String firstName;
    private final String city;
    private final String address;
    private final String phoneNumber;
    private final FindOwnersPage findOwnersPage = new FindOwnersPage();
    private final OwnerDetailsPage ownerDetailsPage = new OwnerDetailsPage();
    private final OwnerInfoPage ownerInfoPage = new OwnerInfoPage();
    private String lastName;
    private List<Pet> petsList = new ArrayList<>();

    public Owner() {
        firstName = "owner";
        lastName = TEST_ID + "_" + ownersCounter++;
        city = "Krakow";
        address = "dluga 1/3";
        phoneNumber = "123456789";
    }

    public Owner(String lastName) {
        firstName = "owner";
        this.lastName = lastName;
        city = "Krakow";
        address = "dluga 1/3";
        phoneNumber = "123456789";
    }

    void addPet(Pet pet) {
        find();
        ownerInfoPage.addNewPet();
        petsList.add(pet);
    }

    void find() {
        findOwnersPage.search(lastName);
        if (findOwnersPage.searchResultsTableIsDisplayed() && findOwnersPage.getOwnersCount() >= 2) {
            findOwnersPage.showOwnerDetails(firstName, lastName);
        }
    }

    void addVisitForPet(String petName) {
        ownerDetailsPage.addVisitForPet(petName);
    }

    public void changeLastName(String newLastName) {
        edit();
        newLastName = newLastName + TEST_ID;
        ownerDetailsPage.setLastName(newLastName);
        ownerDetailsPage.save();
        lastName = newLastName;
    }

    private void edit() {
        find();
        ownerInfoPage.editOwner();
    }

    public void create() {
        findOwnersPage.open();
        findOwnersPage.newOwner();
        fillDetails();
        ownerDetailsPage.save();
    }

    private void fillDetails() {
        ownerDetailsPage.setFirstName(firstName);
        ownerDetailsPage.setLastName(lastName);
        ownerDetailsPage.setAddress(address);
        ownerDetailsPage.setCity(city);
        ownerDetailsPage.setPhoneNumber(phoneNumber);
    }

    void editPet(Pet pet) {
        find();
        ownerInfoPage.editPet(pet.name());
    }

    public boolean exists() {
        findOwnersPage.search(lastName);
        if (ownerInfoPage.isDisplayed()) {
            return ownerInfoPage.ownerName().equals(firstName + " " + lastName);
        } else
            return findOwnersPage.searchResultsTableIsDisplayed() && findOwnersPage.ownerPresentInTheList(firstName, lastName);
    }

    public String lastName() {
        return lastName;
    }

    public boolean petsPresentInTheList() {
        find();
        boolean allPetsPresentInTheList = true;
        for (Pet pet : petsList) {
            if (!ownerDetailsPage.petIsPresentInTheList(pet)) {
                allPetsPresentInTheList = false;
                break;
            }
        }
        return allPetsPresentInTheList;
    }

    void updatePetName(Pet pet, String petName) {
        int petNumber = petsList.indexOf(pet);
        petsList.get(petNumber).setName(petName);
    }
}

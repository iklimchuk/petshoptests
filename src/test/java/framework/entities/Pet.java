package framework.entities;

import framework.enums.PetType;
import framework.pages.OwnerDetailsPage;
import framework.pages.PetDetailsPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static test.BaseTest.TEST_ID;

public class Pet {
    private static int petsCount = 1;
    private List<Visit> visitList = new ArrayList<>();
    private String name;
    private String birthDate;
    private PetType petType;
    private Owner owner;
    private PetDetailsPage petDetailsPage = new PetDetailsPage();

    public Pet(Owner owner) {
        name = String.format("petName_%s_%d", TEST_ID, petsCount++);
        birthDate = "2017/06/14";
        petType = getRandomType();
        this.owner = owner;
    }

    private PetType getRandomType() {
        int petTypesNum = PetType.values().length;
        int randomTypeId = new Random().nextInt(petTypesNum);
        return PetType.values()[randomTypeId];
    }

    void addVisit(Visit visit) {
        owner.find();
        owner.addVisitForPet(name);
        visitList.add(visit);
    }

    void setName(String name) {
        this.name = name;
    }

    public void changeName(String customPetName) {
        owner.editPet(this);
        petDetailsPage.setName(customPetName);
        petDetailsPage.save();
        owner.updatePetName(this, customPetName);
    }

    public boolean visitsPresentInTheList() {
        owner.find();
        boolean allVisitsPresentInTheList = true;
        for (Visit visit : visitList) {
            OwnerDetailsPage ownerDetailsPage = new OwnerDetailsPage();
            if (!ownerDetailsPage.findVisitInTheList(visit, this)) {
                allVisitsPresentInTheList = false;
            }
        }
        return allVisitsPresentInTheList;
    }

    public void create() {
        owner.addPet(this);
        fillDetails();
        petDetailsPage.save();
    }

    private void fillDetails() {
        petDetailsPage.setName(name);
        petDetailsPage.setBirthDate(birthDate);
        petDetailsPage.setType(petType);
    }

    public String name() {
        return name;
    }
}

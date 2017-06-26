package test;

import framework.entities.Owner;
import framework.entities.Pet;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PetTest extends BaseTest {

    @Test
    public void createSinglePetForOneOwner(){
        Owner owner = new Owner();
        owner.create();
        Pet pet = new Pet(owner);
        pet.create();
        Assert.assertTrue(owner.petsPresentInTheList());
    }

    @Test
    public void createMultiplePetsForOneOwner(){
        Owner owner = new Owner();
        owner.create();
        Pet pet1 = new Pet(owner);
        Pet pet2 = new Pet(owner);
        Pet pet3 = new Pet(owner);
        Pet pet4 = new Pet(owner);
        pet1.create();
        pet2.create();
        pet3.create();
        pet4.create();
        Assert.assertTrue(owner.petsPresentInTheList());
    }

    @Test
    public void editPet(){
        Owner owner = new Owner();
        owner.create();
        Pet pet = new Pet(owner);
        pet.create();
        pet.changeName("customPetName");
        Assert.assertTrue(owner.petsPresentInTheList());

    }
}

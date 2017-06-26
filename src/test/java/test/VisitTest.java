package test;

import framework.entities.Owner;
import framework.entities.Pet;
import framework.entities.Visit;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VisitTest extends BaseTest {
    @Test
    public void createSingleVisit(){
        Owner owner = new Owner();
        owner.create();
        Pet pet = new Pet(owner);
        pet.create();
        Visit visit = new Visit(pet);
        visit.create();
        Assert.assertTrue(pet.visitsPresentInTheList());
    }

    @Test
    public void createMultipleVisits(){
        Owner owner = new Owner();
        owner.create();
        Pet pet = new Pet(owner);
        pet.create();
        Visit visit1 = new Visit(pet);
        Visit visit2 = new Visit(pet);
        Visit visit3 = new Visit(pet);
        visit1.create();
        visit2.create();
        visit3.create();
        Assert.assertTrue(pet.visitsPresentInTheList());

    }
}

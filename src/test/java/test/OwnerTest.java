package test;

import framework.entities.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OwnerTest extends BaseTest {
    @Test
    public void createOwnerTest(){
        Owner owner = new Owner();
        owner.create();
        Assert.assertTrue(owner.exists());
    }

    @Test()
    public void editOwnerLastName(){
        Owner owner = new Owner();
        owner.create();
        owner.changeLastName("TestLastName");
        Assert.assertTrue(owner.exists());
    }
}

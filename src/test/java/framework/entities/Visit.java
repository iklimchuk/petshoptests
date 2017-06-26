package framework.entities;

import framework.pages.VisitDetailsPage;

public class Visit {
    private static int visitCounter = 1;
    private Pet pet;
    private String date;
    private String description;
    private VisitDetailsPage visitDetailsPage = new VisitDetailsPage();

    public Visit(Pet pet) {
        this.pet = pet;
        date = "2017/07/25";
        description = "visit" + visitCounter++;
    }

    public void create() {
        pet.addVisit(this);
        fillDetails();
        visitDetailsPage.save();

    }

    private void fillDetails() {
        visitDetailsPage.setDate(date);
        visitDetailsPage.setDescription(description);
    }

    public String description() {
        return description;
    }
}

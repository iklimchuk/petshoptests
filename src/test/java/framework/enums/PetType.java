package framework.enums;

public enum PetType {
    BIRD("bird"),
    CAT("cat"),
    DOG("dog"),
    HAMSTER("hamster"),
    LIZARD("lizard"),
    SNAKE("snake");
    final String petTypeName;

    PetType(String type) {
        petTypeName = type;
    }

    public String petTypeName(){
        return petTypeName;
    }
}

package framework.entities;

public class Veterinarian {
    private final String firstName;
    private final String lastName;
    private final String specialties;
    public Veterinarian(String firstName, String lastName, String specialties) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialties = specialties;
    }

    @Override
    public boolean equals(Object vet) {
        if (this == vet) return true;
        if (vet == null || getClass() != vet.getClass()) return false;
        Veterinarian that = (Veterinarian) vet;
        if (!firstName.equals(that.firstName)) return false;
        if (!lastName.equals(that.lastName)) return false;
        return specialties.equals(that.specialties);
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + specialties.hashCode();
        return result;
    }
}

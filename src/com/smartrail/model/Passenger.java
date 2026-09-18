package com.smartrail.model;

public class Passenger extends User {
    private String passengerId;
    private int age;
    private String gender;

    public Passenger(String passengerId, String name, int age, String gender, String phone, String email) {
        super(name, phone, email);
        this.passengerId = passengerId;
        this.age = age;
        this.gender = gender;
    }

    public String getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getRole() {
        return "Passenger";
    }

    // Convert to CSV string for persistence
    public String toCsv() {
        return passengerId + "," + name + "," + age + "," + gender + "," + phone + "," + email;
    }

    // Parse from CSV string
    public static Passenger fromCsv(String csv) {
        String[] parts = csv.split(",");
        if (parts.length >= 6) {
            return new Passenger(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4], parts[5]);
        }
        return null;
    }

    @Override
    public String toString() {
        return "ID: " + passengerId + ", Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Phone: " + phone + ", Email: " + email;
    }
}

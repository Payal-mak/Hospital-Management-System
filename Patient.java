public class Patient {
    private int patientId;
    private String name;
    private String gender;
    private int age;
    private String address;

    public Patient(int patientId, String name, String gender, int age, String address) {
        this.patientId = patientId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId + "\nName: " + name + "\nGender: " + gender + "\nAge: " + age + "\nAddress: " + address;
    }
}
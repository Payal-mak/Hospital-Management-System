import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HospitalManagementSystem extends JFrame {
    private List<Patient> patients = new ArrayList<>();
    private List<Doctor> doctors = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();
    
    private JComboBox<Patient> patientComboBox;
    private JComboBox<Doctor> doctorComboBox;
    private JTextArea appointmentTextArea;
    private JTextField dateField;

    public HospitalManagementSystem() {
        setTitle("Hospital Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize data
        initializeData();

        // Create components
        patientComboBox = new JComboBox<>(patients.toArray(new Patient[0]));
        doctorComboBox = new JComboBox<>(doctors.toArray(new Doctor[0]));
        appointmentTextArea = new JTextArea();
        appointmentTextArea.setEditable(false);
        dateField = new JTextField(10);

        JButton bookButton = new JButton("Book Appointment");
        bookButton.addActionListener(new BookButtonListener());

        // Create panels
        JPanel topPanel = new JPanel(new GridLayout(3, 2));
        topPanel.add(new JLabel("Select Patient:"));
        topPanel.add(patientComboBox);
        topPanel.add(new JLabel("Select Doctor:"));
        topPanel.add(doctorComboBox);
        topPanel.add(new JLabel("Appointment Date (dd-mm-yyyy):"));
        topPanel.add(dateField);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(bookButton);

        // Add components to frame
        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(appointmentTextArea), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Display frame
        setVisible(true);
    }

    private void initializeData() {
        // Initialize patients
        patients.add(new Patient("1", "John Doe", 30));
        patients.add(new Patient("2", "Jane Smith", 25));
        patients.add(new Patient("3", "Jim Brown", 40));

        // Initialize doctors
        doctors.add(new Doctor("1", "Dr. Alice Johnson", "Cardiology"));
        doctors.add(new Doctor("2", "Dr. Bob Williams", "Neurology"));
        doctors.add(new Doctor("3", "Dr. Charlie Davis", "Orthopedics"));
    }

    private class BookButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Patient selectedPatient = (Patient) patientComboBox.getSelectedItem();
            Doctor selectedDoctor = (Doctor) doctorComboBox.getSelectedItem();
            String dateString = dateField.getText();

            try {
                Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dateString);

                if (selectedPatient != null && selectedDoctor != null) {
                    Appointment appointment = new Appointment(selectedPatient, selectedDoctor, date);
                    appointments.add(appointment);
                    appointmentTextArea.append(appointment + "\n");
                } else {
                    JOptionPane.showMessageDialog(HospitalManagementSystem.this, "Please select both a patient and a doctor.");
                }
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(HospitalManagementSystem.this, "Invalid date format. Please enter the date in dd-mm-yyyy format.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HospitalManagementSystem());
    }
}

class Patient {
    private String id;
    private String name;
    private int age;

    public Patient(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Doctor {
    private String id;
    private String name;
    private String specialty;

    public Doctor(String id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Appointment {
    private Patient patient;
    private Doctor doctor;
    private Date date;

    public Appointment(Patient patient, Doctor doctor, Date date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Appointment: " + patient + " with " + doctor + " on " + new SimpleDateFormat("dd-MM-yyyy").format(date);
    }
}

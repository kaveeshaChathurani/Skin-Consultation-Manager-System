import java.util.ArrayList;

public interface SkinConsultationManager {

    public  ArrayList<Doctor> add(ArrayList<Doctor> doctors);
    public  ArrayList<Doctor> delete(ArrayList<Doctor> doctors);
    public  ArrayList<Doctor> print(ArrayList<Doctor> doctors);
    public  ArrayList<Doctor> save(ArrayList<Doctor> doctors);

    public  void viewGUI(ArrayList<Doctor> doctors, ArrayList<Consultation> consultations);
    public void viewChannel(ArrayList<Doctor> doctors, ArrayList<Consultation> consultations);
    public  void bookDoctor(ArrayList<Doctor> doctors, ArrayList<Consultation> consultations);
    public  void viewDoctor(ArrayList<Doctor> doctors);
}

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    @Override
    public ArrayList<Doctor> add(ArrayList<Doctor> doctors) {
        //Import scanner input to get user input
        try {
            Scanner input = new Scanner(System.in);
            for (int i = 0; i < 10; i++) {


                System.out.println("Insert the doctor's information in the programme..");
                System.out.print("Insert the Doctor's First Name :");
                String firstName = input.nextLine();

                System.out.print("Insert the Doctor's Last Name:");
                String lastName = input.nextLine();

                System.out.print("Insert the Doctor's Contact Number :");
                String mobileNumber = input.nextLine();

                System.out.print("Insert the Doctor's Medical licence Number :");
                String medLicenceNumber = input.nextLine();

                System.out.print("Insert the Doctor's Specialisation :");
                String specialisation = input.nextLine();

                Doctor doctor = new Doctor();
                doctor.setFirstName(firstName);
                doctor.setLastName(lastName);
                doctor.setMobileNumber(Integer.parseInt(mobileNumber));
                doctor.setMedLicenceNum(medLicenceNumber);
                doctor.setSpecialisation(specialisation);
                doctors.add(doctor);

                System.out.println(doctor);

                System.out.print("Choose 'Y' to exit the updating doctor's details; otherwise, Insert 'N' :");
                String yesNo = input.nextLine();
                if (yesNo.equals("Y")) {
                    break;
                }
                else {
                    System.out.println("Invalid Input.Please insert valid letter.");
                }
                System.out.println("");
            }

        }catch (Exception e){
            System.out.println("Invalid Input");
        }
        return doctors;
    }

    @Override
    public ArrayList<Doctor> delete(ArrayList<Doctor> doctors) {
        //Import scanner input to get user input
        try {
            Scanner input = new Scanner(System.in);
            System.out.println("Do you desire the doctor's information removed from the system?");
            System.out.println("Insert the medical license number of the doctor :");
            String deleteLicenceNum = input.nextLine();

            Doctor dctr = null;
            for (Doctor d : doctors) {
                if (d.getMedLicenceNum().equals(deleteLicenceNum)) {
                    dctr = d;
                    break;
                }
            }
            if (dctr== null) {
                System.out.println("No doctors with the required medical license number could be located :( ");
            } else {
                doctors.remove(dctr);
                System.out.println("The doctor Dr. " + dctr.getFirstName() + " " + dctr.getLastName() + " with named and medical license number " + dctr.getMedLicenceNum() + " has been removed.");
                System.out.println("Total Number of doctors remaining : " + doctors.size() );

            }

        }catch(Exception e){

            System.out.println("Invalid input..");
        }

        return doctors;
    }

    @Override
    public ArrayList<Doctor> print(ArrayList<Doctor> doctors) {
        try {

            doctors.sort(new Comparator<Doctor>() {
                @Override
                public int compare(Doctor doc_1, Doctor doc_2) {
                    return doc_1.getLastName().compareTo(doc_2.getLastName());
                }
            });
            for (Doctor doctor : doctors) {
                System.out.println("Doctor's Name : " + doctor.getFirstName() + " " + doctor.getLastName());
                System.out.println("Medical licence number : " + doctor.getMedLicenceNum());
                System.out.println("Specialisation : " + doctor.getSpecialisation());
                System.out.println();

            }
        }catch (Exception e){
            System.out.println("Invalid Input..");
        }
        return doctors;
    }

    @Override
    public ArrayList<Doctor> save(ArrayList<Doctor> doctors) {
        try {
            File docInforFile = new File("F:DoctorInformation.txt");
            FileWriter display = new FileWriter(docInforFile);
            if(docInforFile.exists()){
                for (Doctor doc : doctors){
                    display.write("Doctor Name: "+doc.getFirstName());
                    display.write("\n");
                    display.write("\n");
                    display.write("Doctor's Last Name: " + doc.getLastName());
                    display.write("\n");
                    display.write("\n");
                    display.write("Doctor's Contact Number: " + doc.getMobileNumber());
                    display.write("\n");
                    display.write("\n");
                    display.write("Doctor,s Medical Licence Number: " + doc.getMedLicenceNum());
                    display.write("\n");
                    display.write("\n");
                    display.write("Doctor'sSpecialisation: " + doc.getSpecialisation());
                    display.write("\n");
                    display.write("\n");
                }
            }else {
                System.out.println("The file does not exist...!");
                return doctors;
            }
            System.out.println("Save Successfully ....");
            display.close();

        }catch (Exception e){

            System.out.println("Invalid Input");
        }

        return doctors;
    }


    //Implement add method

}
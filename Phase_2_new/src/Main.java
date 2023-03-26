import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        try {

            //Import Scanner input to get user input
            Scanner input = new Scanner(System.in);
            //Create an ArrayList object for Doctor
            ArrayList<Doctor> doctors = new ArrayList<>();
            //Create an ArrayList object for Consultation
            ArrayList<Consultation> consultations = new ArrayList<>();


            WestminsterSkinConsultationManager westminsterSkinConsultationManager = new WestminsterSkinConsultationManager();
            //Use a for loop for to select an option from the menu
            for (int i = 0; i < 100; i++) {
                System.out.println("...Program Menu...");
                System.out.println();
                System.out.println("1 : Add a new doctor's details for program.");
                System.out.println("2 : Delete a doctor's details from program.");
                System.out.println("3 : Print the list of the doctors' details.");
                System.out.println("4 : Save doctors' details in a file.");
                System.out.println("5 : Open GUI part from program.");
                System.out.println();
                System.out.print("Enter the desired number of Option:");
                String select = input.nextLine();

                if (select.equals("1")) {
                    doctors = westminsterSkinConsultationManager.add(doctors);      //Call add method
                }
                if (select.equals("2")) {
                    doctors = westminsterSkinConsultationManager.delete(doctors);   //Call delete method
                }

                if (select.equals("3")) {
                    doctors = westminsterSkinConsultationManager.print(doctors);    //Call print method
                }

                if (select.equals("4")) {
                    doctors = westminsterSkinConsultationManager.save(doctors);     //Call save method
                }

                if (select.equals("5")) {
                    // GUI(doctors,consultations);   //Call GUI method
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid data");
        }


    }
}

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

                // Create Doctor Object
                Doctor doctor = new Doctor();

                //Get user inputs here
                doctor.setFirstName(firstName);
                doctor.setLastName(lastName);
                doctor.setMobileNumber(Integer.parseInt(mobileNumber));
                doctor.setMedLicenceNum(medLicenceNumber);
                doctor.setSpecialisation(specialisation);
                doctors.add(doctor);

               //Continue or exit add method
                System.out.print("Choose 'Y' to exit the updating doctor's details; otherwise, Insert 'N' :");
                String yesNo = input.nextLine();
                if (yesNo.equals("Y")) {
                    break;
                }
            }
        }catch (Exception e){
            System.out.println("Invalid Input...  ");
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

            //Identify the medical license number of the doctor
            Doctor doctor = null;
            for (Doctor d : doctors) {
                if (d.getMedLicenceNum().equals(deleteLicenceNum)) {
                    doctor = d;
                    break;
                }
            }
            //Can't find the medical license number of the doctor
            if (doctor== null) {
                System.out.println("No doctors with the required medical license number could be located :( ");
            } else {
                doctors.remove(doctor);
                System.out.println("The doctor Dr. " + doctor.getFirstName() + " " + doctor.getLastName() + " with named and medical license number " + doctor.getMedLicenceNum() + " has been removed.");
                System.out.println("Total Number of doctors remaining : " + doctors.size() );

            }

        }catch (Exception e){
            System.out.println("Invalid Input.Insert valid input..");
        }

        return doctors;
    }

    @Override
    public ArrayList<Doctor> print(ArrayList<Doctor> doctors) {
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
        return doctors;
    }

    @Override
    public ArrayList<Doctor> save(ArrayList<Doctor> doctors) {
        try{
            //Path of saving file
            File docInforFile = new File("F:DoctorInformation.txt");
            FileWriter display = new FileWriter(docInforFile);
            //Check the save file
            if(docInforFile.exists()){
                for (Doctor doc : doctors){
                    display.write("Doctor's First Name: " + doc.getFirstName());
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
                    display.write("Doctor's Specialisation: " + doc.getSpecialisation());
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
            System.out.println("Invalid Input...");
        }
        return doctors;

    }

    @Override
    public void viewGUI(ArrayList<Doctor> doctors, ArrayList<Consultation> consultations) {



        // Create 'Details of Doctors' Button
        JFrame aDct_jfm = new JFrame();
        JPanel jpnl = new JPanel();
        aDct_jfm.getContentPane();
        JButton doc_b = new JButton("Details of Doctors");
        //aDct_jfm.setSize(1000, 400);

        //Call and going for another method
        doc_b.addActionListener(e ->
                {
                    viewDoctor(doctors);
                }
        );
        //Main topic of the frame
        JLabel title_lbl1 = new JLabel("Westminster Consultation");
        title_lbl1.setFont(new Font("Merriweather", Font.BOLD, 40));
        title_lbl1.setSize(600, 50);
        title_lbl1.setLocation(450, 100);
        jpnl.add(title_lbl1);


        doc_b.setBounds(550, 300, 300, 40);
        jpnl.setLayout(null);
        jpnl.add(doc_b);

        // Create 'Chanel a Doctor' Button
        JButton chDct_b= new JButton("Chanel a Doctor");
        //Call and going for another method
        chDct_b.addActionListener(e ->
                {

                    bookDoctor(doctors,consultations);
                }
        );


        chDct_b.setBounds(550, 380, 300, 40);
        jpnl.setLayout(null);
        jpnl.add(chDct_b);

        // Create 'View Consultation' Button
        JButton vwChnl_b = new JButton("View Consultation");
        //Call and going for another method
        vwChnl_b.addActionListener(e ->
                {
                    viewChannel(doctors,consultations);
                }
        );


        vwChnl_b.setBounds(550, 460, 300, 40);
        jpnl.setLayout(null);
        jpnl.add(vwChnl_b);

        jpnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        aDct_jfm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        aDct_jfm.add(jpnl);
        aDct_jfm.setLocation(400,70);
        aDct_jfm.setSize(500, 300);
        aDct_jfm.setVisible(true);


    }

    @Override
    public void viewChannel(ArrayList<Doctor> doctors, ArrayList<Consultation> consultations) {
        //Create form for get user inputs
        JFrame ptnt_jfm = new JFrame();
        JPanel jpnl = new JPanel();
        ptnt_jfm.getContentPane();
        jpnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ptnt_jfm.add(jpnl);
        ptnt_jfm.setSize(500, 300);
        ptnt_jfm.setVisible(true);

        JLabel  pFNm_lbl = new JLabel("Insert the patient's First Name : ");
        pFNm_lbl.setFont(new Font("Lexend", Font.PLAIN, 20));
        pFNm_lbl.setBounds(100, 100, 400, 40);
        jpnl.setLayout(null);
        jpnl.add(pFNm_lbl);

        JTextField pFNm_jtf = new JTextField();
        pFNm_jtf.setSize(200,30);
        pFNm_jtf.setLocation(500, 100);
        jpnl.add(pFNm_jtf);

        JLabel  pLNm_lbl = new JLabel("Insert the patient's Last Name:");
        pLNm_lbl.setFont(new Font("Lexend", Font.PLAIN, 20));
        pLNm_lbl.setBounds(100, 180, 400, 40);
        jpnl.setLayout(null);
        jpnl.add(pLNm_lbl);

        JTextField pLNm_jtf = new JTextField();
        pLNm_jtf.setSize(200,30);
        pLNm_jtf.setLocation(500, 180);
        jpnl.add(pLNm_jtf);

        JButton sub_btn = new JButton("Submit");
        Dimension sizeSubmit = sub_btn.getPreferredSize();
        sub_btn.setBounds(300, 280, 150, 30);
        sub_btn.addActionListener(e ->
                {
                    //Create Consultation Details table
                    JFrame only_tbl = new JFrame();
                    JPanel only_pnl = new JPanel();
                    only_tbl.getContentPane();
                    only_pnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                    only_tbl.add(only_pnl);
                    only_tbl.setSize(1300, 800);
                    only_tbl.setVisible(true);

                    String[] columns = { "Consult Date", "Doctor Name", "Start Time", "End Time","Consultation_Fee($)","Notes" };
                    DefaultTableModel model_tbl = new DefaultTableModel(columns, 0);
                    JTable tbl_1 = new JTable(model_tbl);
                    JScrollPane sp = new JScrollPane(tbl_1);
                    only_tbl.add(sp);

                    //Check Consultation array and assign book doctor method inputs
                    ArrayList<Consultation> c1=new ArrayList<>();
                    for (Consultation consultation:consultations) {

                        if (consultation.getPatientFName().equals(pFNm_jtf.getText())){

                            if (consultation.getPatientLName().equals(pLNm_jtf.getText())){

                                c1.add(consultation);
                                // Isn't find patient's name output
                            }else{
                                JOptionPane.showMessageDialog(null, "No previous Consultations..!");
                            }
                        }else {
                            JOptionPane.showMessageDialog(null, "No previous Consultations..!");
                        }
                    }
                    for (Consultation consultation:c1) {

                        String consult_Date = consultation.getConsultdate();
                        String doctor_Name = consultation.getDoctorName();
                        String Start_Time = consultation.getConslStartTime();
                        String End_Time = consultation.getConslEndTime();
                        int con_Fee = consultation.getConsulFee();
                        String Notes = consultation.getAdnlNotes();

                        Object[] data = {consult_Date, doctor_Name, Start_Time, End_Time, con_Fee, Notes};

                        model_tbl.addRow(data);


                    }
                }
        );
        jpnl.add(sub_btn);

    }

    @Override
    public void bookDoctor(ArrayList<Doctor> doctors, ArrayList<Consultation> consultations) {
        //Create a form
        JFrame bd_jfm = new JFrame();
        JPanel bd_pnl = new JPanel();
        bd_jfm.getContentPane();
        bd_pnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bd_jfm.add(bd_pnl);
        bd_jfm.setSize(500, 300);
        bd_jfm.setVisible(true);

        JLabel title_lbl = new JLabel("Details of Patients");
        title_lbl.setFont(new Font("Lexend", Font.PLAIN, 30));
        title_lbl.setSize(300, 30);
        title_lbl.setLocation(250, 30);
        bd_pnl.add(title_lbl);

        //Patient's First Name
        JLabel   pfn_lbl= new JLabel("First Name:");
        pfn_lbl.setFont(new Font("Lexend", Font.PLAIN, 20));
        pfn_lbl.setBounds(100, 100, 350, 40);
        bd_pnl.setLayout(null);
        bd_pnl.add(pfn_lbl);

        JTextField pfn_jtf = new JTextField();
        pfn_jtf.setSize(150,20);
        pfn_jtf.setLocation(350, 110);
        bd_pnl.add(pfn_jtf);

        //Patient's Last Name
        JLabel  pln_lbl = new JLabel("Last Name:");
        pln_lbl.setFont(new Font("Lexend", Font.PLAIN, 20));
        pln_lbl.setBounds(100, 150, 350, 40);
        bd_pnl.setLayout(null);
        bd_pnl.add(pln_lbl);

        JTextField pln_jtf = new JTextField();
        pln_jtf.setSize(150,20);
        pln_jtf.setLocation(350, 160);
        bd_pnl.add(pln_jtf);

        //Datepicker for Patient's DOB
        JLabel dob_lbl = new JLabel("Date of Birth :");
        dob_lbl.setFont(new Font("Lexend", Font.PLAIN, 20));
        dob_lbl.setBounds(100, 200, 350, 40);
        bd_pnl.setLayout(null);
        bd_pnl.add(dob_lbl);

        JTextField dob_jtf = new JTextField(20);
        dob_jtf.setSize(150,20);
        dob_jtf.setLocation(350, 210);
        bd_pnl.add(dob_jtf);

        //Get Popup for Patient's DOB
        JButton pop_btn = new JButton("Popup");
        Dimension pop_size = pop_btn.getPreferredSize();
        pop_btn.setBounds(550, 210, 100, 25);
        bd_pnl.add(pop_btn);
        pop_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                dob_jtf.setText(new Consult_Date(bd_jfm).setPickedDate());
            }
        });


        //Patient's Contact Number
        JLabel  pcnum_lbl = new JLabel("Contact Number:");
        pcnum_lbl.setFont(new Font("Lexend", Font.PLAIN, 20));
        pcnum_lbl.setBounds(100, 250, 350, 40);
        bd_pnl.setLayout(null);
        bd_pnl.add(pcnum_lbl);

        JTextField pcnum_jtf = new JTextField();
        pcnum_jtf.setSize(150,20);
        pcnum_jtf.setLocation(350, 260);
        bd_pnl.add(pcnum_jtf);

        //Consult Information
        JLabel cond_lbl = new JLabel("Details of the Consultation");
        cond_lbl.setFont(new Font("Lexend", Font.PLAIN, 30));
        cond_lbl.setSize(400, 40);
        cond_lbl.setLocation(250, 320);
        bd_pnl.add(cond_lbl);


        //Consult Date
        JLabel cDate_lbl = new JLabel("Consult Date:");
        cDate_lbl.setFont(new Font("Lexend", Font.PLAIN, 20));
        cDate_lbl.setBounds(100, 400, 350, 40);
        bd_pnl.setLayout(null);
        bd_pnl.add(cDate_lbl);

        JTextField cDate_jtf= new JTextField(20);
        cDate_jtf.setSize(150,20);
        cDate_jtf.setLocation(350, 410);
        bd_pnl.add(cDate_jtf);

        JButton pop_btn2 = new JButton("Popup");
        Dimension size = pop_btn2.getPreferredSize();
        pop_btn2.setBounds(550, 410, 100, 25);
        bd_pnl.add(pop_btn2);
        pop_btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                cDate_jtf.setText(new Consult_Date(bd_jfm).setPickedDate());
            }
        });

        //Consultation Start Time
        JLabel  stm_lbl = new JLabel("Start Time:");
        stm_lbl.setFont(new Font("Lexend", Font.PLAIN, 20));
        stm_lbl.setBounds(100, 450, 350, 40);
        bd_pnl.setLayout(null);
        bd_pnl.add(stm_lbl);


        String []startTimearray=new String[18];
        for (int i = 6; i < 18; i++) {
            startTimearray[i]= String.valueOf(i);
        }
        JComboBox jc_box1=new JComboBox(startTimearray);
        jc_box1.setSize(150,20);
        jc_box1.setLocation(350, 460);
        bd_pnl.add(jc_box1);


        String []h=new String[100];
        for (int i = 0; i <12; i++) {
            h[i]= String.valueOf(i+1);

        }

        //Consultation Hours
        JLabel  hrs_lbl = new JLabel("Consultation Hours");
        hrs_lbl.setFont(new Font("Lexend", Font.PLAIN, 20));
        hrs_lbl.setBounds(100, 500, 350, 40);
        bd_pnl.setLayout(null);
        bd_pnl.add(hrs_lbl);


        JComboBox jc_box2=new JComboBox(h);
        jc_box2.setSize(150,20);
        jc_box2.setLocation(350, 510);
        bd_pnl.add(jc_box2);

        //Consultation End Time
        JLabel  endtm_lbl = new JLabel("End time:");
        endtm_lbl.setFont(new Font("Lexend", Font.PLAIN, 20));
        endtm_lbl.setBounds(800, 450, 250, 40);
        bd_pnl.setLayout(null);
        bd_pnl.add(endtm_lbl);

        JTextField endtm_jtf = new JTextField();
        endtm_jtf.setSize(150,20);
        endtm_jtf.setLocation(1050, 460);
        bd_pnl.add(endtm_jtf);

        jc_box2.addActionListener(e ->{
            int getStartTime= Integer.parseInt(jc_box1.getSelectedItem().toString());

            int howMnyHrs= Integer.parseInt(jc_box2.getSelectedItem().toString());
            int finalTime=getStartTime+howMnyHrs;
            endtm_jtf.setText(String.valueOf(finalTime));
        } );



        String []docName=new String[10];
        for (int i = 0; i < doctors.size(); i++) {
            docName[i]=doctors.get(i).getFirstName();
        }


        //Doctor's Name
        JLabel  docN_lbl = new JLabel("Doctor's Name:");
        docN_lbl .setFont(new Font("Lexend", Font.PLAIN, 20));
        docN_lbl .setBounds(100, 550, 350, 40);
        bd_pnl.setLayout(null);
        bd_pnl.add(docN_lbl );

        JComboBox jc_box3=new JComboBox(docName);
        jc_box3.setSize(150,20);
        jc_box3.setLocation(350, 560);
        bd_pnl.add(jc_box3);

        //Description
        JLabel  dspn_lbl = new JLabel("Description");
        dspn_lbl.setFont(new Font("Arial", Font.PLAIN, 20));
        dspn_lbl.setBounds(100, 600, 350, 40);
        bd_pnl.setLayout(null);
        bd_pnl.add( dspn_lbl);

        JTextField dspn_jtf = new JTextField();
        dspn_jtf.setSize(350,40);
        dspn_jtf.setLocation(350, 610);
        bd_pnl.add(dspn_jtf);

        //Consult fee Calculate
        JLabel  confst_lbl = new JLabel("Is this first consultation ? :");
        confst_lbl.setFont(new Font("Lexend", Font.PLAIN, 20));
        confst_lbl.setBounds(100, 680, 350, 40);
        bd_pnl.setLayout(null);
        bd_pnl.add(confst_lbl);

        JButton yes_btn = new JButton("Yes");
        yes_btn.setBounds(350, 690, 100, 25);
        bd_pnl.add(yes_btn);


        JButton no_btn = new JButton("No");
        no_btn.setBounds(450, 690, 100, 25);
        bd_pnl.add(no_btn);


        //Consultation Fee
        JLabel  cfee_lbl = new JLabel("Consultation Fee ($) :");
        cfee_lbl.setFont(new Font("Lexend", Font.PLAIN, 20));
        cfee_lbl.setBounds(800, 690, 350, 40);
        bd_pnl.setLayout(null);
        bd_pnl.add(cfee_lbl);

        JTextField cfee_jtf = new JTextField();
        cfee_jtf.setSize(150,20);
        cfee_jtf.setLocation(1050, 700);
        bd_pnl.add(cfee_jtf);

        yes_btn.addActionListener(e ->{
            int hrsCalculate= Integer.parseInt(jc_box2.getSelectedItem().toString());
            int TotalConslFee=hrsCalculate*15;
            cfee_jtf.setText(String.valueOf(TotalConslFee));

        });
        no_btn.addActionListener(e ->{
            int hrsCalculate= Integer.parseInt(jc_box2.getSelectedItem().toString());
            int TotalConslFee=hrsCalculate*25;
            cfee_jtf.setText(String.valueOf(TotalConslFee));

        });

        //Book Doctor
        JButton bkDct_btn = new JButton("Book Doctor");
        Dimension bkDct_size = bkDct_btn.getPreferredSize();
        bkDct_btn.setBounds(1000, 760, 250, 40);
        bd_pnl.add(bkDct_btn);
        bkDct_btn.addActionListener(e ->
        {
            System.out.println("book doctor start");

            //Create Consultation object
            Consultation consultation1 = new Consultation();
            //Assign all user inputs for object
            consultation1.setDoctorName(jc_box3.getSelectedItem().toString());
            consultation1.setConsultdate(cDate_jtf.getText());
            consultation1.setConslStartTime(jc_box1.getSelectedItem().toString());
            consultation1.setAdnlNotes(dspn_jtf.getText());
            consultation1.setConslEndTime(endtm_jtf.getText());
            consultation1.setPatientFName(pfn_jtf.getText());
            consultation1.setConsulFee(Integer.parseInt(cfee_jtf.getText()));
            consultation1.setPatientDOB(dob_jtf.getText());
            consultation1.setPatientMobileNum(pcnum_jtf.getText());
            consultation1.setPatientLName(pln_jtf.getText());

            //Checking Doctor's availability
            if (consultations.isEmpty()){
                consultations.add(consultation1);
            }else {
                for (Consultation consultation:consultations) {
                    if (consultation.getDoctorName().equals(consultation1.getDoctorName())){
                        if (consultation.getConsultdate().equals(consultation1.getConsultdate())){
                            int c1= Integer.parseInt(consultation.getConslStartTime());
                            int c2= Integer.parseInt(consultation.getConslEndTime());
                            int d1= Integer.parseInt(consultation1.getConslEndTime());
                            int d2= Integer.parseInt(consultation1.getConslStartTime());
                            if (c1>d1 || c2<d2){
                                consultations.add(consultation1);
                            }else{
                                System.out.println("This doctor has another appoinment..");
                                JOptionPane.showMessageDialog(null, "booked");
                            }
                        }else{
                            consultations.add(consultation1);
                        }

                    }else {
                        consultations.add(consultation1);
                    }
                }
            }
        });
        System.out.println("End");
    }

    @Override
    public void viewDoctor(ArrayList<Doctor> doctors) {

        JFrame vwdoc_jf = new JFrame();
        JPanel vwdoc_pnl = new JPanel();
        vwdoc_jf.getContentPane();
        vwdoc_pnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        vwdoc_jf.add(vwdoc_pnl);
        vwdoc_jf.setSize(500, 300);
        vwdoc_jf.setVisible(true);
        doctors.sort(new Comparator<Doctor>() {
            @Override
            public int compare(Doctor d1, Doctor d2) {
                return d1.getLastName().compareTo(d2.getLastName());
            }
        });
        //Create table column header
        String[] Column = { "First_Name", "Last_Name", "Contact_Number","licene_Number","Specialication" };
        DefaultTableModel model_tbl = new DefaultTableModel(Column, 0);
        JTable vwdct_tbl = new JTable(model_tbl);
        JScrollPane vwdct_jsp = new JScrollPane(vwdct_tbl);
        vwdoc_jf.add(vwdct_jsp);

        //Implement assign Doctor's details from console part
        for (int i =0; i<doctors.size();i++) {
            String fstName = doctors.get(i).getFirstName();
            String lastName = doctors.get(i).getLastName();
            int cntctNumber = doctors.get(i).getMobileNumber();
            String liceneNumber = doctors.get(i).getMedLicenceNum();
            String Specialication = doctors.get(i).getSpecialisation();
            Object[] data = {fstName, lastName,cntctNumber,liceneNumber,Specialication};
            model_tbl.addRow(data);
        }
    }
    }

public class Doctor extends Person{
    private String medLicenceNum;
    private String specialisation;

//Insert Getter Setter
    public String getMedLicenceNum() {
        return medLicenceNum;
    }

    public void setMedLicenceNum(String medLicenceNum) {
        this.medLicenceNum = medLicenceNum;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
}

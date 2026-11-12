package model;

public abstract class Staff6 extends Account6 {
    private String staffCode;
    private String position;

    public Staff6() { super(); }
    public Staff6(int id, String username, String password, String fullName, String staffCode, String position) {
        super(id, username, password, fullName, null, null, null, null, "STAFF");
        this.staffCode = staffCode;
        this.position = position;
    }

    public String getStaffCode() { return staffCode; }
    public void setStaffCode(String staffCode) { this.staffCode = staffCode; }
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}

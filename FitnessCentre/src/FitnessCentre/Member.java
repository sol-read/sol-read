package FitnessCentre;

public class Member {

    private char memberType;
    private int memberId;
    private String name;
    private double fees;

    public void setMemberType(char memberType) {
        this.memberType = memberType;
    }
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public char getMemberType() { return memberType; }
    public int getMemberId() {return memberId; }
    public String getName() { return name; }

    public double getFees() {return fees; }

    public Member() {
        // Default constructor.
    }

    public Member(char memberType, int memberId, String name, double fees) {
        this.memberType = memberType;
        this.memberId = memberId;
        this.name = name;
        this.fees = fees;
    }

    @Override
    public String toString() {
        return memberType + ", " + memberId + ", " + name + ", " + fees;
    }



}

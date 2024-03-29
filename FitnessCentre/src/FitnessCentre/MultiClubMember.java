package FitnessCentre;

public class MultiClubMember extends Member {

    private int membershipPoints;
    @Override
    public void addMembershipPoints(int membershipPoints) {
        this.membershipPoints += membershipPoints;
    }

    public int getMembershipPoints() { return membershipPoints; }

    public MultiClubMember(char memberType, int memberId, String name, double fees, int membershipPoints) {
        super(memberType, memberId, name, fees);
        this.membershipPoints = membershipPoints;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + membershipPoints;
    }

    @Override
    public Club getClub() {
        return null;
    }

}

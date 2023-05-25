package FitnessCentre;

public class SingleClubMember extends Member {

    private Club club;

    public void setClub(Club club) {
        this.club = club;
    }

    @Override
    public Club getClub() { return club; }
    @Override
    public void addMembershipPoints(int a) {
        // Do nothing.
    }

    public SingleClubMember(char memberType, int memberId, String name, double fees, Club club) {
        super(memberType, memberId, name, fees);
        this.club = club;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + club.getClubId();
    }

}

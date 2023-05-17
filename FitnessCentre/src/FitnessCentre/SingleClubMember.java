package FitnessCentre;

public class SingleClubMember extends Member {

    private int club;

    public void setClub(int club) {
        this.club = club;
    }

    public int getClub() { return club; }

    public SingleClubMember(char memberType, int memberId, String name, double fees, int club) {
        super(memberType, memberId, name, fees);
        this.club = club;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + club;
    }

}

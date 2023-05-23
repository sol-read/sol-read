package FitnessCentre;

public class SingleClubMember extends Member {

    private int clubId;

    public void setClub(int clubId) {
        this.clubId = clubId;
    }

    public int getClub() { return clubId; }

    public SingleClubMember(char memberType, int memberId, String name, double fees, int clubId) {
        super(memberType, memberId, name, fees);
        this.clubId = clubId;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + clubId;
    }

}

package FitnessCentre;

public class Admin extends Member {

    public Admin() {
        super('A',0,"Admin",0);
    }

    @Override
    public Club getClub() {
        return null;
    }
    @Override
    public void addMembershipPoints(int a) {
        // Do nothing.
    }
}

package FitnessCentre;

import java.util.LinkedList;

public class Club {

    private int id;
    private String name;
    LinkedList<Member> memberList;
    double fees;

    public void assignToClub(SingleClubMember member) {
        memberList.add(member);
    }

    public Club(int id, String name, double fees) {
        this.id = id;
        this.name = name;
        this.fees = fees;
    }

    @Override
    public String toString() { return id + ", " + name + ", " + fees; }

}

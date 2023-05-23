package FitnessCentre;

import java.util.LinkedList;

public class Club {

    private int id;
    private String name;
    private LinkedList<Member> memberList = new LinkedList<>();
    private double fees;

    public int getClubId() { return id; }
    public String getName() { return name; }
    public LinkedList<Member> getMemberList() { return memberList; }
    public double getFees() { return fees; }

    public Club(int id, String name, double fees) {
        this.id = id;
        this.name = name;
        this.fees = fees;
    }

    @Override
    public String toString() { return id + ", " + name + ", " + fees; }

}

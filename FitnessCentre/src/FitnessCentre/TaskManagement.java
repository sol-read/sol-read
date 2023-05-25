package FitnessCentre;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class TaskManagement {

    final private Scanner reader = new Scanner(System.in);

    private int getIntInput() {

        int choice = 0;

        while(choice == 0) {
            try {
                choice = reader.nextInt();
                if(choice == 0) { throw new InputMismatchException(); }
                reader.nextLine();
            } catch(InputMismatchException im) {
                reader.nextLine();
                System.out.println("\nInvalid input! Error: " + im.getMessage() + "\nPlease try again: ");
            }
        }
        return choice;
    }

    protected void printClubOptions() {

        System.out.println("\n1) Club Mercury");
        System.out.println("2) Club Neptune");
        System.out.println("3) Club Jupiter");
        System.out.println("4) Multiple Clubs");
    }

    public int getChoice() {

        int choice = 0;

        System.out.print("""
                
                Welcome to Sol's Fitness Centre
                -------------------------------
                1) Add Member
                2) Remove Member
                3) Display Member Information
                4) Display Club Information
                5) Member Login
                -1) Quit
                
                Please select an option:\t""");

        choice = getIntInput();
        return choice;
    }

    public String addMembers(LinkedList<Member> memberList, ArrayList<Club> clubList) {

        String name;
        int clubId = 0;
        String memberReturnString;
        double fees;
        int memberId;
        Member member;
        Calculator<Integer> calculator;
        Club club;

        System.out.print("\nEnter new member's name: ");
        name = reader.nextLine();

        printClubOptions();

        do {
            System.out.print("\nEnter the club that this member will have access to: ");
            clubId = getIntInput();
        } while(clubId < 1 || clubId > 4);

        if(memberList.size() > 0) {
            memberId = memberList.getLast().getMemberId() + 1;
        } else { memberId = 1; }

        calculator = (n) -> {
            return switch (n) {
                case 1 -> 900;
                case 2 -> 950;
                case 3 -> 1000;
                case 4 -> 1200;
                default -> -1;
            };
        };


        if(clubId == 4) {

            member = new MultiClubMember('M',memberId,name,calculator.calculateFees(clubId),100);
            memberList.add(member);
            System.out.println("\nMulti-club Member successfully added!\n");
            memberReturnString = member.toString();
        } else {

            club = clubList.get(clubId-1);
            member = new SingleClubMember('S',memberId,name,club.getFees(),club);
            memberList.add(member);
            club.getMemberList().add(member);


            System.out.println("\nSingle club Member successfully added to " + club.getName() + "!\n");
            memberReturnString = member.toString();
        }

        return memberReturnString;

    }

    public void removeMember(LinkedList<Member> memberList) {

        System.out.print("\nEnter the ID of the member you'd like to remove: ");
        int memberId = getIntInput();

        for(int i=0;i<memberList.size();i++) {
            if(memberList.get(i).getMemberId() == memberId) {
                memberList.remove(i);
                System.out.println("\nMember with ID " + memberId + " has been removed.");
                return;
            }
        }
        System.out.println("\nNo user found with ID " + memberId);
    }

    public void printMemberInfo(LinkedList<Member> memberList) {

        System.out.print("\nEnter the ID of the member you'd like to know more about: ");
        int memberId = getIntInput();

        for(int i=0;i<memberList.size();i++) {
            if(memberList.get(i).getMemberId() == memberId) {
                String[] memberInfo = memberList.get(i).toString().split(", ");
                if(memberInfo[0].equals("S")) {
                    System.out.printf("""
                        \nMember Type: %s
                        Member ID: %s
                        Member Name: %s
                        Membership Fees: £%s
                        Club ID: %s
                        """,memberInfo[0],memberInfo[1],memberInfo[2],memberInfo[3],memberInfo[4]);
                    return;
                } else {
                    System.out.printf("""
                        \nMember Type: %s
                        Member ID: %s
                        Member Name: %s
                        Membership Fees: £%s
                        Membership Points: %s
                        """,memberInfo[0],memberInfo[1],memberInfo[2],memberInfo[3],memberInfo[4]);
                    return;
                }
            }

        } System.out.println("\nMember does not exist or has been removed.");
    }

    public void printClubInfo(ArrayList<Club> clubList) {

        System.out.print("\nEnter the ID of the club you'd like to know more about: ");
        int clubId = getIntInput();

        for(int i=0;i<clubList.size();i++) {
            if (clubList.get(i).getClubId() == clubId) {
                String[] clubInfo = clubList.get(i).toString().split(", ");
                LinkedList<Member> memberList = clubList.get(i).getMemberList();
                System.out.printf("""
                                                        
                                Club ID: %s
                                Club Name: %s
                                Annual Fee: £%s
                                No. Members: %s
                                                        
                                """
                        , clubInfo[0], clubInfo[1], clubInfo[2], memberList.size());
                return;
            }
        } System.out.println("\nClub with ID " + clubId + " could not be found.\n");
    }

    public Member memberLogin(LinkedList<Member> memberList) {

        Member thisMember;
        System.out.print("Enter your member ID: ");
        int memberId = getIntInput();
        for(int i=0;i<memberList.size();i++) {
            if(memberList.get(i).getMemberId() == memberId) {
                thisMember = memberList.get(i);
                System.out.println("Hello, " + thisMember.getName() + "!");
                return thisMember;
            }
        } System.out.println("Could not find member with ID: " + memberId);
        return null;
    }

    public int getMemberActionChoice() {

        int choice;
        System.out.print("""
                             
                1) Refer a Friend
                2) Meet others at your club(s)
                3) End your membership
                4) Logout
                                    
                Please select an option:\t""");
        choice = getIntInput();
        return choice;
    }

    public Member referFriend(LinkedList<Member> memberList, Member currentUser) {

        Member newMember;
        int memberId;
        if(memberList.size() > 0) {
            memberId = memberList.getLast().getMemberId() + 1;
        } else { memberId = 1; }
        System.out.print("Enter your friend's name: ");
        String name = reader.nextLine();
        if(currentUser.getMemberType() == 'S') {
            newMember = new SingleClubMember('S',memberId,name,currentUser.getFees()*0.85,currentUser.getClub());
        } else {
            newMember = new MultiClubMember('M',memberId,name, currentUser.getFees(), 200);
            currentUser.addMembershipPoints(100);
        }

        System.out.print("""
                
                New user: " + newMember.getName() + " added with ID: " + newMember.getMemberId() + ". 
                Enjoy your bonus points!
                
                """);

        memberList.add(newMember);
        return newMember;
    }

    public LinkedList<Member> getMembersOfThisClub(LinkedList<Member> memberList, Member currentUser) {

        LinkedList<Member> membersOfThisClubList = new LinkedList<>();

        for(int i=0;i<memberList.size();i++) {
            if(memberList.get(i).getClub() == currentUser.getClub()) {
                membersOfThisClubList.add(memberList.get(i));
            }
        }

        return membersOfThisClubList;
    }

    public void displayMembersOfThisClub(LinkedList<Member> memberList) {

        System.out.println("There are " + memberList.size() + " members in your club.");
        for(int i=0;i<memberList.size();i++) {
            Member thisMember = memberList.get(i);
            System.out.println("Member " + (i+1) + ": " + thisMember.getName());
        }
    }

    public void removeMember(LinkedList<Member> memberList, Member currentUser) {

        String input = "X";
        while (!input.equals("Y")) {
            System.out.print("Sorry to see you go! Are you sure you want to do this? Type 'Y' to confirm: ");
            input = reader.nextLine();
            if(input.equals("Y")) {
                System.out.println("We'll miss you, " + currentUser.getName() + "!");
                for(int i = 0;i<memberList.size();i++) {
                    if(memberList.get(i).getMemberId() == currentUser.getMemberId()) {
                        memberList.remove(i);
                    }
                }
            } else {
                System.out.println("Phew! Glad you changed your mind!");
            }
        }
    }
}

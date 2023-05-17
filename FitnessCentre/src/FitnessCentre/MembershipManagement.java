package FitnessCentre;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {

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

    private void printClubOptions() {

        System.out.println("1) Club Mercury");
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
                9) Quit
                
                Please select an option: """);

        choice = getIntInput();
        return choice;
    }

    public String addMembers(LinkedList<Member> memberList) {

        String name;
        int club = 0;
        String memberReturnString;
        double fees;
        int memberId;
        Member member;
        Calculator<Integer> calculator;

        System.out.print("\nEnter new member's name: ");
        name = reader.nextLine();

        printClubOptions();

        do {
            System.out.print("\nEnter the club that this member will have access to: ");
            club = getIntInput();
        } while(club < 1 || club > 4);

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


        if(club == 4) {

            member = new MultiClubMember('M',memberId,name,calculator.calculateFees(club),100);
            memberList.add(member);
            System.out.println("\nMulti-club Member successfully added!\n");
            memberReturnString = member.toString();
        } else {

            member = new SingleClubMember('S',memberId,name,calculator.calculateFees(club),club);
            memberList.add(member);
            System.out.println("\nSingle club Member successfully added!\n");
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
                        \n""",memberInfo[0],memberInfo[1],memberInfo[2],memberInfo[3],memberInfo[4]);
                    return;
                } else {
                    System.out.printf("""
                        \nMember Type: %s
                        Member ID: %s
                        Member Name: %s
                        Membership Fees: £%s
                        Membership Points: %s
                        \n""",memberInfo[0],memberInfo[1],memberInfo[2],memberInfo[3],memberInfo[4]);
                    return;
                }
            } else {
                System.out.println("\nMember does not exist or has been removed.");
            }
        }
    }

}

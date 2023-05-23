package FitnessCentre;

import java.util.ArrayList;
import java.util.LinkedList;

public class Controller {

    public static void main(String[] args) {

        String memberString;

        MembershipManagement management = new MembershipManagement();
        FileHandler handler = new FileHandler();

        LinkedList<Member> memberList = handler.readMemberFile();
        ArrayList<Club> clubList = handler.readClubFile();
        int choice = management.getChoice();

        while (choice != 9) {
            switch (choice) {
                case 1 -> {
                    memberString = management.addMembers(memberList);
                    handler.appendFile(memberString);
                }
                case 2 -> {
                    management.removeMember(memberList);
                    handler.overwriteFile(memberList);
                }
                case 3 -> {
                    management.printMemberInfo(memberList);
                }
                default -> {
                    System.out.println("Invalid entry!");
                }
            }

            choice = management.getChoice();
        }

        System.out.println("\nThanks - goodbye!\n");

    }

}


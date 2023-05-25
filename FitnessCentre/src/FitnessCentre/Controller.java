package FitnessCentre;

import java.util.ArrayList;
import java.util.LinkedList;

public class Controller {

    public static void main(String[] args) {

        String memberString;

        Admin admin = new Admin();

        Member currentUser = admin;

        TaskManagement management = new TaskManagement();
        FileHandler handler = new FileHandler();

        ArrayList<Club> clubList = handler.readClubFile();
        LinkedList<Member> memberList = handler.readMemberFile(clubList);

        int choice = management.getChoice();

        while (choice != -1) {
            switch (choice) {
                case 1 -> {
                    memberString = management.addMembers(memberList, clubList);
                    handler.appendFile(memberString);
                }
                case 2 -> {
                    if(currentUser.getMemberType() != 'A') {
                        System.out.println("You do not have permission to do that!");
                    } else {
                        management.removeMember(memberList);
                        handler.overwriteFile(memberList);
                    }
                }
                case 3 -> {
                    management.printMemberInfo(memberList);
                }
                case 4 -> {
                    management.printClubOptions();
                    management.printClubInfo(clubList);
                }
                case 5 -> {
                    currentUser = management.memberLogin(memberList);
                    int memberChoice = management.getMemberActionChoice();
                    while(memberChoice != 4) {
                        switch(memberChoice) {
                            case 1 -> {
                                Member newMember = management.referFriend(memberList,currentUser);
                                handler.overwriteFile(memberList);
                            }
                            case 2 -> {

                            }
                            case 3 -> {
                                management.removeMember(memberList,currentUser);
                                handler.overwriteFile(memberList);
                            }
                            default -> {
                                System.out.println("Invalid entry!");
                            }
                        }
                        memberChoice = management.getMemberActionChoice();
                    }
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


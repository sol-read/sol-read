package FitnessCentre;

import java.io.*;
import java.util.LinkedList;

public class FileHandler {

    public LinkedList<Member> readFile() {

        LinkedList<Member> memberList = new LinkedList();
        String line;
        String[] splitLine;
        Member member;


        try(BufferedReader reader = new BufferedReader(new FileReader("member-file.csv"))) {
            line = reader.readLine();
            while(line != null) {
                splitLine = line.split(", ");
                if(splitLine[0].equals("S")) {
                    member = new SingleClubMember('S',Integer.parseInt(splitLine[1]), splitLine[2], Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));

                } else {
                    member = new MultiClubMember('M', Integer.parseInt(splitLine[1]), splitLine[2], Double.parseDouble(splitLine[3]), Integer.parseInt(splitLine[4]));
                }
                memberList.add(member);
                line = reader.readLine();
            }
        } catch(IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return memberList;
    }

    public void appendFile(String member) {

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("member-file.csv",true))) {

            writer.write(member + "\n");

        } catch(IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

    }

    public void overwriteFile(LinkedList<Member> memberList) {

        String thisMember;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("member.temp",false))) {
            for(int i = 0;i< memberList.size();i++) {
                thisMember = memberList.get(i).toString();
                writer.write(thisMember + "\n");
            }
        } catch(IOException e) {
            System.out.println("Error writing to temp file: " + e.getMessage());
        }

        try {
            File oldFile = new File("member-file.csv");
            File newFile = new File("member.temp");

            oldFile.delete();
            newFile.renameTo(oldFile);
        } catch(Exception e) {
            System.out.println("Error renaming temp file: " + e.getMessage());
        }
    }

}

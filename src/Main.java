import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {
        List<Bejegyzes> bejegyzesek = new ArrayList<Bejegyzes>();
        bejegyzesek.add(new Bejegyzes("tartalom1","ember1"));
        bejegyzesek.add(new Bejegyzes("tartalom2","ember2"));
        int db=0;
        Scanner scanner=new Scanner(System.in);


        while(db<1){
            try {
                System.out.println("Kérek egy darabszámot: ");
                db = scanner.nextInt();

            }
            catch (Exception e) {
                System.out.println("Nem természetes számot adtál meg");
            }
        }
        for(int i=0;i<db;i++){
            scanner=new Scanner(System.in);
            System.out.println("Kérek egy bejegyzést: ");
            String s = scanner.nextLine();
            bejegyzesek.add(new Bejegyzes(s,"felhasználó"));
        }
        try {
                File myObj = new File("bejegyzesek.csv");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String[] data = myReader.nextLine().split(";");
                    bejegyzesek.add(new Bejegyzes(data[0],data[1]));
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("Ez a fájl nem létezik");
                e.printStackTrace();
            }
            Random random = new Random();
            for (int i = 0; i < bejegyzesek.stream().count()*20; i++) {
                int current = random.nextInt(0,bejegyzesek.size());
                bejegyzesek.get(current).Like();
            }
            System.out.println("Kérek egy szöveget");
            bejegyzesek.get(1).setTartalom(scanner.nextLine());
            for (int i = 0; i < bejegyzesek.stream().count(); i++) {
                System.out.println(bejegyzesek.get(i).toString());
            }
            int max = 0;
            for (int i = 0; i < bejegyzesek.size(); i++) {
                max = Math.max(max,bejegyzesek.get(i).getLikeok());
            }
            System.out.printf("likeok száma: %d",max);
            if(max > 35) System.out.println("Igen van");
            else System.out.println("Nincs");
            int count = 0;
            for (int i = 0; i < bejegyzesek.size(); i++) {
                if(bejegyzesek.get(i).getLikeok()<15){
                    count++;
                }
            }
            System.out.println();
            System.out.printf("Összesen %d bejegyzés van ami 15-nél kevesebb likeot kapott",count);
            List<Bejegyzes> csokkeno = new ArrayList<>();
            max = 0;
            int index = 0;
            String current = "";
            int szamlalo = 0;
            while(bejegyzesek.size()>0){
                if(max<bejegyzesek.get(szamlalo).getLikeok()){
                    max = bejegyzesek.get(index).getLikeok();
                    index = szamlalo;
                }
                szamlalo++;
                if(szamlalo==bejegyzesek.size()){
                    csokkeno.add(bejegyzesek.get(index));
                    bejegyzesek.remove(index);
                    szamlalo=0;
                    index = 0;
                    max = 0;
                }
            }
            for (int i = 0; i < csokkeno.size(); i++) {
                System.out.println(csokkeno.get(i).toString());
            }
        String filePath = "bejegyzesek_rendezett.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Bejegyzes line : csokkeno) {
                writer.write(line.getTartalom()+";"+line.getSzerzo());
                writer.newLine();
            }
            System.out.println("Sikerült beírni");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
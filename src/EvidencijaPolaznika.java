import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;


public class EvidencijaPolaznika {
    public static void main(String[] args) {
//        ArrayList<Polaznik> polaznici = new ArrayList<>();
//        HashSet<Polaznik> polaznici = new HashSet<>();
        TreeSet<Polaznik> polaznici = new TreeSet<>(
                Comparator.comparing(Polaznik::getEmail, String.CASE_INSENSITIVE_ORDER));

        Scanner input = new Scanner(System.in);

        String ime = "";
        String prezime = "";
        String email = "";
        boolean odluka = true;

        System.out.println("odaberite 1 za unos novog polaznika, 2 za ispis polaznika, 3 za pretraživanje korisnika po unesenoj email adresi");

        int izbor = input.nextInt();
        input.nextLine();
        ucitaj(polaznici);

        switch (izbor) {
            case 1:
                while (odluka) {
                    System.out.println("unesite ime polaznika");
                    ime = input.nextLine();
                    System.out.println("unesite prezime polaznika");
                    prezime = input.nextLine();
                    System.out.println("unesite email ispis polaznika");
                    email = input.nextLine();
                    Polaznik novi = new Polaznik(ime, prezime, email);

                    if (polaznici.add(novi)) {
                        System.out.println("Polaznik dodan.");
                    } else {
                        System.out.println("Polaznik s tim emailom već postoji!");
                    }

                    System.out.println("unos sljedećeg polaznika (d)?");
                    if (input.nextLine().equalsIgnoreCase("d")) {
                        odluka = true;
                    } else {
                        odluka = false;
                        spremi(polaznici);
                        System.out.println("hvala!");
                    }
                }
                break;
            case 2:
                System.out.println("popis svih polaznika: ");
                for (Polaznik p : polaznici) System.out.println(p.polaznici());
                break;
            case 3:
                System.out.print("Unesite email za pretraživanje: ");
                String unosEmail = input.nextLine();

                boolean pronaden = false;

                for (Polaznik p : polaznici) {
                    if (p.getEmail().equalsIgnoreCase(unosEmail)) {
                        System.out.println("Polaznik pronađen:");
                        System.out.println(p.polaznici());
                    } else {
                        System.out.println("Nijedan polaznik s tim emailom ne postoji.");
                    }
                }
                break;
        }
    }

    public static void spremi(TreeSet<Polaznik> polaznici) {
        try (FileWriter fw = new FileWriter("polaznici.txt")) {

            for (Polaznik p : polaznici) {
                fw.write(
                        p.getIme() + ";" +
                                p.getPrezime() + ";" +
                                p.getEmail() + "\n"
                );
            }

        } catch (IOException e) {
            System.out.println("Greška pri spremanju u datoteku!");
        }
    }

    public static void ucitaj(TreeSet<Polaznik> polaznici) {
        File file = new File("polaznici.txt");

        if (!file.exists()) return;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");

                if (parts.length == 3) {
                    polaznici.add(
                            new Polaznik(parts[0], parts[1], parts[2])
                    );
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Datoteka nije pronađena!");
        }
    }
}


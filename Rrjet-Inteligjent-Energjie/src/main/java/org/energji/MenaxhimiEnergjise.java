package org.energji;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenaxhimiEnergjise {

        private static String[][] zonat = new String[50][7];
        private static int numerimi = 0;
        private static final String EMRI_SKEDARIT = "raport-energji.txt";

        public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
            int zgjedhja;

            do {
                System.out.println("\n=== SISTEMI I MENAXHIMIT TË ENERGJISË ===");
                System.out.println("1. Shto zonë energjie");
                System.out.println("2. Shfaq zonat");
                System.out.println("3. Zbulo mbingarkesat");
                System.out.println("4. Aktivizo rezervën");
                System.out.println("5. Statistika");
                System.out.println("6. Ruaj në skedar");
                System.out.println("7. Dil");
                System.out.print("Zgjidh opsionin: ");

                zgjedhja = lexoIntSigurt(sc);

                switch (zgjedhja) {
                    case 1 -> shtoZone(sc);
                    case 2 -> shfaqZonat();
                    case 3 -> zbuloMbingarkesat();
                    case 4 -> aktivizoRezerven(sc);
                    case 5 -> statistika();
                    case 6 -> ruajNeSkedar();
                    case 7 -> System.out.println("Duke dalë nga programi...");
                    default -> System.out.println("Opsion i pavlefshëm");
                }

            } while (zgjedhja != 7);
        }

        private static void shtoZone(Scanner sc) {
            sc.nextLine();

            System.out.print("Emri i zonës: ");
            zonat[numerimi][0] = sc.nextLine();

            System.out.print("Konsumi: ");
            zonat[numerimi][1] = String.valueOf(lexoDoubleSigurt(sc));

            System.out.print("Prodhimi: ");
            zonat[numerimi][2] = String.valueOf(lexoDoubleSigurt(sc));

            System.out.print("Kapaciteti maksimal: ");
            zonat[numerimi][3] = String.valueOf(lexoDoubleSigurt(sc));

            System.out.print("Prioriteti (1-5): ");
            zonat[numerimi][4] = String.valueOf(lexoIntSigurt(sc));

            sc.nextLine();
            System.out.print("Statusi (AKTIVE/DËSHTIM): ");
            zonat[numerimi][5] = sc.nextLine();

            System.out.print("Rezervë aktive (true/false): ");
            zonat[numerimi][6] = sc.nextLine();

            numerimi++;
            System.out.println("Zona u shtua me sukses");
        }

        private static void shfaqZonat() {
            if (numerimi == 0) {
                System.out.println("Nuk ka zona të regjistruara");
                return;
            }

            System.out.println("------------------------------------------------------------------------------------------------");
            System.out.printf("%-12s %-12s %-12s %-12s %-10s %-12s %-10s%n",
                    "Zona", "Konsum", "Prodhim", "Kapacitet", "Prioritet", "Status", "Rezervë");
            System.out.println("------------------------------------------------------------------------------------------------");

            for (int i = 0; i < numerimi; i++) {
                System.out.printf("%-12s %-12s %-12s %-12s %-10s %-12s %-10s%n",
                        zonat[i][0],
                        zonat[i][1],
                        zonat[i][2],
                        zonat[i][3],
                        zonat[i][4],
                        zonat[i][5],
                        zonat[i][6]);
            }
        }

        private static void zbuloMbingarkesat() {
            boolean uGjet = false;

            for (int i = 0; i < numerimi; i++) {
                double konsum = Double.parseDouble(zonat[i][1]);
                double kapacitet = Double.parseDouble(zonat[i][3]);

                if (konsum > kapacitet) {
                    System.out.println("MBINGARKESË e zbuluar në zonën: " + zonat[i][0]);
                    uGjet = true;
                }
            }

            if (!uGjet) {
                System.out.println("Nuk u gjet asnjë mbingarkesë");
            }
        }

        private static void aktivizoRezerven(Scanner sc) {
            shfaqZonat();
            System.out.print("Zgjidh numrin e zonës: ");
            int indeksi = lexoIntSigurt(sc) - 1;

            if (indeksi < 0 || indeksi >= numerimi) {
                System.out.println("Numër zone i pavlefshëm");
                return;
            }

            zonat[indeksi][6] = "true";
            System.out.println("Rezerva u aktivizua për zonën " + zonat[indeksi][0]);
        }

        private static void statistika() {
            int numerRezervash = 0;

            for (int i = 0; i < numerimi; i++) {
                if (zonat[i][6].equalsIgnoreCase("true")) {
                    numerRezervash++;
                }
            }

            System.out.println("Numri total i zonave: " + numerimi);
            System.out.println("Zona me rezervë aktive: " + numerRezervash);
            System.out.println("Zona normale: " + (numerimi - numerRezervash));
        }

        private static void ruajNeSkedar() {
            try (FileWriter fw = new FileWriter(EMRI_SKEDARIT)) {

                fw.write("------------------------------------------------------------------------------------------------\n");
                fw.write(String.format("%-12s %-12s %-12s %-12s %-10s %-12s %-10s%n",
                        "Zona", "Konsum", "Prodhim", "Kapacitet", "Prioritet", "Status", "Rezervë"));
                fw.write("------------------------------------------------------------------------------------------------\n");

                for (int i = 0; i < numerimi; i++) {
                    fw.write(String.format("%-12s %-12s %-12s %-12s %-10s %-12s %-10s%n",
                            zonat[i][0],
                            zonat[i][1],
                            zonat[i][2],
                            zonat[i][3],
                            zonat[i][4],
                            zonat[i][5],
                            zonat[i][6]));
                }

                System.out.println("Të dhënat u ruajtën në raport-energji.txt");

            } catch (IOException e) {
                System.out.println("Gabim gjatë shkrimit në skedar");
            }
        }

        private static int lexoIntSigurt(Scanner sc) {
            while (true) {
                try {
                    return sc.nextInt();
                } catch (InputMismatchException e) {
                    sc.nextLine();
                    System.out.print("Shkruaj një numër të vlefshëm: ");
                }
            }
        }

        private static double lexoDoubleSigurt(Scanner sc) {
            while (true) {
                try {
                    return sc.nextDouble();
                } catch (InputMismatchException e) {
                    sc.nextLine();
                    System.out.print("Shkruaj një numër të vlefshëm: ");
                }
            }
        }
    }



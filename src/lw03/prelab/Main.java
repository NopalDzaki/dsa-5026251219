package lw03.prelab;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        problem1();
        System.out.println();
        problem2();
        System.out.println();
        problem3();
    }

    private static void problem1() {
        System.out.println("===== Problem 1 =====");
        List<String> laguLagu = new ArrayList<>();
        
        try {
            File fileTxt = new File("src/lw03/prelab/playlist.txt");
            Scanner sc = new Scanner(fileTxt);
            
            while (sc.hasNextLine()) {
                String inputLagu = sc.nextLine().trim();
                if (inputLagu.equals("")) {
                    continue;
                }
                
                int spasiPertama = inputLagu.indexOf(" ");
                String aksi = inputLagu.substring(0, spasiPertama);
                String detail = inputLagu.substring(spasiPertama + 1);
                
                if (aksi.equals("ADD")) {
                    laguLagu.add(detail);
                } 
                else if (aksi.equals("INSERT")) {
                    int spasiKedua = detail.indexOf(" ");
                    int posisi = Integer.parseInt(detail.substring(0, spasiKedua));
                    String judul = detail.substring(spasiKedua + 1);
                    laguLagu.add(posisi, judul);
                } 
                else if (aksi.equals("REMOVE")) {
                    laguLagu.remove(detail);
                }
            }
            sc.close();
        } catch (Exception e) {
        }

        System.out.println("Total songs: " + laguLagu.size());
        for (int i = 0; i < laguLagu.size(); i++) {
            int no = i + 1;
            System.out.println(no + ": " + laguLagu.get(i));
        }
    }

    private static void problem2() {
        System.out.println("===== Problem 2 =====");
        Set<String> setPeserta = new LinkedHashSet<>();
        int jumlahDuplikat = 0;
        
        try {
            File f = new File("src/lw03/prelab/participants.txt");
            Scanner baca = new Scanner(f);
            
            while (baca.hasNextLine()) {
                String nm = baca.nextLine().trim();
                if (nm.length() == 0) {
                    continue;
                }
                
                boolean tambah = setPeserta.add(nm);
                if (tambah == false) {
                    jumlahDuplikat = jumlahDuplikat + 1;
                }
            }
            baca.close();
        } catch (Exception err) {
        }

        System.out.println("Unique participants: " + setPeserta.size());
        int nomor = 1;
        for (String org : setPeserta) {
            System.out.println(nomor + ". " + org);
            nomor++;
        }
        System.out.println("Duplicate registrations: " + jumlahDuplikat);
    }

    private static void problem3() {
        System.out.println("===== Problem 3 =====");
        Map<String, Integer> mapStok = new LinkedHashMap<>();
        int totalGagal = 0;
        
        try {
            Scanner masuk = new Scanner(new File("src/lw03/prelab/inventory.txt"));
            
            while (masuk.hasNextLine()) {
                String lineData = masuk.nextLine().trim();
                if (lineData.isEmpty()) {
                    continue;
                }
                
                String[] arr = lineData.split(" ");
                String perintah = arr[0];
                String nmBarang = arr[1];
                int banyak = Integer.parseInt(arr[2]);

                if (perintah.equals("ADD")) {
                    if (mapStok.containsKey(nmBarang)) {
                        int stokLama = mapStok.get(nmBarang);
                        mapStok.put(nmBarang, stokLama + banyak);
                    } else {
                        mapStok.put(nmBarang, banyak);
                    }
                } 
                else if (perintah.equals("SELL")) {
                    if (mapStok.containsKey(nmBarang) == false) {
                        totalGagal++;
                    } else {
                        int stokSkrg = mapStok.get(nmBarang);
                        if (stokSkrg < banyak) {
                            totalGagal++;
                        } else {
                            mapStok.put(nmBarang, stokSkrg - banyak);
                        }
                    }
                }
            }
            masuk.close();
        } catch (Exception exception) {
        }

        for (Map.Entry<String, Integer> elemen : mapStok.entrySet()) {
            System.out.println(elemen.getKey() + ": " + elemen.getValue());
        }
        System.out.println("Failed sales: " + totalGagal);
    }
}

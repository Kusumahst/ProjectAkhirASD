package ProjectAkhir;

import java.time.LocalDate;
import java.util.*;

class Node {
    Film data;
    Node next;

    public Node(Film data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    public void tambahFilm(Film data) {
        Node baru = new Node(data);
        if (head == null) {
            head = baru;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = baru;
        }
    }

    public void hapusFilm(String judul) {
        if (head == null) {
            System.out.println("Daftar film kosong. Tidak ada yang dapat dihapus.");
            return;
        }

        if (head.data != null && head.data.judul.equalsIgnoreCase(judul)) {
            System.out.println("Film \"" + head.data.judul + "\" berhasil dihapus.");
            head = head.next;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data != null && temp.next.data.judul.equalsIgnoreCase(judul)) {
                System.out.println("Film \"" + temp.next.data.judul + "\" berhasil dihapus.");
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }

        // Jika film tidak ditemukan
        System.out.println("Film dengan judul \"" + judul + "\" tidak ditemukan.");
    }

    public void tampilkanFilm() {
        Node temp = head;
        if (temp == null) {
            System.out.println("Tidak ada film.");
            return;
        }
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public void tampilkanFilmBelumTayang() {
        Node temp = head;
        boolean found = false;

        while (temp != null) {
            if (LocalDate.parse(temp.data.tanggalTayang).isAfter(LocalDate.now())) {
                System.out.println(temp.data);
                found = true;
            }
            temp = temp.next;
        }

        if (!found) {
            System.out.println("Tidak ada film yang belum tayang.");
        }
    }

    public void urutkanAscending(String kriteria) {
        if (head == null || head.next == null)
            return;

        boolean swapped;
        do {
            swapped = false;
            Node temp = head;

            while (temp.next != null) {
                Node berikutnya = temp.next;
                boolean perluTukar = false;

                switch (kriteria) {
                    case "harga":
                        if (temp.data.hargaTiket > berikutnya.data.hargaTiket) {
                            perluTukar = true;
                        }
                        break;
                    case "rating":
                        if (temp.data.rating > berikutnya.data.rating) {
                            perluTukar = true;
                        }
                        break;
                    case "tanggal":
                        if (temp.data.tanggalTayang.compareTo(berikutnya.data.tanggalTayang) > 0) {
                            perluTukar = true;
                        }
                        break;
                }

                if (perluTukar) {
                    Film tempData = temp.data;
                    temp.data = berikutnya.data;
                    berikutnya.data = tempData;
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);
    }

    public void urutkanDescending(String kriteria) {
        if (head == null || head.next == null)
            return;

        boolean swapped;
        do {
            swapped = false;
            Node temp = head;

            while (temp.next != null) {
                Node berikutnya = temp.next;
                boolean perluTukar = false;

                switch (kriteria) {
                    case "harga":
                        if (temp.data.hargaTiket < berikutnya.data.hargaTiket) {
                            perluTukar = true;
                        }
                        break;
                    case "rating":
                        if (temp.data.rating < berikutnya.data.rating) {
                            perluTukar = true;
                        }
                        break;
                    case "tanggal":
                        if (temp.data.tanggalTayang.compareTo(berikutnya.data.tanggalTayang) < 0) {
                            perluTukar = true;
                        }
                        break;
                }

                if (perluTukar) {
                    Film tempData = temp.data;
                    temp.data = berikutnya.data;
                    berikutnya.data = tempData;
                    swapped = true;
                }
                temp = temp.next;
            }
        } while (swapped);
    }

    public Film cariFilm(String judul) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.judul.equalsIgnoreCase(judul)) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }
}

class Film {
    String judul;
    double hargaTiket;
    double rating;
    String tanggalTayang;

    public Film(String judul, double hargaTiket, double rating, String tanggalTayang) {
        this.judul = judul;
        this.hargaTiket = hargaTiket;
        this.rating = rating;
        this.tanggalTayang = tanggalTayang;
    }

    @Override
    public String toString() {
        return "Judul          : " + judul + "\nHarga Tiket    : Rp" + hargaTiket + "\nRating         : " + rating
                + "\nTanggal Tayang : " + tanggalTayang + "\n";
    }
}

public class Plotify {
    public static void main(String[] args) {
        LinkedList daftarFilm = new LinkedList();
        daftarFilm.tambahFilm(new Film("Moana 2", 60000, 0, "2024-11-27"));
        daftarFilm.tambahFilm(new Film("Wicked", 55000, 8.2, "2024-11-22"));
        daftarFilm.tambahFilm(new Film("Venom: The Last Dance", 75000, 6.3, "2024-10-23"));
        daftarFilm.tambahFilm(new Film("Red One", 50000, 6.9, "2024-11-06"));
        daftarFilm.tambahFilm(new Film("Bila Esok Ibu Tiada", 45000, 6.4, "2024-11-14"));
        daftarFilm.tambahFilm(new Film("Gladiotor II", 60000, 7.0, "2024-11-13"));
        daftarFilm.tambahFilm(new Film("Gangnam Zombie", 65000, 0, "2024-11-29"));

        Scanner scanner = new Scanner(System.in);
        String username, password;

        System.out.println("=================================");
        System.out.println("|            PLOTIFY            |");
        System.out.println("=================================");
        System.out.println("Silahkan login untuk melanjutkan:");
        System.out.println("---------------------------------");
        System.out.print("Username : ");
        username = scanner.nextLine();
        System.out.print("Password : ");
        password = scanner.nextLine();
        System.out.println("---------------------------------\n");

        boolean isAdmin = username.equals("admin") && password.equals("admin123");
        boolean isUser = !isAdmin;

        if (isAdmin) {
            System.out.println("Selamat datang, " + username + "!");
        } else if (isUser) {
            System.out.println("Selamat datang, " + username + "!");
        } else {
            System.out.println("Username atau password salah!");
        }

        while (true) {
            if (isAdmin) {
                System.out.println("=================================");
                System.out.println("|           MENU ADMIN          |");
                System.out.println("=================================");
                System.out.println("| 1. Tambah Film                |");
                System.out.println("| 2. Hapus Film                 |");
                System.out.println("| 3. Lihat Daftar Film          |");
                System.out.println("| 4. Sorting Film               |");
                System.out.println("| 5. Cari Film                  |");
                System.out.println("| 6. Keluar                     |");
                System.out.println("=================================");
                System.out.print("Silakan pilih menu [1-6]: ");
                int pilihanAdmin = scanner.nextInt();
                scanner.nextLine();

                switch (pilihanAdmin) {
                    case 1:
                        System.out.println("\n=================================");
                        System.out.println("|          TAMBAH FILM          |");
                        System.out.println("=================================");
                        System.out.print("Judul film  : ");
                        String judul = scanner.nextLine();
                        System.out.print("Harga tiket : ");
                        double harga = scanner.nextDouble();
                        System.out.print("Rating film : ");
                        double rating = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Tanggal tayang (YYYY-MM-DD): ");
                        String tanggal = scanner.nextLine();
                        daftarFilm.tambahFilm(new Film(judul, harga, rating, tanggal));
                        System.out.println("---------------------------------");
                        System.out.println("Film berhasil ditambahkan");
                        System.out.println("---------------------------------\n");
                        break;
                    case 2:
                        System.out.println("\n=================================");
                        System.out.println("|           HAPUS FILM           |");
                        System.out.println("=================================");
                        System.out.print("Judul Film : ");
                        String hapusJudul = scanner.nextLine();
                        daftarFilm.hapusFilm(hapusJudul);
                        System.out.println("---------------------------------");
                        System.out.println("Film berhasil dihapus");
                        System.out.println("---------------------------------\n");
                        break;
                    case 3:
                        System.out.println("=================================");
                        System.out.println("|          DAFTAR FILM          |");
                        System.out.println("=================================");
                        daftarFilm.tampilkanFilm();
                        break;
                    case 4:
                        boolean kembaliSorting = false;
                        while (!kembaliSorting) {
                            System.out.println("\n=================================");
                            System.out.println("|          MENU SORTING         |");
                            System.out.println("=================================");
                            System.out.println("| 1. Harga Tiket                |");
                            System.out.println("| 2. Rating                     |");
                            System.out.println("| 3. Tanggal Tayang             |");
                            System.out.println("| 4. Kembali                    |");
                            System.out.println("=================================");
                            System.out.print("Silakan pilih menu [1-4]: ");
                            int sortingUser = scanner.nextInt();
                            scanner.nextLine();

                            switch (sortingUser) {
                                case 1:
                                    System.out.println("\n=================================");
                                    System.out.println("|           HARGA TIKET         |");
                                    System.out.println("=================================");
                                    System.out.println("| 1. Termurah                   |");
                                    System.out.println("| 2. Termahal                   |");
                                    System.out.println("=================================");
                                    System.out.print("Silakan pilih menu [1-2]: ");
                                    int pilihanHarga = scanner.nextInt();
                                    scanner.nextLine();

                                    if (pilihanHarga == 1) {
                                        daftarFilm.urutkanAscending("harga");
                                    } else if (pilihanHarga == 2) {
                                        daftarFilm.urutkanDescending("harga");
                                    } else {
                                        System.out.println("Pilihan tidak valid!");
                                    }

                                    System.out.println("\n=================================");
                                    System.out.println("|          DAFTAR FILM          |");
                                    System.out.println("=================================");
                                    daftarFilm.tampilkanFilm();
                                    break;
                                case 2:
                                    System.out.println("\n=================================");
                                    System.out.println("|          RATING FILM          |");
                                    System.out.println("=================================");
                                    System.out.println("| 1. Terendah                   |");
                                    System.out.println("| 2. Tertinggi                  |");
                                    System.out.println("=================================");
                                    System.out.print("Silakan pilih menu [1-2]: ");
                                    int pilihanRating = scanner.nextInt();
                                    scanner.nextLine();
                                    if (pilihanRating == 1) {
                                        daftarFilm.urutkanAscending("rating");
                                    } else if (pilihanRating == 2) {
                                        daftarFilm.urutkanDescending("rating");
                                    } else {
                                        System.out.println("Pilihan tidak valid!");
                                    }

                                    System.out.println("\n=================================");
                                    System.out.println("|          DAFTAR FILM          |");
                                    System.out.println("=================================");
                                    daftarFilm.tampilkanFilm();
                                    break;
                                case 3:
                                    System.out.println("\n=================================");
                                    System.out.println("|          DAFTAR FILM          |");
                                    System.out.println("=================================");
                                    daftarFilm.tampilkanFilm();
                                    daftarFilm.urutkanAscending("tanggal");
                                    break;
                                case 4:
                                    kembaliSorting = true;
                                    break;
                                default:
                                    System.out.println("Pilihan tidak valid.");
                            }
                        }
                        break;
                    case 5:
                        System.out.println("\n=================================");
                        System.out.println("|           CARI FILM           |");
                        System.out.println("=================================");
                        System.out.print("Judul film yang dicari : ");
                        String cariJudulAdmin = scanner.nextLine();
                        Film hasilCariAdmin = daftarFilm.cariFilm(cariJudulAdmin);
                        System.out.println("---------------------------------");
                        if (hasilCariAdmin != null) {
                            System.out.println(hasilCariAdmin);
                            System.out.println("---------------------------------");
                        } else {
                            System.out.println("Film dengan judul '" + cariJudulAdmin + "' tidak ditemukan.");
                            System.out.println("---------------------------------\n");
                        }
                        break;
                    case 6:
                        System.out.println("\nTerima kasih, Admin!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } else if (isUser) {
                System.out.println("=================================");
                System.out.println("|         MENU PENGGUNA         |");
                System.out.println("=================================");
                System.out.println("| 1. Lihat Daftar Film          |");
                System.out.println("| 2. Film yang Belum Tayang     |");
                System.out.println("| 3. Sorting Film               |");
                System.out.println("| 4. Cari Film                  |");
                System.out.println("| 5. Keluar                     |");
                System.out.println("=================================");
                System.out.print("Silakan pilih menu [1-5]: ");
                int pilihanUser = scanner.nextInt();
                scanner.nextLine();

                switch (pilihanUser) {
                    case 1:
                        System.out.println("\n=================================");
                        System.out.println("|          DAFTAR FILM          |");
                        System.out.println("=================================");
                        daftarFilm.tampilkanFilm();
                        break;
                    case 2:
                        System.out.println("\n=================================");
                        System.out.println("|          DAFTAR FILM          |");
                        System.out.println("=================================");
                        daftarFilm.tampilkanFilmBelumTayang();
                        break;
                    case 3:
                        boolean kembaliSorting = false;
                        while (!kembaliSorting) {
                            System.out.println("\n=================================");
                            System.out.println("|          MENU SORTING         |");
                            System.out.println("=================================");
                            System.out.println("| 1. Harga Tiket                |");
                            System.out.println("| 2. Rating                     |");
                            System.out.println("| 3. Tanggal Tayang             |");
                            System.out.println("| 4. Kembali                    |");
                            System.out.println("=================================");
                            System.out.print("Silakan pilih menu [1-4]: ");
                            int sortingUser = scanner.nextInt();
                            scanner.nextLine();

                            switch (sortingUser) {
                                case 1:
                                    System.out.println("\n=================================");
                                    System.out.println("|           HARGA TIKET         |");
                                    System.out.println("=================================");
                                    System.out.println("| 1. Termurah                   |");
                                    System.out.println("| 2. Termahal                   |");
                                    System.out.println("=================================");
                                    System.out.print("Silakan pilih menu [1-2]: ");
                                    int pilihanHarga = scanner.nextInt();
                                    scanner.nextLine();

                                    if (pilihanHarga == 1) {
                                        daftarFilm.urutkanAscending("harga");
                                    } else if (pilihanHarga == 2) {
                                        daftarFilm.urutkanDescending("harga");
                                    } else {
                                        System.out.println("Pilihan tidak valid!");
                                    }

                                    System.out.println("\n=================================");
                                    System.out.println("|          DAFTAR FILM          |");
                                    System.out.println("=================================");
                                    daftarFilm.tampilkanFilm();
                                    break;
                                case 2:
                                    System.out.println("\n=================================");
                                    System.out.println("|          RATING FILM          |");
                                    System.out.println("=================================");
                                    System.out.println("| 1. Terendah                   |");
                                    System.out.println("| 2. Tertinggi                  |");
                                    System.out.println("=================================");
                                    System.out.print("Silakan pilih menu [1-2]: ");
                                    int pilihanRating = scanner.nextInt();
                                    scanner.nextLine();
                                    if (pilihanRating == 1) {
                                        daftarFilm.urutkanAscending("rating");
                                    } else if (pilihanRating == 2) {
                                        daftarFilm.urutkanDescending("rating");
                                    } else {
                                        System.out.println("Pilihan tidak valid!");
                                    }

                                    System.out.println("\n=================================");
                                    System.out.println("|          DAFTAR FILM          |");
                                    System.out.println("=================================");
                                    daftarFilm.tampilkanFilm();
                                    break;
                                case 3:
                                    System.out.println("\n=================================");
                                    System.out.println("|          DAFTAR FILM          |");
                                    System.out.println("=================================");
                                    daftarFilm.urutkanAscending("tanggal");
                                    daftarFilm.tampilkanFilm();
                                    break;
                                case 4:
                                    kembaliSorting = true;
                                    break;
                                default:
                                    System.out.println("Pilihan tidak valid.");
                            }
                        }
                        break;
                    case 4:
                        System.out.println("\n=================================");
                        System.out.println("|           CARI FILM           |");
                        System.out.println("=================================");
                        System.out.print("Judul film yang dicari : ");
                        String cariJudulUser = scanner.nextLine();
                        Film hasilCariUser = daftarFilm.cariFilm(cariJudulUser);
                        System.out.println("---------------------------------");
                        if (hasilCariUser != null) {
                            System.out.println(hasilCariUser);
                            System.out.println("---------------------------------");
                        } else {
                            System.out.print("Film dengan judul '" + cariJudulUser + "' tidak ditemukan.");
                            System.out.println("---------------------------------\n");
                        }
                        break;
                    case 5:
                        System.out.println("\nTerima kasih, " + username + "!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            }
        }
    }
}
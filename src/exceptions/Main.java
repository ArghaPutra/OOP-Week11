package exceptions;

import exceptions.AuthenticationException;
import exceptions.ExcessiveFailLoginException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<User> listOfUser = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initialize(); // 1. Menambahkan user default (John Doe)

        while (true) {
            System.out.println("\nMenu Utama");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.print("Pilihan : ");

            String input = scanner.nextLine();

            if (input.equals("1")) {
                handleLogin();
            } else if (input.equals("2")) {
                handleSignUp();
            } else {
                System.out.println("Pilihan tidak valid.");
            }
        }
    }

    // Soal No 1: Method initialize
    public static void initialize() {
        // Menambahkan user default sesuai tabel
        User defaultUser = new User(
                "John",
                "Doe",
                'L',
                "Jl. Merpati No. 1 RT 1 RW 1, Banten",
                "admin",
                "admin"
        );
        listOfUser.add(defaultUser);
    }

    // Soal No 2: Method handleLogin
    public static void handleLogin() {
        System.out.println("\nMenu Login");
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.print("Pilihan : 1"); // Hardcoded visual sesuai gambar, tapi logika tetap jalan
        System.out.println(); // Newline

        System.out.print("Username : ");
        String username = scanner.nextLine();
        System.out.print("Password : ");
        String password = scanner.nextLine();

        boolean isLoginSuccess = false;

        try {
            for (User user : listOfUser) {
                // Cek apakah user bisa login
                // Method login() di class User akan melempar ExcessiveFailedLoginException jika terkunci
                if (user.login(username, password)) {
                    System.out.println(user.greeting());
                    isLoginSuccess = true;
                    break;
                }
            }

            // Jika loop selesai dan tidak ada yang sukses login (tapi tidak terkunci)
            if (!isLoginSuccess) {
                throw new AuthenticationException("Username / password salah");
            }

        } catch (ExcessiveFailLoginException e) {
            System.out.println(e.getMessage());
        } catch (AuthenticationException e) {
            System.out.println(e.getMessage());
        }
    }

    // Soal No 3: Method handleSignUp
    public static void handleSignUp() {
        System.out.println("\nMenu Lihat Sign Up"); // Sesuai judul di gambar
        System.out.println("1. Login");
        System.out.println("2. Sign Up");
        System.out.print("Pilihan : 2");
        System.out.println();

        System.out.print("Nama Depan : ");
        String firstName = scanner.nextLine();

        System.out.print("Nama Belakang : ");
        String lastName = scanner.nextLine();

        System.out.print("Jenis Kelamin (L/P) : ");
        char gender = scanner.nextLine().charAt(0);

        System.out.print("Alamat : ");
        String address = scanner.nextLine();

        System.out.print("Username : ");
        String username = scanner.nextLine();

        // Validasi Username (Harus > 8 karakter sesuai gambar error)
        if (username.length() <= 8) {
            System.out.println("Username harus lebih dari 8 karakter");
            return; // Kembali ke menu utama
        }

        System.out.print("Password : ");
        String password = scanner.nextLine();


        if (!password.matches("^(?=.*[0-9])(?=.*[A-Z]).{6,16}$")) {
            System.out.println("Password harus mengandung huruf besar, angka, minimum 6 karakter dan maksimum 16 karakter");
            return; // Kembali ke menu utama
        }

        // Jika lolos semua validasi, buat user baru
        User newUser = new User(firstName, lastName, gender, address, username, password);
        listOfUser.add(newUser);

        System.out.println("User telah berhasil didaftarkan");
    }
}
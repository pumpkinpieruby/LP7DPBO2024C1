# Flappy Bird Game
## JANJI
Saya Septiani Eka Putri NIM 2206000 mengerjakan Latihan Praktikum 7 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

## Deskripsi
Ini adalah implementasi sederhana dari permainan Flappy Bird menggunakan Java Swing. Dalam permainan ini, pemain mengendalikan burung untuk melewati pipa-pipa yang muncul dengan cara melompat.

## Desain Program
- Program terdiri dari beberapa kelas utama: `App`, `FlappyBird`, `Player`, dan `Pipe`.
- Kelas `App` adalah kelas utama yang menampung metode `main` untuk memulai permainan.
- Kelas `FlappyBird` adalah kelas yang mengatur logika permainan, seperti menggambar elemen-elemen permainan, mengatur pergerakan burung, dan menangani tabrakan.
- Kelas `Player` merepresentasikan burung yang dikendalikan oleh pemain.
- Kelas `Pipe` merepresentasikan pipa-pipa yang harus dilewati oleh burung.

## Alur Permainan
1. Pemain memulai permainan dengan menekan tombol spasi untuk melompat.
2. Burung akan terus bergerak ke bawah karena adanya gaya gravitasi, kecuali jika pemain menekan tombol spasi untuk melompat.
3. Pipa-pipa akan muncul dari sisi kanan layar ke kiri secara periodik.
4. Pemain harus menghindari tabrakan dengan pipa-pipa dengan cara melompat untuk melewati celah antara pipa-pipa.
5. Setiap kali pemain berhasil melewati sepasang pipa, skor akan bertambah satu.
6. Permainan berakhir jika burung menabrak pipa atau menabrak tanah.
7. Setelah permainan berakhir, pemain dapat menekan tombol "R" untuk memulai ulang permainan.

## Cara Menjalankan
1. Pastikan Anda memiliki Java terinstal di sistem Anda.
2. Kompilasilah semua file `.java` menggunakan perintah `javac`.
    ```
    javac *.java
    ```
3. Jalankan kelas `App`.
    ```
    java App
    ```

## Kontrol Permainan
- Tekan tombol **Spasi** untuk melompat.
- Tekan **R** untuk merestart permainan setelah Game Over.

## Dokumentasi
- `App.java`: Kelas utama untuk memulai permainan.
- `FlappyBird.java`: Kelas untuk logika permainan dan komponen-komponen Swing.
- `Player.java`: Kelas yang merepresentasikan pemain (burung).
- `Pipe.java`: Kelas yang merepresentasikan pipa-pipa.
- `assets/`: Direktori yang berisi gambar-gambar yang digunakan dalam permainan.

## Kredit
Permainan ini dikembangkan oleh [Nama Anda] sebagai proyek sederhana menggunakan Java Swing.

## Dokumentasi 
![dokum](<Screenshot 2024-04-27 201630-1.png>)
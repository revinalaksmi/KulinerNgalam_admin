# Kuliner Ngalam (Admin Version)
| Grup | 14 |
| :---------------:   | :---------------:|
| Alfinda Rahmadiarni | 03 |
| Revina Laksmi P H   | 25 |

## Deskripsi
Kuliner Ngalam adalah sebuah aplikasi berbasis android yang berisi data review makanan atau kuliner yang ada di Kota Malang. Dengan aplikasi ini, user dapat melihat menu-menu hits yang ada di Kota Malang beserta keterangan seperti alamat, harga, dan review makanannya. 

## Screenshots
https://drive.google.com/open?id=1j9TDHkh5ImopnI7gqUuU4x-Lq4CoOO_M

## Android OS dan Level
min Sdk version yang digunakan adalah 22

## List class
+ Folder Adapter => terdiri dari MakananAdapter.java, UserAdapter.java, dan SukaAdapter.java
- Berfungsi untuk menampilkan data dari database ke recyclerview
+ Folder Model => terdiri dari Makanan.java, GetMakanan.java, User.java, GetUser.java, Suka.java, dan GetSuka.java
- Berfungsi untuk menampung data dari database
+ Folder Rest => terdiri dari ApiClient.java dan ApiInterface.java
- Kelas ApiClient.java berfungsi untuk mengkoneksikan android ke rest-server, sedangkan ApiInterface.java berisi metode-metode yang digunakan dalam komunikasi data dengan rest-server (CRUD) 
+ MainActivity.java berfungsi untuk autentikasi login admin
+ LayarInsertMakanan.java berfungsi untuk menambahkan data review makanan
+ LayarListMakanan.java untuk menampilkan seluruh data review makanan
+ LayarEditMakanan.java untuk mengedit atau menghapus data review makanan
+ LayarInsertUser.java berfungsi untuk menambahkan data user
+ LayarListUser.java untuk menampilkan seluruh data user
+ LayarEditUser.java untuk mengedit atau menghapus data user
+ LayarInsertSuka.java berfungsi untuk menambahkan data suka
+ LayarListSuka.java untuk menampilkan seluruh data suka
+ LayarEditSuka.java untuk mengedit atau menghapus data suka
+ OpsiMenu.java untuk menampilkan menu yang berada di kanan atas

## Referensi
* [Rest-Server](https://github.com/chriskacerguis/codeigniter-restserver) - Rest Server
* [Github](https://github.com/) - Untuk Kolaborasi proyek

## Lisensi
MIT License

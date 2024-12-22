# Aplikasi Manajemen Keuangan Mahasiswa

Aplikasi ini adalah program berbasis Java yang dibuat menggunakan library `Swing`. Tujuannya adalah untuk membantu mahasiswa dalam mengelola keuangan bulanan mereka. Aplikasi ini memungkinkan pengguna untuk mencatat pemasukan awal, pengeluaran berdasarkan kategori, melihat laporan bulanan, dan menghitung sisa pemasukan.

## Fitur Utama

1. **Input Pemasukan Awal**: Memasukkan jumlah uang yang dimiliki sebagai pemasukan awal.
2. **Input Pengeluaran**:
   - Memasukkan jumlah pengeluaran.
   - Memilih atau menambahkan kategori pengeluaran.
   - Memilih bulan pengeluaran.
3. **Laporan Bulanan**:
   - Menampilkan total pengeluaran untuk bulan tertentu.
   - Mengelompokkan pengeluaran berdasarkan kategori.
   - Menampilkan sisa pemasukan setelah dikurangi total pengeluaran.
4. **Pencatatan Per Bulan**: Data pengeluaran dikelompokkan berdasarkan bulan.

## Teknologi yang Digunakan

- **Bahasa Pemrograman**: Java
- **Framework GUI**: Swing
- **Library**: `javax.swing`, `java.text.NumberFormat`, `java.awt`

## Cara Kerja Aplikasi

1. **Memasukkan Pemasukan Awal**:  
   Pengguna mengisi jumlah pemasukan awal dan menekan tombol "Set Pemasukan". Pemasukan awal akan digunakan sebagai acuan untuk menghitung sisa pemasukan.

2. **Mencatat Pengeluaran**:  
   - Pengguna memasukkan jumlah uang yang dikeluarkan.  
   - Pilih kategori pengeluaran dari daftar yang tersedia atau masukkan kategori baru.  
   - Pilih bulan untuk pengeluaran tersebut.  
   - Tekan tombol "Tambah Catatan" untuk menyimpan pengeluaran.  

3. **Melihat Data Pengeluaran**:  
   Tabel di sisi kanan akan diperbarui sesuai dengan bulan yang dipilih pada dropdown. Tabel menampilkan rincian pengeluaran, termasuk jumlah uang, kategori, dan bulan.

4. **Melihat Laporan Bulanan**:  
   Pengguna dapat menekan tombol "Lihat Laporan" untuk melihat ringkasan pengeluaran bulan tersebut. Laporan mencakup total pengeluaran berdasarkan kategori, total keseluruhan pengeluaran, dan sisa pemasukan.

## Cara Menjalankan Program

1. Pastikan **Java JDK** telah terinstal pada komputer Anda.
2. Simpan kode program ke dalam file bernama `FinanceManagerApp.java`.
3. Buka terminal atau command prompt, navigasikan ke direktori tempat file disimpan, lalu kompilasi program dengan perintah:
   ```bash
   javac FinanceManagerApp.java
   ```
4. Jalankan program dengan perintah:
   ```bash
   java FinanceManagerApp
   ```

## Struktur Kode

### 1. **`FinanceManagerApp`**
Kelas utama yang mengatur GUI dan logika aplikasi.

### 2. **Komponen GUI**
- **JFrame**: Mengelola jendela utama aplikasi.
- **JPanel**: Panel untuk mengelompokkan elemen antarmuka pengguna.
- **JTable**: Menampilkan data pengeluaran dalam tabel.
- **JComboBox**: Dropdown untuk memilih kategori atau bulan.
- **JTextField**: Input untuk memasukkan jumlah uang dan kategori kustom.
- **JLabel**: Menampilkan teks seperti total pengeluaran dan sisa pemasukan.
- **JButton**: Tombol untuk menginisialisasi aksi.

### 3. **Fungsi Utama**
- **`addRecord()`**: Menambahkan data pengeluaran ke tabel dan memori.
- **`updateTableForMonth()`**: Memperbarui tabel sesuai dengan bulan yang dipilih.
- **`showMonthlyReport()`**: Menampilkan laporan ringkasan pengeluaran per bulan.
- **`formatCurrency()`**: Memformat angka menjadi bentuk mata uang.
- **`parseCurrency()`**: Mengonversi teks mata uang menjadi angka.

## Tampilan Aplikasi

1. **Header**: Judul aplikasi.
2. **Panel Input**:  
   - Input pemasukan awal.  
   - Input pengeluaran, kategori, dan bulan.  
   - Tombol untuk menambahkan catatan dan melihat laporan.  
3. **Tabel Pengeluaran**: Menampilkan daftar pengeluaran untuk bulan tertentu.
4. **Panel Informasi**: Menampilkan total pengeluaran dan sisa pemasukan.

## Contoh Penggunaan

1. **Memasukkan Pemasukan Awal**:  
   Masukkan nilai, misalnya `1000000`, dan tekan "Set Pemasukan".

2. **Mencatat Pengeluaran**:  
   - Masukkan jumlah pengeluaran, misalnya `50000`.  
   - Pilih kategori, misalnya "Makanan".  
   - Pilih bulan, misalnya "Januari".  
   - Tekan "Tambah Catatan".  

3. **Melihat Laporan Bulanan**:  
   Pilih bulan "Januari", lalu tekan "Lihat Laporan". Laporan akan muncul dalam dialog.

## Validasi Input

- Input pemasukan awal dan pengeluaran harus berupa angka. Jika tidak, akan muncul pesan kesalahan di bawah form input.

## Catatan Tambahan

- Aplikasi ini hanya menyimpan data dalam memori selama program berjalan. Data tidak akan tersimpan secara permanen setelah aplikasi ditutup.
- Pengeluaran dapat dimasukkan ke bulan yang berbeda tanpa memengaruhi data bulan lain.

## Lisensi

Aplikasi ini dibuat untuk tujuan pembelajaran dan bebas digunakan oleh siapa saja.

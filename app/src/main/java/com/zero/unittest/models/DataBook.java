package com.zero.unittest.models;

import java.util.Date;

public class DataBook {
    int bookID;
    String title;
    String barcode;
    Boolean isBorrowed;
    int longPeriod;
    int fine;
    String studentID;
    String shelfID;
    Date tanggalPeminjaman;

    public DataBook(int bookID, String title, String barcode) {
        this.bookID = bookID;
        this.title = title;
        this.barcode = barcode;
        this.fine = 100;
        this.isBorrowed = false;
    }

    public DataBook(int bookID, String title, String barcode, boolean isBorrowed) {
        this.bookID = bookID;
        this.title = title;
        this.barcode = barcode;
        this.fine = 100;
        this.isBorrowed = isBorrowed;
    }

    public DataBook(int bookID, String title, String barcode, boolean isBorrowed, String studentID) {
        this.bookID = bookID;
        this.title = title;
        this.barcode = barcode;
        this.fine = 100;
        this.isBorrowed = isBorrowed;
        this.studentID = studentID;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Boolean getBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        isBorrowed = borrowed;
    }

    public int getLongPeriod() {
        return longPeriod;
    }

    public void setLongPeriod(int loadPeriod) {
        this.longPeriod = loadPeriod;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Date getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }

    public void setTanggalPeminjaman(Date tanggalPeminjaman) {
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    public String getShelfID() {
        return shelfID;
    }

    public void setShelfID(String shelfID) {
        this.shelfID = shelfID;
    }


}

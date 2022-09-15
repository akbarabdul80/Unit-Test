package com.zero.unittest.service.helper;


import com.zero.unittest.models.DataBook;
import com.zero.unittest.service.usecase.LibraryServiceImp;
import com.zero.unittest.utils.DateExt;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LibraryHelper {
    LibraryServiceImp service;

    public LibraryHelper(LibraryServiceImp service) {
        this.service = service;
    }

    public List<DataBook> searchBook(String keyword) {
        List<DataBook> allBooks = service.getAllBook();
        List<DataBook> result = new ArrayList<>();

        for (DataBook book : allBooks) {
            if (book.getTitle().contains(keyword)) {
                result.add(book);
            }
        }

        return result;
    }

    public void returnToShelf(DataBook book, String shelfID) {
        if (book.getShelfID().equals(shelfID)) {
            service.returnBookToShelf(book);
        }
    }

    public int getBookCount(int bookID) {
        List<DataBook> allBooks = service.getAllBook();
        int count = 0;
        for (DataBook book : allBooks) {
            if (book.getBookID() == bookID) {
                count++;
            }
        }
        return count;
    }

    public boolean isOverdue(String barcode, Date tglKembali) {
        DataBook book = service.findBook(barcode);
        Date tglPinjam = book.getTanggalPeminjaman();
        int longPeriod = book.getLongPeriod();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tglPinjam);
        calendar.add(Calendar.DATE, longPeriod);

        Date tglHarusKembali = calendar.getTime();
        return tglHarusKembali.before(tglKembali);
    }

    public int getTotalFine(String barcode, Date tglKembali) {
        DataBook book = service.findBook(barcode);
        Date tglPinjam = book.getTanggalPeminjaman();
        int longPeriod = book.getLongPeriod();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(tglPinjam);
        calendar.add(Calendar.DATE, longPeriod);

        Date tglHarusKembali = calendar.getTime();
        if (tglHarusKembali.before(tglKembali)) {
            int diffDays = DateExt.getDiffDays(tglHarusKembali, tglKembali);
            return (int) diffDays * book.getFine();
        }
        return 0;
    }

    public boolean isBookNeedToBuy(int bookID) {
        List<DataBook> allBooks = service.getAllBook();
        int count = 0;
        for (DataBook book : allBooks) {
            if (book.getBookID() == bookID && !book.getBorrowed()) {
                count++;
            }
        }
        return count == 0;
    }

    public DataBook borrowBook(int bookID) {
        List<DataBook> allBooks = service.getAllBook();
        for (DataBook book : allBooks) {
            if (book.getBookID() == bookID && !book.getBorrowed()) {
                service.borrowBook(book.getBarcode());
                return book;
            }
        }
        return null;
    }

    public void reminderStudent(Date tglKembali) {
        List<DataBook> allBooks = service.getAllBook();
        for (DataBook book : allBooks) {
            if (DateExt.getDiffDays(book.getTanggalPeminjaman(), tglKembali) >= 0) {
                service.sendMessageToStudent(book.getStudentID());
            }
        }
    }

}

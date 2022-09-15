package com.zero.unittest.service.helper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.zero.unittest.models.DataBook;
import com.zero.unittest.service.usecase.LibraryServiceImp;
import com.zero.unittest.utils.DateExt;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class LibraryHelperTest extends TestCase {

    public void testSearchBook() {
        LibraryServiceImp service = mock(LibraryServiceImp.class);
        when(service.getAllBook()).thenReturn(
                new ArrayList<>(List.of(
                        new DataBook(1, "Test Book", "1234567890"),
                        new DataBook(1, "Test Book", "1234567890"),
                        new DataBook(1, "Hello", "1234567890")
                ))
        );
        LibraryHelper helper = new LibraryHelper(service);
        assertEquals(2, helper.searchBook("Test").size());
    }

    public void testReturnToShelf() {
        LibraryServiceImp service = mock(LibraryServiceImp.class);
        LibraryHelper helper = new LibraryHelper(service);

        DataBook book = new DataBook(1, "Test Book", "1234567890");
        book.setShelfID("RAK-001");

        helper.returnToShelf(book, "RAK-001");

        verify(service).returnBookToShelf(book);
    }

    public void testGetBookCount() {
        LibraryServiceImp service = mock(LibraryServiceImp.class);
        when(service.getAllBook()).thenReturn(
                new ArrayList<>(List.of(
                        new DataBook(1, "Test Book", "1234567890"),
                        new DataBook(1, "Test Book", "123456780"),
                        new DataBook(2, "Hello", "1234590")
                ))
        );
        LibraryHelper helper = new LibraryHelper(service);
        assertEquals(2, helper.getBookCount(1));
    }

    public void testIsOverdue() {
        LibraryServiceImp service = mock(LibraryServiceImp.class);
        LibraryHelper helper = new LibraryHelper(service);

        DataBook book = new DataBook(1, "Test Book", "1234567890");
        book.setLongPeriod(7);
        book.setTanggalPeminjaman(DateExt.getDayAgo(8));

        when(service.findBook("1234567890")).thenReturn(book);

        assertTrue(helper.isOverdue(book.getBarcode(), DateExt.getToday()));
    }

    public void testTotalFine() {
        LibraryServiceImp service = mock(LibraryServiceImp.class);
        LibraryHelper helper = new LibraryHelper(service);

        DataBook book = new DataBook(1, "Test Book", "1234567890");
        book.setLongPeriod(7);
        book.setTanggalPeminjaman(DateExt.getDayAgo(9));
        book.setFine(300);

        when(service.findBook("1234567890")).thenReturn(book);

        assertEquals(600, helper.getTotalFine(book.getBarcode(), DateExt.getToday()));
    }

    public void testBookNeedToBuy() {
        LibraryServiceImp service = mock(LibraryServiceImp.class);
        LibraryHelper helper = new LibraryHelper(service);

        when(service.getAllBook()).thenReturn(
                new ArrayList<>(List.of(
                        new DataBook(1, "Test Book", "1234567890", true),
                        new DataBook(1, "Test Book", "1234567890", true),
                        new DataBook(1, "Hello", "1234567890", true)
                ))
        );

        assertTrue(helper.isBookNeedToBuy(1));
    }

    public void testBorrowBook() {
        LibraryServiceImp service = mock(LibraryServiceImp.class);
        LibraryHelper helper = new LibraryHelper(service);

        when(service.getAllBook()).thenReturn(
                new ArrayList<>(List.of(
                        new DataBook(1, "Test Book", "123890", true),
                        new DataBook(1, "Test Book", "12345890", true),
                        new DataBook(1, "Hello", "12345611890", false)
                ))
        );

        DataBook bookBorrow = helper.borrowBook(1);

        verify(service).borrowBook(bookBorrow.getBarcode());
    }

    public void testReminderStudent() {
        LibraryServiceImp service = mock(LibraryServiceImp.class);
        LibraryHelper helper = new LibraryHelper(service);

        DataBook book1 = new DataBook(1, "Test Book", "123890", true, "898732163812");
        book1.setLongPeriod(7);
        book1.setTanggalPeminjaman(DateExt.getDayAgo(9));
        book1.setFine(300);

        DataBook book2 = new DataBook(1, "Test Book", "12389320", true, "898732164322");
        book2.setLongPeriod(7);
        book2.setTanggalPeminjaman(DateExt.getDayAgo(8));
        book2.setFine(300);

        when(service.getAllBook()).thenReturn(
                new ArrayList<>(List.of(
                        book1,
                        book2
                ))
        );

        helper.reminderStudent(DateExt.getToday());

        verify(service).sendMessageToStudent("898732164322");
    }
}
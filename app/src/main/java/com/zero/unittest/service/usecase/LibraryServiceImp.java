package com.zero.unittest.service.usecase;

import com.zero.unittest.models.DataBook;

import java.util.List;

public class LibraryServiceImp implements LibraryServiceUseCase {
    @Override
    public List<DataBook> getAllBook() {
        return null;
    }

    @Override
    public void returnBookToShelf(DataBook book) {

    }

    @Override
    public void borrowBook(String barcode) {

    }

    @Override
    public DataBook findBook(String barcode) {
        return null;
    }

    @Override
    public void sendMessageToStudent(String studentID) {

    }
}

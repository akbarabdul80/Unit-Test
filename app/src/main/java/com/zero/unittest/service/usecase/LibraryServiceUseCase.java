package com.zero.unittest.service.usecase;


import com.zero.unittest.models.DataBook;

import java.util.List;

public interface LibraryServiceUseCase {
    List<DataBook> getAllBook();

    void returnBookToShelf(DataBook book);

    void borrowBook(String barcode);

    DataBook findBook(String barcode);

    void sendMessageToStudent(String studentID);
}

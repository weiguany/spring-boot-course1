package top.wgy.book.service;

import top.wgy.book.common.PageResult;
import top.wgy.book.dto.*;

import top.wgy.book.vo.BookVO;

public interface BookService {
    BookVO createBook(BookCreateDTO dto);
    BookVO updateBook(Long id, BookUpdateDTO dto);
    BookVO adjustStock(Long id, StockAdjustDTO dto);
    BookVO getBookById(Long id);
    boolean existsByIsbn(String isbn);
    PageResult<BookVO> pageQuery(BookPageQuery query);
    void deleteBook(Long id);
    BookVO restoreBook(Long id);
}
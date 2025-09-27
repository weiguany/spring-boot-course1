package top.wgy.book.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.wgy.book.common.PageResult;
import top.wgy.book.dto.*;
import top.wgy.book.service.BookService;
import top.wgy.book.vo.BookVO;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookVO> createBook(@RequestBody @Valid BookCreateDTO dto) {
        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookVO> updateBook(@PathVariable Long id, @RequestBody @Valid BookUpdateDTO dto) {
        return ResponseEntity.ok(bookService.updateBook(id, dto));
    }

    @PatchMapping("/{id}/stock/adjust")
    public ResponseEntity<BookVO> adjustStock(@PathVariable Long id, @RequestBody @Valid StockAdjustDTO dto) {
        return ResponseEntity.ok(bookService.adjustStock(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookVO> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/exists/isbn/{isbn}")
    public ResponseEntity<Map<String, Boolean>> existsIsbn(@PathVariable String isbn) {
        boolean exists = bookService.existsByIsbn(isbn);
        return ResponseEntity.ok(Collections.singletonMap("exists", exists));
    }

    @GetMapping("/page")
    public ResponseEntity<PageResult<BookVO>> page(BookPageQuery query) {
        return ResponseEntity.ok(bookService.pageQuery(query));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/restore")
    public ResponseEntity<BookVO> restoreBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.restoreBook(id));
    }
}
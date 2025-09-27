package top.wgy.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wgy.book.common.PageResult;
import top.wgy.book.dto.*;
import top.wgy.book.entity.Book;
import top.wgy.book.exception.NotFoundException;
import top.wgy.book.mapper.BookMapper;
import top.wgy.book.service.BookService;
import top.wgy.book.vo.BookVO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;

    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    @Override
    @Transactional
    public BookVO createBook(BookCreateDTO dto) {
        // 检查 ISBN 唯一性（仅未删除记录）
        if (dto.getIsbn() != null && existsByIsbn(dto.getIsbn())) {
            throw new IllegalArgumentException("ISBN 已存在");
        }
        Book book = new Book();
        BeanUtils.copyProperties(dto, book);
        bookMapper.insert(book);
        return toVO(book);
    }

    @Override
    @Transactional
    public BookVO updateBook(Long id, BookUpdateDTO dto) {
        Book book = bookMapper.selectById(id);
        if (book == null || book.getDeleted()  == 1) {
            throw new NotFoundException("图书未找到");
        }
        if (dto.getTitle() != null) book.setTitle(dto.getTitle());
        if (dto.getAuthor() != null) book.setAuthor(dto.getAuthor());
        if (dto.getCategory() != null) book.setCategory(dto.getCategory());
        if (dto.getStock() != null) book.setStock(dto.getStock());
        int rows = bookMapper.updateById(book);
        if (rows == 0) {
            throw new IllegalArgumentException("更新失败");
        }
        Book updated = bookMapper.selectById(id);
        return toVO(updated);
    }

    @Override
    @Transactional
    public BookVO adjustStock(Long id, StockAdjustDTO dto) {
        Book book = bookMapper.selectById(id);
        if (book == null || book.getDeleted() != null && book.getDeleted() == 1) {
            throw new NotFoundException("图书未找到");
        }
        int newStock = (book.getStock() == null ? 0 : book.getStock()) + dto.getAmount();
        if (newStock < 0) throw new IllegalArgumentException("库存不足");
        book.setStock(newStock);
        bookMapper.updateById(book);
        return toVO(book);
    }
    @Override
    public BookVO getBookById(Long id) {
        Book book = bookMapper.selectById(id);
        if (book == null || book.getDeleted() != null && book.getDeleted() == 1) {
            throw new NotFoundException("图书未找到");
        }
        return toVO(book);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        QueryWrapper<Book> qw = new QueryWrapper<>();
        qw.eq("isbn", isbn).eq("deleted", 0);
        Integer count = Math.toIntExact(bookMapper.selectCount(qw));
        return count != null && count > 0;
    }

    @Override
    public PageResult<BookVO> pageQuery(BookPageQuery query) {
        int pageNo = (query.getPageNo() == null) ? 0 : query.getPageNo();
        int pageSize = (query.getPageSize() == null) ? 10 : query.getPageSize();
        // MyBatis-Plus page starts from 1
        Page<Book> page = new Page<>(pageNo + 1L, pageSize);
        QueryWrapper<Book> qw = new QueryWrapper<>();
        qw.eq("deleted", 0);
        if (query.getTitle() != null && !query.getTitle().isEmpty()) {
            qw.like("title", query.getTitle());
        }
        if (query.getAuthor() != null && !query.getAuthor().isEmpty()) {
            qw.like("author", query.getAuthor());
        }
        if (query.getCategory() != null && !query.getCategory().isEmpty()) {
            qw.eq("category", query.getCategory());
        }
        Page<Book> res = bookMapper.selectPage(page, qw);
        List<BookVO> vos = res.getRecords().stream().map(this::toVO).collect(Collectors.toList());
        return new PageResult<>(res.getTotal(), vos);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookMapper.selectById(id);
        if (book == null) throw new NotFoundException("图书未找到");
        if (book.getDeleted() != null && book.getDeleted() == 1) return;
        // 逻辑删除：MyBatis-Plus @TableLogic 会在 update 时处理 deleted 字段
        book.setDeleted(1);
        bookMapper.updateById(book);
    }

    @Override
    @Transactional
    public BookVO restoreBook(Long id) {
        Book book = bookMapper.selectById(id);
        if (book == null) throw new NotFoundException("图书未找到");
        if (book.getDeleted() != null && book.getDeleted() == 0) return toVO(book);
        book.setDeleted(0);
        bookMapper.updateById(book);
        return toVO(book);
    }

    private BookVO toVO(Book book) {
        BookVO vo = new BookVO();
        vo.setId(book.getId());
        vo.setTitle(book.getTitle());
        vo.setAuthor(book.getAuthor());
        vo.setCategory(book.getCategory());
        vo.setStock(book.getStock());
        vo.setUpdateTime(book.getUpdateTime());
        return vo;
    }
}
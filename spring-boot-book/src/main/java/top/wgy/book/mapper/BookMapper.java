package top.wgy.book.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.wgy.book.entity.Book;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
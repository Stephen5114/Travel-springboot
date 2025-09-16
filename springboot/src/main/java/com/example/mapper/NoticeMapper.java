package com.example.mapper;

import com.example.entity.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NoticeMapper {
    List<Notice> selectAll(Notice notice);

    void insert(Notice notice);

    @Select("SELECT* FROM notice where noticename = #{noticename}")
    Notice selectByNoticename(String noticename);

    void updateByid(Notice notice);

    @Delete("DELETE FROM notice WHERE id = #{id}")
    void deleteById(Integer id);

    
}

package com.example.mapper;

import com.example.entity.Introduction;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IntroductionMapper {
    List<Introduction> selectAll(Introduction introduction);

    void insert(Introduction introduction);

    @Select("SELECT* FROM introduction where introductionname = #{introductionname}")
    Introduction selectByIntroductionname(String introductionname);

    void updateByid(Introduction introduction);

    @Delete("DELETE FROM introduction WHERE id = #{id}")
    void deleteById(Integer id);

    
}

package com.pinyougou.mapper;

import com.pinyougou.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * BrandMapper 数据访问接口
 * @date 2019-02-21 09:26:43
 * @version 1.0
 */
public interface BrandMapper extends Mapper<Brand>{
List<Brand>findAll(Brand brand);

    @Select("select id, name as text from tb_brand order by id asc")
    List<Map<String,Object>> findAllByIdAndName();
}
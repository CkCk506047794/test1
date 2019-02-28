package com.pinyougou.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.pojo.Specification;
import com.pinyougou.service.SpecificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/specification")
public class SpecificationController {
    @Reference(timeout = 10000)
    private SpecificationService specificationService;
    /** 多条件分页查询规格 */
    @GetMapping("/findByPage")
    public PageResult findByPage(Specification specification,
                                 Integer page, Integer rows){
        /** GET请求中文转码 */
        if (specification != null && StringUtils
                .isNoneBlank(specification.getSpecName())){
            try {
                specification.setSpecName(new String(specification
                        .getSpecName().getBytes("ISO8859-1"),"UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return specificationService.findByPage(specification,
                page, rows);
    }
    /** 查询所有的规格(id与specName) */
    @GetMapping("/findSpecList")
    public List<Map<String, Object>> findSpecList(){
        return specificationService.findAllByIdAndName();
    }
}

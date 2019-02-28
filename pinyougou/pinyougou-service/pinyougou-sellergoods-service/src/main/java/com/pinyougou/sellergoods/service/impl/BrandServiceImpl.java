package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.mapper.BrandMapper;
import com.pinyougou.pojo.Brand;
import com.pinyougou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 服务提供方实现品牌服务接口
 *
 * @author lee.siu.wah
 * @version 1.0
 * <p>File Created at 2019-02-16<p>
 */
@Service(interfaceName = "com.pinyougou.service.BrandService")
@Transactional
public class BrandServiceImpl implements BrandService{

    @Autowired
    private BrandMapper brandMapper;


    public void save(Brand brand) {
        brandMapper.insertSelective(brand);
    }


    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }


    public void delete(Serializable id) {

    }

//删除品牌
    public void deleteAll(Serializable[] ids) {
//        创建示范对象
        Example example=new Example(Brand.class);
//        创建条件对象
        Example.Criteria criteria=example.createCriteria();
//        添加in条件
        criteria.andIn("id", Arrays.asList(ids));
//        根据条件删除
        brandMapper.deleteByExample(example);
    }


    public Brand findOne(Serializable id) {
        return null;
    }


    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }


    public PageResult findByPage(Brand brand, int page, int rows) {
        try {
//开始分页
            PageInfo<Object> pageInfo = PageHelper.startPage(page, rows)
                    .doSelectPageInfo(new ISelect() {
                        @Override
                        public void doSelect() {
                            brandMapper.findAll(brand);
                        }
                    });
            return  new PageResult(pageInfo.getTotal(),pageInfo.getList());
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    /** 查询所有的品牌(id与name) */
    public List<Map<String, Object>> findAllByIdAndName(){
        try{
            return brandMapper.findAllByIdAndName();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
}

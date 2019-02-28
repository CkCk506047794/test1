package com.pinyougou.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.common.pojo.PageResult;
import com.pinyougou.mapper.TypeTemplateMapper;
import com.pinyougou.pojo.TypeTemplate;
import com.pinyougou.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Service(interfaceName = "com.pinyougou.service.TypeTemplateService")
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService {
    /** 注入数据访问接口代理对象 */
    @Autowired
    private TypeTemplateMapper typeTemplateMapper;
    @Override
    public void save(TypeTemplate typeTemplate) {
        try{
            // 判断类型模板对象中的属性是否有值，有值的话就生成到insert语句中
            // insert into 表 (name)
            typeTemplateMapper.insertSelective(typeTemplate);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void update(TypeTemplate typeTemplate) {
        try{
            // 判断类型模板对象中的属性是否有值，有值的话就生成到update语句中
            // update 表 set name = ? where id = ?
            typeTemplateMapper.updateByPrimaryKeySelective(typeTemplate);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    @Override
    public void delete(Serializable id) {
    }
    @Override
    public void deleteAll(Serializable[] ids) {
        try{
            // DELETE FROM tb_type_template WHERE ( id in ( ? , ? ) )
            // 创建示例对象
            Example example = new Example(TypeTemplate.class); // delete from tb_type_template
            // 创建条件对象
            Example.Criteria criteria = example.createCriteria();
            // 添加条件 id in (?,?,?)
            criteria.andIn("id", Arrays.asList(ids));
            // 条件删除
            typeTemplateMapper.deleteByExample(example);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
    @Override
    public TypeTemplate findOne(Serializable id) {
        return null;
    }
    @Override
    public List<TypeTemplate> findAll() {
        return null;
    }
    /** 分页查询类型模版 */
    @Override
    public PageResult findByPage(TypeTemplate typeTemplate, int page, int rows) {
        try{
            /** 开始分页 */
            PageInfo<TypeTemplate> pageInfo = PageHelper.startPage(page, rows)
                    .doSelectPageInfo( new ISelect() {
                        @Override
                        public void doSelect() {
                            typeTemplateMapper.findAll(typeTemplate);
                        }
                    });
            return new PageResult(pageInfo.getTotal(), pageInfo.getList());
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    }
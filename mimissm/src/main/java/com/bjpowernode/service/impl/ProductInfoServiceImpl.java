package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.ProductInfoExample;
import com.bjpowernode.pojo.vo.ProductInfoVo;
import com.bjpowernode.service.ProductInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    //切记:业务逻辑层中一定有数据访问层的对象
    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public List<ProductInfo> getAll() {
        //当前查询没有任何条件，不用创建 ProductInfoExample 对象添加条件。
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    //select * from product_info limit 起始记录数=((当前页-1)*每页的条数),每页取几条
    //select * from product_info limit 10,5
    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
        //使用PageHelper分页插件工具类完成分页设置。
        PageHelper.startPage(pageNum,pageSize);

        //进行PageInfo的数据封装
        //进行 有条件 的查询操作,必须要创建ProductInfoExample对象
        ProductInfoExample example = new ProductInfoExample();
        //条件：将查询的结果排序，按主键降序排序.
        //select * from product_info order by p_id desc
        example.setOrderByClause("p_id desc");
        //设置完排序后，取集合，切记切记：一定在取集合之前：使用PageHelper.startPage(pageNum,pageSize);才有分页的效果。
        List<ProductInfo> list = productInfoMapper.selectByExample(example);
        //将查询到的集合封装进PageInfo对象中。会自动将查询到的集合的各种数据（每页条数、每页显示多少条数、总条数...）封装进 PageInfo 对象。
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int save(ProductInfo info) {
        return productInfoMapper.insert(info);
    }

    @Override
    public ProductInfo getByID(int pid) {
        return productInfoMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int update(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);
    }

    @Override
    public int delete(int pid) {
        return productInfoMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public int deleteBatch(String[] ids) {
        return productInfoMapper.deleteBatch(ids);
    }

    @Override
    public List<ProductInfo> selectCondition(ProductInfoVo vo) {
        return productInfoMapper.selectCondition(vo);
    }

    @Override
    public PageInfo<ProductInfo> splitPageVo(ProductInfoVo vo, int pageSize) {
        //取出集合之前,先要设置PageHelper.startPage()属性
        PageHelper.startPage(vo.getPage(),pageSize);
        List<ProductInfo> list = productInfoMapper.selectCondition(vo);
        return new PageInfo<>(list);
    }

}
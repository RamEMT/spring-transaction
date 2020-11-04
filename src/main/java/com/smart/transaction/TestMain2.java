package com.smart.transaction;

import com.smart.transaction.entity.ProductStock;
import com.smart.transaction.mapper.ProductStockMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Emilia
 * @Since 2020.11.04 21:03
 */
public class TestMain2 {
    public static void main(String[] args) {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(
                TestMain2.class.getClassLoader().getResourceAsStream("mybatis-config.xml")
        );
        SqlSession sqlSession = sessionFactory.openSession(true);
        ProductStockMapper mapper = sqlSession.getMapper(ProductStockMapper.class);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        List<ProductStock> productStocks = mapper.selectProductSize(list);
        System.out.println(productStocks);
    }
}

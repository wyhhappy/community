package com.nowcoder.community.dao;

import org.springframework.stereotype.Repository;

//需要被获取管理
@Repository("alphaHibernate")
public class AlphaDaoHibernateImpl implements AlphaDao {
    @Override
    public String select() {
        return "Hibernate";
    }
}

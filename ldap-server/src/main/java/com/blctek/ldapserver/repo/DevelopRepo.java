package com.blctek.ldapserver.repo;

import com.blctek.ldapserver.pojo.Developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.ldap.repository.Query;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/21/12:00
 * @Description:
 *  developer的crud
 *  对“开发人员”进行增删改查
 */
@Repository
public class DevelopRepo {

    @Autowired
    private LdapTemplate ldapTemplate;

    /*
    *   插入developer
    * */
    public Developer insert(Developer developer) {
        ldapTemplate.create(developer);
        return developer;
    }

    /*
    *   删除developer（只能根据cn，也就是rdn来删除）
    * */
    public void delete(Developer developer) {
        ldapTemplate.delete(developer);
    }

    /*
    *   修改developer
    * */
    public Developer update(Developer developer) {
        System.out.println(developer.getPassword());
        ldapTemplate.update(developer);
        System.out.println(developer.getPassword());
        return developer;
    }

    /*
    *   根据cn查询developer
    * */
    public Developer selectOneByCn(String cn) {
        return ldapTemplate.findOne(query().where("cn").is(cn),Developer.class);
    }

    /*
    *   根据uid（用户名）developer
    * */
    public Developer selectOneByUid(String uid) {
        return ldapTemplate.findOne(query().where("uid").is(uid), Developer.class);
    }

    /*
    *   查询所有developer
    * */
    public List<Developer> selectAllDeveloper(){
        return ldapTemplate.findAll(Developer.class);
    }

    /*
    *   developer认证（登录）
    *   正确即登录，否则直接抛出异常
    * */
    public void authenticateDeveloper(String uid,String password) {
        ldapTemplate.authenticate(query().where("uid").is(uid),password);
    }
}

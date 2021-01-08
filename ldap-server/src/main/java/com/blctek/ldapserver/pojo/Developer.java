package com.blctek.ldapserver.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Name;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/21/11:43
 * @Description:
 *  开发人员
 *  cn=xxx,ou=develop,o=blctek,dc=blctek,dc=com
 */
@Entry(objectClasses = {"inetOrgPerson","top"}, base = "ou=develop,o=blctek")
public class Developer {

    /*
    *   必须
    * */
    @Id
    @JsonIgnore
    private Name dn;

    /*
    *   common name：普通名字
    * */
    @Attribute(name = "cn")
    private String cn;

    /*
    *   surname：别名
    * */
    @Attribute(name = "sn")
    private String sn;

    /*
    *   登录用户名
    * */
    @Attribute(name = "uid")
    private String uid;

    /*
    *   登录密码
    * */
    @Attribute(name = "userPassword")
    private String password;


    public Developer() {
    }

    /*
    *   cn=xxx,ou=develop,o=blctek
    * */
    public Developer(String cn) {
        Name dn = LdapNameBuilder.newInstance()
                .add("o", "blctek")
                .add("ou", "develop")
                .add("cn", cn)
                .build();
        this.dn = dn;
    }

    public Name getDn() {
        return dn;
    }

    public void setDn(Name dn) {
        this.dn = dn;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
        if(this.dn==null){
            Name dn = LdapNameBuilder.newInstance()
                    .add("o", "blctek")
                    .add("ou", "develop")
                    .add("cn", cn)
                    .build();
            this.dn = dn;
        }
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Develop{" +
                "dn:" + dn.toString() +
                "\t" + "attributes:" +
                "cn='" + cn + '\'' +
                ", sn='" + sn + '\'' +
                ", uid='" + uid + '\'' +
                ", userPassword='" + password + '\'' +
                '}';
    }
}

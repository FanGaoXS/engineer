package com.blctek.ldapserver.repo;

import com.blctek.ldapserver.pojo.Developer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: 吴青珂
 * @Date: 2020/12/23/14:30
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class DevelopRepoTest {

    @Autowired
    DevelopRepo developRepo;

    @Test
    public void insert() {
        Developer developer = new Developer();
        developer.setCn("万琦");
        developer.setSn("万琦");
        developer.setUid("wanqi");
        developer.setPassword("954278478");
        developRepo.insert(developer);
    }

    @Test
    public void delete() {
        Developer developer = new Developer();
        developer.setCn("万琦");
        developRepo.delete(developer);
    }

    @Test
    public void update() {
    }

    @Test
    public void selectOneByCn() {

    }

    @Test
    public void selectOneByUid() {
        Developer developer = developRepo.selectOneByUid("wuqingke");
        System.out.println(developer);
    }

    @Test
    public void selectAllDeveloper() {
        List<Developer> developerList = developRepo.selectAllDeveloper();
        for (Developer developer : developerList) {
            System.out.println(developer);
        }
    }
}
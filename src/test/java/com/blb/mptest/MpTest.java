package com.blb.mptest;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blb.mapper.UserMapper;
import com.blb.pojo.User;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)//创建应用上下文
@SpringBootTest//读取application.properties
public class MpTest {

    @Autowired
    private UserMapper userMapper;


    //查询所有
    @Test
    public void test1(){
        List<User> users = userMapper.selectList(null);
        for(User user:users){
            System.out.println(user);
        }


    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setAge(11);
        user.setMail("5267777@qq.com");
        user.setName("tom");
        user.setPassword("111");
        user.setUserName("tom1");
        user.setAddress("ddd");
        int i = userMapper.insert(user);//i是受影响条数
        System.out.println(i);
        System.out.println(user.getId());//返回主键
    }


    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1L);
        user.setUserName("tom");
        userMapper.updateById(user);
    }

    @Test
    public void testUpdate2(){

        User user = new User();
        user.setUserName("mike");

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",1L);
        userMapper.update(user,userQueryWrapper);
    }


    @Test
    public void testUpdate3(){

        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.eq("id",2L).set("user_name","mike");
        int update = userMapper.update(null, userUpdateWrapper);
        System.out.println(update);
    }

    @Test
    public void testDeleteById(){

        int i = userMapper.deleteById(2L);
        System.out.println(i);
    }

    @Test
    public void testDeleteMap(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name","王五");
        map.put("age",28);
        int i = this.userMapper.deleteByMap(map);
        System.out.println(i);


    }


    @Test
    public void testDelete(){

        User user = new User();
        user.setAge(21);
        user.setName("赵六");
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        this.userMapper.delete(userQueryWrapper);
    }

    @Test
    public void testDeleteIds(){

        int i = this.userMapper.deleteBatchIds(Arrays.asList(1L, 1322868817096871938L));
        System.out.println(i);
    }

    @Test
    public void testSelectById(){

        User user = userMapper.selectById(5);
        System.out.println(user);
    }

    @Test
    public void testSelectByids(){

        List<User> users = userMapper.selectBatchIds(Arrays.asList(5L, 1322868959397068801L));
        for(User u:users){
            System.out.println(u);

        }
    }

    @Test
    public void testSelectOne(){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name","tom1");
        User user = this.userMapper.selectOne(userQueryWrapper);
        System.out.println(user);
    }


    @Test
    public void testCount(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.gt("age",10);
        Integer integer = this.userMapper.selectCount(userQueryWrapper);
        System.out.println(integer);


    }
    @Test
    public void testList(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.gt("age",10);
        List<User> users = this.userMapper.selectList(userQueryWrapper);



    }


    @Test
    public void testPage(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.gt("age",10);

        Page<User> objectPage = new Page<>(1, 2);
        IPage<User> userIPage = this.userMapper.selectPage(objectPage, userQueryWrapper);
        System.out.println(userIPage.getTotal());
        System.out.println(userIPage.getPages());
        List<User> records = userIPage.getRecords();
        for(User u:records){
            System.out.println(u);

        }


    }

    @Test
    public void testFind(){
        User user = this.userMapper.findUserById(5L);
        System.out.println(user);

    }

    @Test
    public void  testWrapper(){

        QueryWrapper<User> objectQueryWrapper = new QueryWrapper<>();
        Map<String,Object> params = new HashMap<>();
        params.put("name", "孙七");
        params.put("age", "24");
        params.put("password", null);
        //true:select * from tb_user where name='孙七' and  age=24 and password is null
        //false:select * from tb_user where name='孙七' and  age=24
        //为true则在map的value为null时调用 isNull 方法,为false时则忽略value为null的
        //objectQueryWrapper.allEq(params,true);

        List<User> users = this.userMapper.selectList(objectQueryWrapper);
        for(User u:users){
            System.out.println(u);

        }

    }


    @Test
    public void testEq(){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("password",123456)
                .ge("age",20).in("name","李四","王五");

        List<User> users = this.userMapper.selectList(userQueryWrapper);
        for(User s:users){
            System.out.println(s);

        }
    }

    @Test
    public void testlike(){

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.likeRight("name","张");

        List<User> users = this.userMapper.selectList(userQueryWrapper);
        for(User s:users){
            System.out.println(s);

        }
    }


    public void testAsc(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.orderByAsc("age");
        System.out.println("haha");
        System.out.println("ccc");


    }
}

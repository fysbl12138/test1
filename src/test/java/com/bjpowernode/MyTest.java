package com.bjpowernode;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.entity.CustomObject;
import com.bjpowernode.entity.Student;
import com.bjpowernode.util.MyBatisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author fangy
 * @date 2022-01-10 20:31
 */
public class MyTest {

    //使用mybatis工具类
    @Test
    public void testSelectById(){
        SqlSession session = MyBatisUtil.getSqlsession();
        String sqlId = "com.bjpowernode.dao.StudentDao.selectStudentById";
        Student stu = session.selectOne(sqlId,2);
        System.out.println("查询成功：dao方法式"+stu);
        session.close();
    }
    //通过mybatis代理查询
    @Test
    public void testSelectById2(){
        SqlSession session = MyBatisUtil.getSqlsession();
        StudentDao dao = session.getMapper(StudentDao.class);
        Student stu = dao.selectStudentById(2);
        System.out.println("通过proxy查询"+stu);
        session.close();
    }

    //通过mybatis代理查询
    @Test
    public void testSelectByNameOrAge(){
        SqlSession session = MyBatisUtil.getSqlsession();
        StudentDao dao = session.getMapper(StudentDao.class);
        Student stu = dao.selectStudentByNameOrAge("张三",35);
        System.out.println("通过proxy查询"+stu);
        session.close();
    }

    //自定义对象作为参数
    //通过mybatis代理查询
    @Test
    public void testSelectCustomObject(){
        SqlSession session = MyBatisUtil.getSqlsession();
        StudentDao dao = session.getMapper(StudentDao.class);
        CustomObject stu = dao.selectCustomById(1);
        System.out.println("CustomObject:"+stu);
        session.close();
    }

    //使用student类作为参数
    @Test
    public void testInsertStudent(){
        SqlSession session = MyBatisUtil.getSqlsession();
        StudentDao dao = session.getMapper(StudentDao.class);

        Student stu = new Student();
        stu.setId(4);
        stu.setName("王五");
        stu.setEmail("wangwu@qq.com");
        stu.setAge(25);

        int num = dao.insertStudent(stu);
        session.commit();
        System.out.println("改变了"+num+"行");
        session.close();
    }

    //使用student类作为参数
    @Test
    public void testPagehelperStudent(){
        SqlSession session = MyBatisUtil.getSqlsession();
        StudentDao dao = session.getMapper(StudentDao.class);

        PageHelper.startPage(1,3);
        List<Student> num = dao.selectStudentPagehelper();
        num.forEach(b->System.out.println("查询到的结果"+b));
        session.close();
    }
}

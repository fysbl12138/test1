package com.bjpowernode.dao;

import com.bjpowernode.entity.CustomObject;
import com.bjpowernode.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fangy
 * @date 2022-01-10 13:56
 */
public interface StudentDao {

    Student selectStudentById(Integer id);

    Student selectStudentByNameOrAge(@Param("myname") String name,@Param("myage") Integer age);

    int insertStudent(Student student);

    CustomObject selectCustomById(Integer id);

    //分页插件pagehelper的测试方法
    List<Student> selectStudentPagehelper();
}

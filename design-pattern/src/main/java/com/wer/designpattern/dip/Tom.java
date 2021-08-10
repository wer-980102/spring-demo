package com.wer.designpattern.dip;

/**
 * 依赖倒置原则
 */
public class Tom {

   /* public void studentJavaCourse(){
        System.out.println("Tom在学习Java的课程！");
    }

    public void studentPythonCourse(){
        System.out.println("Tom在学习Python的课程！");
    }*/

    public void studey(Icourse icourse){
        icourse.study();
    }
}

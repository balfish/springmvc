package com.balfish.hotel.train.callback;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class Asker extends CallBack {

    /**
     * (1) A 类实现接口CallBack
     * (2) A 类中包含一个B类的引用b
     * (3) B 类中有一个参数为callback的方法func(CallBack callBack)
     * (4) A 类的对象a调用B的方法func(CallBack callBack),而func中调用CallBack的方法,也就是A中实现的抽象方法
     */

    public String execute() {
        return "the answer of the question has fetched";
    }

    private Answerer answerer;

    public void setAnswerer(Answerer answerer) {
        this.answerer = answerer;
    }

    public void askQuestion() {
        new Thread(new Runnable() {
            public void run() {
                answerer.answer(Asker.this);
            }
        }).start();

        doSomethingElse();
    }

    public void doSomethingElse() {
        System.out.println("do something else");
    }

}

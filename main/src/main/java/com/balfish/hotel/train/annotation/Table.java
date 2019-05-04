package com.balfish.hotel.train.annotation;

import java.lang.annotation.*;

/**
 * Created by yhm on 2017/7/12 PM2:17
 * <p>
 * 元注解
 *
 * @Target 用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
 * @Retention 表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
 * @Documented 用于描述其它类型的annotation应该被作为被标注的程序成员的公共API，因此可以被例如javadoc此类的工具文档化。Documented是一个标记注解，没有成员。
 * @Inherited 标记注解, 阐述了某个被标注的类型是被继承的
 * <p>
 * <p>
 * 自定义注解类编写的一些规则:
 * 1. Annotation型定义为@interface, 所有的Annotation会自动继承java.lang.Annotation这一接口,并且不能再去继承别的类或是接口.
 * 2. 参数成员只能用public或默认(default)这两个访问权修饰
 * 3. 参数成员只能用基本类型byte,short,char,int,long,float,double,boolean八种基本数据类型和String、Enum、Class、annotations等数据类型,以及这一些类型的数组.
 * 4. 要获取类方法和字段的注解信息，必须通过Java的反射技术来获取 Annotation对象,因为你除此之外没有别的获取注解对象的方法
 * <p>
 * how to use:
 * class annotation : Main.class.getAnnotation(classAnnotation.class);
 * field annotation : Main.class.getDeclaredField("xxx").getAnnotation(fieldAnnotation.class);
 * method annotation: Main.class.getDeclaredMethod("setXxx", Type.class).getAnnotation(methodAnnotation.class);
 * constructor annotation: Main.class.getConstructor().getAnnotation(constructorAnnotation.class);
 */


/**
 * 水果名称注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface FruitName {
    String value() default "";
}

/**
 * 水果颜色注解
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface FruitColor {
    /**
     * 颜色枚举
     */
    enum Color {
        BLUE, RED, GREEN
    }

    /**
     * 颜色属性
     */
    Color fruitColor() default Color.GREEN;

}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface TableName {
    String value() default "desk";
}

@TableName("mydesk")
@FruitColor
public class Table {
    private String appleName;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String appleColor;

    public void setAppleColor(String appleColor) {
        this.appleColor = appleColor;
    }

    public String getAppleColor() {
        return appleColor;
    }

    @FruitName("Apple")
    public void setAppleName(String appleName) {
        this.appleName = appleName;
    }

    public String getAppleName() {
        return appleName;
    }

    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        FruitColor annotation = Table.class.getDeclaredField("appleColor").getAnnotation(FruitColor.class);
        System.out.println(annotation.fruitColor());

//        Table table = new Table();
//        Class aClass = table.getClass();
//        aClass = Table.class;
        FruitColor fruitColor = Table.class.getAnnotation(FruitColor.class);
        System.out.println(fruitColor.fruitColor() + "~~~~");

        FruitName annotation1 = Table.class.getDeclaredMethod("setAppleName", String.class).getAnnotation(FruitName.class);
        System.out.println(annotation1.value());

        TableName annotation2 = Table.class.getAnnotation(TableName.class);
        System.out.println(annotation2.value());
    }
}
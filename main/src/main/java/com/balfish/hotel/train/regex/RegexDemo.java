package com.balfish.hotel.train.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yhm on 2017/7/12 PM2:17
 */
public class RegexDemo {

    /**
     * <pre>
     *     (1) 字符:
     *     x 字符 x
     *     \\ 反斜线字符
     *     \t 制表符('\u0009')
     *     \n 换行符 ('\u000A')
     *     \r 回车符 ('\u000D')
     *     \f 换页符 ('\u000C')
     *     \a 响铃符 ('\u0007')
     *     \e 转义符 ('\u001B')
     *     \cx T对应于x的控制字符 x
     *
     *
     *     (2) 字符类:
     *     [abc] a, b, or c (简单类)
     *     [^abc] 除了a、b或c之外的任意 字符（求反）
     *     [a-zA-Z] a到z或A到Z ，包含（范围)
     *     [a-z-[bc]] a到z，除了b和c ： [ad-z]（减去）
     *     [a-z-[m-p]] a到z，除了m到 p： [a-lq-z]
     *     [a-z-[^def]] d, e, 或 f
     *     t(a|e|i|o|oo)n 圆括号匹配多个字符(方括号只允许匹配单个字符)
     *
     *
     *     (3) 预定义的字符类:
     *     . 任意字符
     *     \d 数字: [0-9]
     *     \D 非数字: [^0-9]
     *     \s 空格符: [\t\n\f\r]
     *     \S 非空格符: [^\s]
     *     \w 单词字符: [a-zA-Z_0-9]
     *     \W 非单词字符: [^\w]
     *
     *
     *     (4) 表达次数的符号:
     *     * 0次或者多次
     *     + 1次或者多次
     *     ? 0次或者1次
     *     {n} 恰好n次
     *     {n,m} 从n次到m次
     * </pre>
     *
     *
     * java.util.regex是一个用正则表达式所订制的模式来对字符串进行匹配工作的类库包
     *
     * 它包括两个类：Pattern和Matcher
     *
     * Pattern： 一个Pattern是一个正则表达式经编译后的表现模式
     *
     * Matcher： 一个Matcher对象是一个状态机器，它依据Pattern对象做为匹配模式对字符串展开匹配检查
     *
     * Pattern实例订制了一个所用语法与PERL的类似的正则表达式经编译后的模式，然后Matcher实例在这个给定的Pattern实例的模式控制下进行字符串的匹配
     */
    /**
     * 过滤掉所有指定的字符，并可以用其他统一的字符来替代
     *
     * @param args
     */
    public static void main(String[] args) {
//        regexDemo1();
        regexDemo2();
    }

    /**
     * matcher.group(1)  matcher.group(2)...
     */
    private static void regexDemo2() {
        String str = "Hello,World! in Java.";
        Pattern pattern = Pattern.compile("W(or)(ld!)");
        Matcher matcher = pattern.matcher(str);
        boolean matches = Pattern.compile("\\d+x\\d+(x\\d{1,2})?").matcher("100x100x1999").matches();
        System.out.println("matches + " + matches);
        while (matcher.find()) {
            System.out.println("Group 0:" + matcher.group(0));// 得到第0组——整个匹配
            System.out.println("Group 1:" + matcher.group(1));// 得到第一组匹配——与(or)匹配的
            System.out.println("Group 2:" + matcher.group(2));// 得到第二组匹配——与(ld!)匹配的，组也就是子表达式
            System.out.println("Start 0:" + matcher.start(0) + " End 0:" + matcher.end(0));// 总匹配的索引
            System.out.println("Start 1:" + matcher.start(1) + " End 1:" + matcher.end(1));// 第一组匹配的索引
            System.out.println("Start 2:" + matcher.start(2) + " End 2:" + matcher.end(2));// 第二组匹配的索引
            System.out.println(str.substring(matcher.start(0), matcher.end(1)));// 从总匹配开始索引到第1组匹配的结束索引之间子串——Wor
        }
    }

    /**
     * 格式过滤 检测输入的EMAIL地址是否以 非法符号"www"作为起始字符,"com"作为终止符号
     */
    private static void regexDemo1() {
        String input = "www.sawadee@163.com";

        Pattern pattern = Pattern.compile("^www\\..*com$");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            System.out.println("不能以www作起始符");
            return;
        }

        // 检测是否包含非法字符
        pattern = Pattern.compile("[^A-Za-z0-9.@_-~#]+");
        matcher = pattern.matcher(input);
        StringBuffer sb = new StringBuffer();
        boolean isIllegal = false;
        while (matcher.find()) {
            // 如果找到了非法字符那么就设下标记
            isIllegal = true;
            // 如果里面包含非法字符如冒号双引号等，那么就把他们消去，加到SB里面
            matcher.appendReplacement(sb, "");
        }
        matcher.appendTail(sb);
        if (isIllegal) {
            System.out.println("输入的EMAIL地址里包含非法字符, 修改后合法的地址应类似: " + input);
        } else {
            System.out.println("没毛病");
        }
    }
}

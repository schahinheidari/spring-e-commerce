package fr.tln.univ.designPatterns.proxy;

public class Main {
    // this is like a decorator pattern or maybe sometimes like adaptor pattern
    // هدف کنترل کردن آبجکت هاست
    // تفاوت پروکسی و دکوراتور
    /*
    1. میتواند قابلیت اضافه کند ولی هدف اصلیش نیست
    2. ارتباط شی اصلی با پروکسی در زمان کامپایل مشخص میشود
    3. هدف اصلی پروکسی کنترل کردن شی اصلی است به طور مثال برای پیاده سازی امنیت و caching, lazy Objects و ...

     */

    public int plus(int x, int y){
        return x+y;
    }
    public static void main(String[] args) throws Exception {
        // به این کار میگن جاوا ریفلکشن
        Class<?> aClass = Class.forName("fr.tln.univ.designPatterns.proxy.Main");
        Object testObject = aClass.newInstance();
        Object plus = testObject.getClass().getMethod("plus", int.class, int.class)
                .invoke(testObject, 10, 15);
        System.out.println(plus);
    }
}

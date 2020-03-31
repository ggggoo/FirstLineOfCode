package com.ycdage.activity.otheruse;

public class ClassUseHelper extends BaseHelper {

    // 构造函数
    public ClassUseHelper() {
        sendHelperMsg("----构造函数---");
    }

    // 静态的参数初始化
    static {
        sendHelperMsg("---静态的参数初始化---");
    }

    // 非静态的参数初始化
    {
        sendHelperMsg("----非静态的参数初始化---");
    }


    public static void main(String[] args) {
        @SuppressWarnings("rawtypes")
        Class testTypeClass = ClassUseHelper.class;
        System.out.println("testTypeClass---" + testTypeClass);

        // 测试Class.forName()
        @SuppressWarnings("rawtypes")
        Class testTypeForName = null;
        try {
            testTypeForName = Class.forName("com.ycdage.activity.otheruse.ClassUseHelper");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("testTypeForName---" + testTypeForName);

        // 测试Object.getClass()
        ClassUseHelper testTypeGetClass = new ClassUseHelper();
        System.out.println("testTypeGetClass---"
                + testTypeGetClass.getClass());


    }

    public static void useDotClass() {
        Class testTypeClass = ClassUseHelper.class;

    }

    public static void useClassForName() {
        try {
            Class testTypeClass = Class.forName("com.ycdage.activity.otheruse.ClassUseHelper");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void useGetClass() {
        ClassUseHelper testTypeGetClass = new ClassUseHelper();
        testTypeGetClass.getClass();
    }

}

package com.bus.anno;


import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;


public class ParseAnnotation {
    @Test
    public void testOne() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //1.得到目标对象的Class文件对象
        Class<?> aClass = Class.forName("com.bus.anno.Admin");
        Constructor<?> con = aClass.getConstructor();
        Admin admin = (Admin) con.newInstance();
        //2.得到类上面的注解
        /*boolean f = aClass.isAnnotationPresent(Column.class);
        System.out.println(f);
        if (f) {
            Column t = aClass.getAnnotation(Column.class);
            System.out.println("得到的注解对象是:"+t);
            System.out.println("得到的注解上面的值是:"+t.value());
        }*/

        //3.得到属性上的注解
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            //判断属性上面是否有注解
            boolean fieldHasAnno = field.isAnnotationPresent(Column.class);
            if (fieldHasAnno) {
                Column fieldAnno = field.getAnnotation(Column.class);
                //输出注解属性
                System.out.println(fieldAnno.value());
                field.setAccessible(true);
                String typeName = field.getGenericType().getTypeName();
                if (typeName.contains("Integer")) {
                    field.set(admin, Integer.parseInt(fieldAnno.value()));
                } else {
                    field.set(admin, fieldAnno.value());
                }
            }
        }
    }

    public static String  testSelect(Object obj) throws Exception {
        StringBuilder autoSql = new StringBuilder(" select * from ");
        Class<?> c = obj.getClass();
        boolean f = c.isAnnotationPresent(Table.class);
        if (f) {
            Table t = c.getAnnotation(Table.class);
            String tableName = t.value();
            if (tableName.length() != 0) {
                autoSql.append(tableName);
            } else {
                throw new Exception("-- 注解Table上面的值无效! --");
            }
        }
        autoSql.append(" where 1 = 1 ");

        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            boolean fieldHasAnno = field.isAnnotationPresent(Column.class);
            if (fieldHasAnno) {
                /*
                Column fieldAnno = field.getAnnotation(Column.class);
                if (fieldAnno.value().length() != 0) {
                    autoSql.append(" and " + field.getName() + " = " + "'" + fieldAnno.value() + "'");
                } else {
                    throw new Exception("-- 注解Column上面的值无效! --");
                }
                */
                if (field.getGenericType().getTypeName().contains("Integer")) {
                    Integer anInt = (Integer) field.get(obj);//返回obj对象上此 Field 表示的字段的值
                    if (anInt > 0) {
                        autoSql.append(" and " + field.getName() + " = " + anInt);
                    }
                } else if (field.getGenericType().getTypeName().contains("String")) {
                    String anStr = (String) field.get(obj);
                    if (anStr != null) {
                        autoSql.append(" and " + field.getName() + " = " + anStr);
                    }
                }
            }
        }
        return autoSql.toString();

    }
    
    public static String testInsert(Object obj) throws Exception {
        StringBuilder autoSql = new StringBuilder(" insert into ");
        Class<?> c = obj.getClass();
        boolean f = c.isAnnotationPresent(Table.class);
        if (f) {
            Table t = c.getAnnotation(Table.class);
            String tableName = t.value();
            if (tableName.length() != 0) {
                autoSql.append(tableName+"(");
            } else {
                throw new Exception("-- 注解Table上面的值无效! --");
            }
        }
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i< fields.length; i++){
            fields[i].setAccessible(true);
            boolean fieldHasAnno = fields[i].isAnnotationPresent(Column.class);
            String name = fields[i].getName();
            if (fieldHasAnno) {
                if (fields[i+1].isAnnotationPresent(Column.class)) {
                    autoSql.append(name+", ");
                } else {
                    autoSql.append(name+")  values (");
                }
            }
        }
        for (int i = 0; i< fields.length; i++){
            fields[i].setAccessible(true);
            boolean fieldHasAnno = fields[i].isAnnotationPresent(Column.class);
            if (fieldHasAnno) {
                if (fields[i].getGenericType().getTypeName().contains("Integer")) {
                    Integer anInt = (Integer) fields[i].get(obj);//返回obj对象上此 Field 表示的字段的值
                    if (anInt > 0 && fields[i+1].isAnnotationPresent(Column.class)) {
                        autoSql.append(anInt+", ");
                    } else {
                        autoSql.append(anInt+") ");
                    }
                } else if (fields[i].getGenericType().getTypeName().contains("String")) {
                    String anStr = (String) fields[i].get(obj);
                    if (anStr != null && fields[i+1].isAnnotationPresent(Column.class)) {
                        autoSql.append("'" + anStr + "', ");
                    } else {
                        autoSql.append("'" + anStr + "')");
                    }
                }
            }
        }

        return autoSql.toString();
    }

    public static String testUpdate(Object obj) throws Exception {
        StringBuilder autoSql = new StringBuilder(" update ");
        Class<?> c = obj.getClass();
        boolean f = c.isAnnotationPresent(Table.class);
        if (f) {
            Table t = c.getAnnotation(Table.class);
            String tableName = t.value();
            if (tableName.length() != 0) {
                autoSql.append(tableName+" set ");
            } else {
                throw new Exception("-- 注解Table上面的值无效! --");
            }
        }
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i< fields.length; i++) {
            fields[i].setAccessible(true);
            boolean fieldHasAnno = fields[i].isAnnotationPresent(Column.class);
            if (fieldHasAnno) {
                 if (fields[i].getGenericType().getTypeName().contains("String")) {
                    String anStr = (String) fields[i].get(obj);
                    if (anStr != null && fields[i+1].isAnnotationPresent(Column.class)) {
                        autoSql.append(fields[i].getName() + " = '" + anStr + "', ");
                    } else {
                        autoSql.append(fields[i].getName() + " = " + anStr);
                    }
                }

            }
        }
        autoSql.append(" where 1 = 1 ");
        for (int i = 0; i< fields.length; i++) {
            fields[i].setAccessible(true);
            boolean fieldHasAnno = fields[i].isAnnotationPresent(Column.class);
            if (fieldHasAnno) {
                if (fields[i].getGenericType().getTypeName().contains("Integer")) {
                    Integer anInt = (Integer) fields[i].get(obj);//返回obj对象上此 Field 表示的字段的值
                    if (anInt > 0) {
                        autoSql.append(" and " + fields[i].getName() + " = " + anInt);
                    }
                }
            }
        }
        return autoSql.toString();
    }



    public static void main(String[] args) {
        Admin admin = new Admin();
        admin.setAdminId(8812);
        admin.setAdminName("lisa");
        String sql = null;
        try {
            sql = testUpdate(admin);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println(sql);
    }
}

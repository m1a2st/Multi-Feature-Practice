package com.example.annotation;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

public class ExcelTest {

    @Test
    public void test() throws IllegalAccessException {
        Model model = new Model();
        model.setFundUuid("fundUuidasdf");
        model.setTempId("tempIdasdf");
        model.setFundFileDate("fundFileDateadf");
        model.setMoney(null);
        Class<? extends Model> clazz = model.getClass();
        ExcelSheet annotation = clazz.getAnnotation(ExcelSheet.class);
        System.out.println(annotation);
        System.out.println(annotation.annotationType());
        System.out.println(annotation.sheetName());
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            ExcelField annotation1 = field.getAnnotation(ExcelField.class);
            System.out.println(field.getName());
            System.out.println(field.get(model));
            System.out.println(annotation1);
            System.out.println(annotation1.annotationType());
        }
    }
}

package com.tad.utils;

public class SlugConvert {
    //chuyển chuỗi thành dạng URL-friendly (slug-like).
    public static String convert(String str) {
        // Xóa kí tự "/"
        str = str.replace("/", "");

        // Thay thế dấu gạch ngang trước và sau khoảng trắng bằng khoảng trắng
        str = str.replaceAll("\\s*\\-\\s*", " ");

        // Thay thế khoảng trắng bằng dấu gạch ngang
        str = str.replaceAll("\\s+", "-");
        return str;
    }
}

package com.example.java_group_11_homework_71_alfit_bashirov.util;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

public class PageUtil {
    public static <T> void constructPageable(Page<T> products, int pageSize, Model model, String uri) {
        if (products.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri,
                    products.nextPageable().getPageNumber(), products.nextPageable().getPageSize()));
        }


        if (products.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri,
                    products.previousPageable().getPageNumber(), products.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", products.hasNext());
        model.addAttribute("hasPrev", products.hasPrevious());
        model.addAttribute("products", products.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }

    private static String constructPageUri(String uri, int page, int size) {
        if (uri.contains("search")) {
            return String.format("%s&page=%s&size=%s", uri, page, size);
        }
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }
}

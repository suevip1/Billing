package com.pingpongx.smb.fee.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PageDTO<T> implements Serializable {

    private static final long serialVersionUID = -8197253642316804705L;

    private List<T> records = Collections.emptyList();
    /**
     * 总数
     */
    private long total;

    /**
     * 每页显示条数，默认 10
     */
    private long size;

    /**
     * 总页数
     */
    private long pages;

    /**
     * 当前页
     */
    private long current;

    public static <T extends Serializable> PageDTO<T> emptyList(int pages, int size) {
        PageDTO dto = new PageDTO();
        dto.setTotal(0);
        dto.setPages(pages);
        dto.setSize(size);
        return dto;
    }

    //    public static <T extends Serializable> PageDTO<T> convertToDTO(IPage pages, Class<T> clazz) {
//        List<T> result = ConvertUtils.convert(pages.getRecords(), clazz);
//        return page(pages, result);
//    }
//
//    public static <T> PageDTO page(IPage pages, List<T> list) {
//        return new PageDTO<T>()
//            .setCurrent(pages.getCurrent())
//            .setPages(pages.getPages())
//            .setTotal(pages.getTotal())
//            .setSize(pages.getSize())
//            .setRecords(list);
//    }
}

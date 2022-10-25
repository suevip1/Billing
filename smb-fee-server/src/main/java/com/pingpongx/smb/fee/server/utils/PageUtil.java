package com.pingpongx.smb.fee.server.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pingpongx.smb.fee.common.dto.PageDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageUtil {

    public static <T extends Serializable> PageDTO<T> covert(IPage<?> page) {
        PageDTO<T> pageDTO = PageUtil.covertWithOutRecords(page);
        pageDTO.setRecords((List<T>) page.getRecords());
        return pageDTO;
    }

    public static <T extends Serializable> PageDTO<T> covertWithOutRecords(IPage<?> page) {
        PageDTO<T> pageDTO = new PageDTO<T>();
        pageDTO.setPages(Long.valueOf(page.getPages()).intValue());
        pageDTO.setTotal(Long.valueOf(page.getTotal()).intValue());
        pageDTO.setCurrent(Long.valueOf(page.getCurrent()).intValue());
        pageDTO.setSize(Long.valueOf(page.getSize()).intValue());
        pageDTO.setRecords(new ArrayList<T>());
        return pageDTO;
    }

    public static <T extends Serializable> PageDTO<T> covertWithRecords(IPage<?> page, List<T> records) {
        PageDTO<T> pageDTO = PageUtil.covertWithOutRecords(page);
        pageDTO.setRecords(records);
        return pageDTO;
    }

}

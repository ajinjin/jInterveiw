package com.gu.mapper;

import com.gu.model.SimpleData;


import java.util.List;


public interface SimpleDataMapper {
    List<SimpleData> getData();

    Integer updateData();
}

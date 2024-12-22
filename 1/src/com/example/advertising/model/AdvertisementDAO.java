package com.example.advertising.model;

import java.util.List;
import java.util.Map;

public interface AdvertisementDAO {
    List<Advertisement> getAdvertisementsByTags(Map<String, Integer> tagFrequency);
    // 可按需添加其他数据库操作方法，如插入、更新、删除广告等
}
package smaug.cloud.data.mapper.shop;

import smaug.cloud.data.entity.shop.ShopEntity;

public interface ShopEntityMapper {
    int deleteByPrimaryKey(Integer shopid);

    int insert(ShopEntity record);

    int insertSelective(ShopEntity record);

    ShopEntity selectByPrimaryKey(Integer shopid);

    int updateByPrimaryKeySelective(ShopEntity record);

    int updateByPrimaryKey(ShopEntity record);
}
package smaug.cloud.provider.mappers.shop;

import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Configuration;
import smaug.cloud.data.entity.shop.ShopEntity;


public interface ShopEntityMapper {

    ShopEntity selectByPrimaryKey(@Param("shopId") Integer shopid);

}
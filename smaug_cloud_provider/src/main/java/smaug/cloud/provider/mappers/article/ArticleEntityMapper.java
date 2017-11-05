package smaug.cloud.provider.mappers.article;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import smaug.cloud.data.entity.article.ArticleEntity;

import java.util.List;

@Mapper
public interface ArticleEntityMapper {
    //List<ArticleEntity> getAllArticles();

    ArticleEntity selectByPrimaryKey(@Param("id") int id);
}
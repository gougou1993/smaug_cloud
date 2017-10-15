package smaug.cloud.data.mapper.article;

import smaug.cloud.data.entity.article.ArticleEntity;

public interface ArticleEntityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleEntity record);

    int insertSelective(ArticleEntity record);

    ArticleEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleEntity record);

    int updateByPrimaryKey(ArticleEntity record);
}
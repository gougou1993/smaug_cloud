package smaug.cloud.provider.mappers.article;


import smaug.cloud.data.entity.article.ArticleEntity;

import java.util.List;

public interface ArticleEntityMapper {
    List<ArticleEntity> getAllArticles();
}
package smaug.cloud.provider.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import smaug.cloud.api.vos.article.ArticleResponse;
import smaug.cloud.data.entity.article.ArticleEntity;
import smaug.cloud.provider.mappers.article.ArticleEntityMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by naonao on 17/10/15.
 */
@Component("articleHelper")
public class ArticleHelper extends AbstractHandler {

    @Autowired
    public ArticleEntityMapper articleEntityMapper;

    public List<ArticleEntity> articleList() {
        List<ArticleEntity> entities = articleEntityMapper.getAllArticles();
        logger.info(jsonUtil.toJson(entities));
        return entities;
    }

    public List<ArticleResponse> toResponse(List<ArticleEntity> entities) {
        List<ArticleResponse> response = new ArrayList<>();
        if (null == entities || entities.isEmpty()) {
            return Collections.emptyList();
        }
        entities.stream().map(e -> {
            ArticleResponse re = new ArticleResponse();
            re.setId(e.getId());
            re.setAuthorId(e.getAuthorid());
            re.setCreateTime(e.getCreatetime());
            re.setHtmlContent(e.getContenthtml());
            re.setRightContent(e.getContentright());
            re.setLeftContent(e.getContentleft());
            return re;
        }).forEach(response::add);
        return response;
    }
}

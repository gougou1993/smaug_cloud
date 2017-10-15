package smaug.cloud.api.vos.article;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by naonao on 17/10/15.
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class ArticleResponse {
    private int id;
    private String title;
    private String leftContent;
    private int authorId;
    private Date createTime;
    private String rightContent;
    private String htmlContent;
}

package smaug.cloud.data.entity.article;

import java.util.Date;

public class ArticleEntity {
    private Integer id;

    private String title;

    private String contentleft;

    private Integer authorid;

    private Date createtime;

    private String contentright;

    private String contenthtml;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContentleft() {
        return contentleft;
    }

    public void setContentleft(String contentleft) {
        this.contentleft = contentleft == null ? null : contentleft.trim();
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContentright() {
        return contentright;
    }

    public void setContentright(String contentright) {
        this.contentright = contentright == null ? null : contentright.trim();
    }

    public String getContenthtml() {
        return contenthtml;
    }

    public void setContenthtml(String contenthtml) {
        this.contenthtml = contenthtml == null ? null : contenthtml.trim();
    }
}
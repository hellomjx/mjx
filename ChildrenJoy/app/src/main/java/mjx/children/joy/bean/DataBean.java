package mjx.children.joy.bean;

/**
 * 故事数据
 * Created by MJX on 2018/3/7.
 */
public class DataBean {
    private String name;
    private String content;
    private String img;
    private String link;

    public DataBean(String name, String content, String img,String link) {
        this.name = name;
        this.content = content;
        this.img = img;
        this.link = link;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

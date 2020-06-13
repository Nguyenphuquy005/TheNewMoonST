package stackjava.com.mongodb.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//@Document(collection = "Products")
public class Product {
    @Id
    private Long id;
    private String title;
    private String handle;
    private String body_html;
    private String published_at;
    private String created_at;
    private String updated_at;
    private String vendor;
    private String product_type;
    private List tags;
    private List variants;
    private List images;
    private List options;

    public Product(Long id, String title, String handle, String body_html, String published_at, String created_at, String updated_at, String vendor, String product_type, List tags, List variants, List images, List options) {
        this.id = id;
        this.title = title;
        this.handle = handle;
        this.body_html = body_html;
        this.published_at = published_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.vendor = vendor;
        this.product_type = product_type;
        this.tags = tags;
        this.variants = variants;
        this.images = images;
        this.options = options;
    }

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getBody_html() {
        return body_html;
    }

    public void setBody_html(String body_html) {
        this.body_html = body_html;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public List getTags() {
        return tags;
    }

    public void setTags(List tags) {
        this.tags = tags;
    }

    public List getVariants() {
        return variants;
    }

    public void setVariants(List variants) {
        this.variants = variants;
    }

    public List getImages() {
        return images;
    }

    public void setImages(List images) {
        this.images = images;
    }

    public List getOptions() {
        return options;
    }

    public void setOptions(List options) {
        this.options = options;
    }
}

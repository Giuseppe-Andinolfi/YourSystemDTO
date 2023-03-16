package com.yourSystem.project;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
public class CourseDTO {
    @Id
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    private String content;

    @NotNull
    private Integer maxNumberOfParticipants;

    private String url;

    @ManyToOne
    private Category category;

    private Double price;

    public CourseDTO() {}

    public CourseDTO(Long id, String name, String content, Integer maxNumberOfParticipants, String url, Category category, Double price) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.maxNumberOfParticipants = maxNumberOfParticipants;
        this.url = url;
        this.category = category;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMaxNumberOfParticipants() {
        return maxNumberOfParticipants;
    }

    public void setMaxNumberOfParticipants(Integer maxNumberOfParticipants) {
        this.maxNumberOfParticipants = maxNumberOfParticipants;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "CourseDTO [id=" + id + ", name=" + name + ", content=" + content + ", maxNumberOfParticipants=" + maxNumberOfParticipants
                + ", url=" + url + ", category=" + category + ", price=" + price + "]";
    }
}

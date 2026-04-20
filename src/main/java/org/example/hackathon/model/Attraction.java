package org.example.hackathon.model;

import jakarta.validation.constraints.*;

public class Attraction {
    private Long id;

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 10, max = 200, message = "Độ dài từ 10 đến 200 ký tự")
    private String name;

    @NotBlank(message = "Vui lòng chọn hoặc nhập tỉnh/thành phố")
    private String location;

    private String description;

    @NotNull(message = "Vui lòng nhập điểm đánh giá")
    @DecimalMin(value = "1", message = "Điểm thấp nhất là 1")
    @DecimalMax(value = "5", message = "Điểm cao nhất là 5")
    private Double rating;

    private String coverImage;

    public Attraction() {
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
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public String getCoverImage() {
        return coverImage;
    }
    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }
}
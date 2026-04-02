package org.themoviedb.movie.vo.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;

public class WriteVO {

    private String movieId;
    @NotBlank(message = "포스터 URL을 입력해 주세요")
    private String posterUrl;
    @NotBlank(message = "제목을 입력해 주세요")
    private String title;
    private String movieRating;
    private String openDate;
    private String openCountry;
    private int runningTime;
    private String introduce;
    @NotBlank(message = "개요를 입력해 주세요")
    private String sysnopsis;
    private String originalTitle;
    @NotBlank(message = "상태를 선택해 주세요")
    private String state;
    @NotBlank(message = "원어를 입력해 주세요")
    private String language;
    private int budget;
    private int profit;
    
    private List<MultipartFile> posterFiles;
    
    public String getMovieId() {
        return this.movieId;
    }
    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }
    public String getPosterUrl() {
        return this.posterUrl;
    }
    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getMovieRating() {
        return this.movieRating;
    }
    public void setMovieRating(String movieRating) {
        this.movieRating = movieRating;
    }
    public String getOpenDate() {
        return this.openDate;
    }
    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }
    public String getOpenCountry() {
        return this.openCountry;
    }
    public void setOpenCountry(String openCountry) {
        this.openCountry = openCountry;
    }
    public int getRunningTime() {
        return this.runningTime;
    }
    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }
    public String getIntroduce() {
        return this.introduce;
    }
    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
    public String getSysnopsis() {
        return this.sysnopsis;
    }
    public void setSysnopsis(String sysnopsis) {
        this.sysnopsis = sysnopsis;
    }
    public String getOriginalTitle() {
        return this.originalTitle;
    }
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }
    public String getState() {
        return this.state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getLanguage() {
        return this.language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public int getBudget() {
        return this.budget;
    }
    public void setBudget(int budget) {
        this.budget = budget;
    }
    public int getProfit() {
        return this.profit;
    }
    public void setProfit(int profit) {
        this.profit = profit;
    }
    public List<MultipartFile> getPosterFiles() {
        return this.posterFiles;
    }
    public void setPosterFiles(List<MultipartFile> posterFiles) {
        this.posterFiles = posterFiles;
    }

}

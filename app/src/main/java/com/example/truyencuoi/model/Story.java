package com.example.truyencuoi.model;

public class Story {
    private String storyName;
    private String storyContent;
    private boolean favorite;

    public Story(String storyName, String storyContent) {
        this.storyName = storyName;
        this.storyContent = storyContent;
        this.favorite = false;
    }

    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName;
    }

    public String getStoryContent() {
        return storyContent;
    }

    public void setStoryContent(String storyContent) {
        this.storyContent = storyContent;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}

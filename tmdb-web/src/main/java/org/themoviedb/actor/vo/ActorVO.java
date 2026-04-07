package org.themoviedb.actor.vo;

import jakarta.validation.constraints.NotBlank;

public class ActorVO {

    private String actorId;
    @NotBlank(message = "이름을 입력하세요.")
    private String actorName;
    private String actorProfileUrl;

    public String getActorId() {
        return this.actorId;
    }
    public void setActorId(String actorId) {
        this.actorId = actorId;
    }
    public String getActorName() {
        return this.actorName;
    }
    public void setActorName(String actorName) {
        this.actorName = actorName;
    }
    public String getActorProfileUrl() {
        return this.actorProfileUrl;
    }
    public void setActorProfileUrl(String actorProfileUrl) {
        this.actorProfileUrl = actorProfileUrl;
    }
    
}

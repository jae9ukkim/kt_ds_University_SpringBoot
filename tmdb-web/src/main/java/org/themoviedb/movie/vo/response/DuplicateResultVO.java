package org.themoviedb.movie.vo.response;

public class DuplicateResultVO {

    private String email;
    private Boolean duplicate;

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Boolean getDuplicate() {
        return this.duplicate;
    }
    public void setDuplicate(Boolean duplicate) {
        this.duplicate = duplicate;
    }
    
}

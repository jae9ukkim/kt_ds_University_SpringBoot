package org.themoviedb.members.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MembersVO {

    @NotBlank(message = "이메일 형태가 아닙니다")
    @Email(message = "이메일 형태가 아닙니다")
    private String email;
    @NotBlank(message = "이메일을 입력하세요")
    private String name;
    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;
    private String salt;
    private String registDate;
    private String modifyDate;
    private String latestPasswordChangeDate;
    private String loginDate;
    private String latestLoginIp;
    private int loginFailCount;
    private String latestLoginFailDae;
    private String blockYn;
    private String fileGroupId;

    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSalt() {
        return this.salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }
    public String getRegistDate() {
        return this.registDate;
    }
    public void setRegistDate(String registDate) {
        this.registDate = registDate;
    }
    public String getModifyDate() {
        return this.modifyDate;
    }
    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
    public String getLatestPasswordChangeDate() {
        return this.latestPasswordChangeDate;
    }
    public void setLatestPasswordChangeDate(String latestPasswordChangeDate) {
        this.latestPasswordChangeDate = latestPasswordChangeDate;
    }
    public String getLoginDate() {
        return this.loginDate;
    }
    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }
    public String getLatestLoginIp() {
        return this.latestLoginIp;
    }
    public void setLatestLoginIp(String latestLoginIp) {
        this.latestLoginIp = latestLoginIp;
    }
    public int getLoginFailCount() {
        return this.loginFailCount;
    }
    public void setLoginFailCount(int loginFailCount) {
        this.loginFailCount = loginFailCount;
    }
    public String getLatestLoginFailDae() {
        return this.latestLoginFailDae;
    }
    public void setLatestLoginFailDae(String latestLoginFailDae) {
        this.latestLoginFailDae = latestLoginFailDae;
    }
    public String getBlockYn() {
        return this.blockYn;
    }
    public void setBlockYn(String blockYn) {
        this.blockYn = blockYn;
    }
    public String getFileGroupId() {
        return this.fileGroupId;
    }
    public void setFileGroupId(String fileGroupId) {
        this.fileGroupId = fileGroupId;
    }
    
}

package com.omninos.gwappuser.modelClasses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserLoginModel {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("details")
    @Expose
    private Details details;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }


    public class Details {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("password")
        @Expose
        private String password;
        @SerializedName("otp")
        @Expose
        private String otp;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("phoneVerifyStatus")
        @Expose
        private String phoneVerifyStatus;
        @SerializedName("device_type")
        @Expose
        private String deviceType;
        @SerializedName("reg_id")
        @Expose
        private String regId;
        @SerializedName("login_type")
        @Expose
        private String loginType;
        @SerializedName("created")
        @Expose
        private String created;
        @SerializedName("updated")
        @Expose
        private String updated;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPhoneVerifyStatus() {
            return phoneVerifyStatus;
        }

        public void setPhoneVerifyStatus(String phoneVerifyStatus) {
            this.phoneVerifyStatus = phoneVerifyStatus;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getRegId() {
            return regId;
        }

        public void setRegId(String regId) {
            this.regId = regId;
        }

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
            this.loginType = loginType;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

    }
}

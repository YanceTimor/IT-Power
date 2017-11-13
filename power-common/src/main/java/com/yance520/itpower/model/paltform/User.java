package com.yance520.itpower.model.paltform;

import com.alibaba.fastjson.JSON;
import com.yance520.itpower.model.base.AuditAuto;

import java.util.Date;
import java.util.List;

/**
 * 前端用户基础信息表
 *
 * @author yangyang
 * @email 1414512858@qq.com
 * @date 2017-11-07 19:10:28
 */
public class User extends AuditAuto {

    //登录用户名
    private String userName;
    //密码
    private String password;
    //真实姓名
    private String trueName;
    //昵称
    private String nickName;
    //微信号
    private String wechat;
    //身份证号
    private String idCard;
    //用户头衔
    private String userTitle;
    //头像
    private String avatar;
    //职称（例如：特邀认证作者）
    private String title;
    //机构
    private String organization;
    //简介
    private String introduction;
    //是否第三方会员
    private String isThirdSite;
    //第三方会员类型
    private Integer thirdSiteUserType;
    //第三方会员Id
    private String thirdSiteUserId;
    //邮箱
    private String email;
    //01:已绑定 02:未绑定
    private String emailBind;
    //验证码
    private String emailBindCode;
    //所在公司，工作单位
    private String company;
    //职位
    private String career;
    //学位
    private String degree;
    //用户类型 1、普通用户 2、加V认证用户 3、加V专栏权限用户 4、加V活动权限用户 5、加V所有权限用户 6、主持人用户 7、嘉宾用户
    private Integer type;
    //名片图片url
    private String cardUrl;
    //名片上传时间
    private Date cardUploadTime;
    //名片审核时间
    private Date cardVerifyTime;
    //名片审核状态 0、认证未通过 1、认证通过 2、未认证 3、申请认证
    private String cardVerifyStatus;
    //名片审核状态说明
    private String cardVerifyNote;
    //省
    private String province;
    //市
    private String city;
    //区县
    private String county;
    //地址
    private String address;
    //邮编
    private String zip;
    //电话
    private String tel;
    //手机
    private String mobile;
    //性别
    private String sex;
    //生日
    private Date birthday;
    //是否接受短信 0、是 1、否'
    private String isReceiveMsg;
    //是否启用 0、是 1、否'
    private String isEnable;
    //是否注销 0、是 1、否
    private String isCanceled;
    //是否黑名单 0、是 1、否
    private String isBlackUser;
    //逻辑删除，0正常显示，1前后端都看不到，数据库可查
    private Long isDeleted;

    //角色id列表
    private List<Long> roleIdList;
    //角色实体类列表
    private List<Role> roleList;

    /**
     * 设置：登录用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * 获取：登录用户名
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置：真实姓名
     */
    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }
    /**
     * 获取：真实姓名
     */
    public String getTrueName() {
        return trueName;
    }
    /**
     * 设置：昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    /**
     * 获取：昵称
     */
    public String getNickName() {
        return nickName;
    }
    /**
     * 设置：微信号
     */
    public void setWechat(String wechat) {
        this.wechat = wechat;
    }
    /**
     * 获取：微信号
     */
    public String getWechat() {
        return wechat;
    }
    /**
     * 设置：身份证号
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
    /**
     * 获取：身份证号
     */
    public String getIdCard() {
        return idCard;
    }
    /**
     * 设置：用户头衔
     */
    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle;
    }
    /**
     * 获取：用户头衔
     */
    public String getUserTitle() {
        return userTitle;
    }
    /**
     * 设置：头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    /**
     * 获取：头像
     */
    public String getAvatar() {
        return avatar;
    }
    /**
     * 设置：职称（例如：特邀认证作者）
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * 获取：职称（例如：特邀认证作者）
     */
    public String getTitle() {
        return title;
    }
    /**
     * 设置：机构
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }
    /**
     * 获取：机构
     */
    public String getOrganization() {
        return organization;
    }
    /**
     * 设置：简介
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    /**
     * 获取：简介
     */
    public String getIntroduction() {
        return introduction;
    }
    /**
     * 设置：是否第三方会员
     */
    public void setIsThirdSite(String isThirdSite) {
        this.isThirdSite = isThirdSite;
    }
    /**
     * 获取：是否第三方会员
     */
    public String getIsThirdSite() {
        return isThirdSite;
    }
    /**
     * 设置：第三方会员类型
     */
    public void setThirdSiteUserType(Integer thirdSiteUserType) {
        this.thirdSiteUserType = thirdSiteUserType;
    }
    /**
     * 获取：第三方会员类型
     */
    public Integer getThirdSiteUserType() {
        return thirdSiteUserType;
    }
    /**
     * 设置：第三方会员Id
     */
    public void setThirdSiteUserId(String thirdSiteUserId) {
        this.thirdSiteUserId = thirdSiteUserId;
    }
    /**
     * 获取：第三方会员Id
     */
    public String getThirdSiteUserId() {
        return thirdSiteUserId;
    }
    /**
     * 设置：邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 获取：邮箱
     */
    public String getEmail() {
        return email;
    }
    /**
     * 设置：01:已绑定 02:未绑定
     */
    public void setEmailBind(String emailBind) {
        this.emailBind = emailBind;
    }
    /**
     * 获取：01:已绑定 02:未绑定
     */
    public String getEmailBind() {
        return emailBind;
    }
    /**
     * 设置：验证码
     */
    public void setEmailBindCode(String emailBindCode) {
        this.emailBindCode = emailBindCode;
    }
    /**
     * 获取：验证码
     */
    public String getEmailBindCode() {
        return emailBindCode;
    }
    /**
     * 设置：所在公司，工作单位
     */
    public void setCompany(String company) {
        this.company = company;
    }
    /**
     * 获取：所在公司，工作单位
     */
    public String getCompany() {
        return company;
    }
    /**
     * 设置：职位
     */
    public void setCareer(String career) {
        this.career = career;
    }
    /**
     * 获取：职位
     */
    public String getCareer() {
        return career;
    }
    /**
     * 设置：学位
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }
    /**
     * 获取：学位
     */
    public String getDegree() {
        return degree;
    }
    /**
     * 设置：用户类型 1、普通用户 2、加V认证用户 3、加V专栏权限用户 4、加V活动权限用户 5、加V所有权限用户 6、主持人用户 7、嘉宾用户
     */
    public void setType(Integer type) {
        this.type = type;
    }
    /**
     * 获取：用户类型 1、普通用户 2、加V认证用户 3、加V专栏权限用户 4、加V活动权限用户 5、加V所有权限用户 6、主持人用户 7、嘉宾用户
     */
    public Integer getType() {
        return type;
    }
    /**
     * 设置：名片图片url
     */
    public void setCardUrl(String cardUrl) {
        this.cardUrl = cardUrl;
    }
    /**
     * 获取：名片图片url
     */
    public String getCardUrl() {
        return cardUrl;
    }
    /**
     * 设置：名片上传时间
     */
    public void setCardUploadTime(Date cardUploadTime) {
        this.cardUploadTime = cardUploadTime;
    }
    /**
     * 获取：名片上传时间
     */
    public Date getCardUploadTime() {
        return cardUploadTime;
    }
    /**
     * 设置：名片审核时间
     */
    public void setCardVerifyTime(Date cardVerifyTime) {
        this.cardVerifyTime = cardVerifyTime;
    }
    /**
     * 获取：名片审核时间
     */
    public Date getCardVerifyTime() {
        return cardVerifyTime;
    }
    /**
     * 设置：名片审核状态 0、认证未通过 1、认证通过 2、未认证 3、申请认证
     */
    public void setCardVerifyStatus(String cardVerifyStatus) {
        this.cardVerifyStatus = cardVerifyStatus;
    }
    /**
     * 获取：名片审核状态 0、认证未通过 1、认证通过 2、未认证 3、申请认证
     */
    public String getCardVerifyStatus() {
        return cardVerifyStatus;
    }
    /**
     * 设置：名片审核状态说明
     */
    public void setCardVerifyNote(String cardVerifyNote) {
        this.cardVerifyNote = cardVerifyNote;
    }
    /**
     * 获取：名片审核状态说明
     */
    public String getCardVerifyNote() {
        return cardVerifyNote;
    }
    /**
     * 设置：省
     */
    public void setProvince(String province) {
        this.province = province;
    }
    /**
     * 获取：省
     */
    public String getProvince() {
        return province;
    }
    /**
     * 设置：市
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * 获取：市
     */
    public String getCity() {
        return city;
    }
    /**
     * 设置：区县
     */
    public void setCounty(String county) {
        this.county = county;
    }
    /**
     * 获取：区县
     */
    public String getCounty() {
        return county;
    }
    /**
     * 设置：地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * 获取：地址
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置：邮编
     */
    public void setZip(String zip) {
        this.zip = zip;
    }
    /**
     * 获取：邮编
     */
    public String getZip() {
        return zip;
    }
    /**
     * 设置：电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }
    /**
     * 获取：电话
     */
    public String getTel() {
        return tel;
    }
    /**
     * 设置：手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**
     * 获取：手机
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置：性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }
    /**
     * 获取：性别
     */
    public String getSex() {
        return sex;
    }
    /**
     * 设置：生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    /**
     * 获取：生日
     */
    public Date getBirthday() {
        return birthday;
    }
    /**
     * 设置：是否接受短信 0、是 1、否'
     */
    public void setIsReceiveMsg(String isReceiveMsg) {
        this.isReceiveMsg = isReceiveMsg;
    }
    /**
     * 获取：是否接受短信 0、是 1、否'
     */
    public String getIsReceiveMsg() {
        return isReceiveMsg;
    }
    /**
     * 设置：是否启用 0、是 1、否'
     */
    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }
    /**
     * 获取：是否启用 0、是 1、否'
     */
    public String getIsEnable() {
        return isEnable;
    }
    /**
     * 设置：是否注销 0、是 1、否
     */
    public void setIsCanceled(String isCanceled) {
        this.isCanceled = isCanceled;
    }
    /**
     * 获取：是否注销 0、是 1、否
     */
    public String getIsCanceled() {
        return isCanceled;
    }
    /**
     * 设置：是否黑名单 0、是 1、否
     */
    public void setIsBlackUser(String isBlackUser) {
        this.isBlackUser = isBlackUser;
    }
    /**
     * 获取：是否黑名单 0、是 1、否
     */
    public String getIsBlackUser() {
        return isBlackUser;
    }
    /**
     * 设置：逻辑删除，0正常显示，1前后端都看不到，数据库可查
     */
    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }
    /**
     * 获取：逻辑删除，0正常显示，1前后端都看不到，数据库可查
     */
    public Long getIsDeleted() {
        return isDeleted;
    }

    public List<Long> getRoleIdList() {
        return roleIdList;
    }

    public void setRoleIdList(List<Long> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

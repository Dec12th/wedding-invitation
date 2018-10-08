package com.baily.template.weddinginvitation.domain;

import com.baily.template.weddinginvitation.common.db.entity.IUser;

import java.util.List;

public interface IUserService {

    /**
     * 根据用户名密码获取user
     *
     * @param account
     * @param password
     * @return
     */
    public IUser getUserByLoginAndPwd(String account, String password);

    /**
     * 保存用户
     *
     * @param user
     */
    public void saveUser(IUser user);

    /**
     * 关联用户
     *
     * @param user
     */
    public void updateUser(IUser user);

    /**
     * 根据身份证号、手机号获取user
     *
     * @param idcard
     * @param phone
     * @return
     */
    public IUser getUserByIdCardAndPhone(String idcard, String phone);

    /**
     * 根据ID获取user
     *
     * @param openId
     * @return
     */
    public IUser getUserById(String openId);

    /**
     * 获取所有用户信息
     *
     * @return
     */
    public List<IUser> getUsers();

    /**
     * 根据openid获取user
     *
     * @param openId
     * @return
     */
    public IUser getUserByOpenId(String openId);

    /**
     * 根据身份证号获取user
     *
     * @param idCard
     * @return
     */
    public IUser getUserByuserIdCard(String idCard);
}

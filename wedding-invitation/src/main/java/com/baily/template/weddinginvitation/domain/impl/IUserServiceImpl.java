package com.baily.template.weddinginvitation.domain.impl;

import com.baily.template.weddinginvitation.common.db.entity.IUser;
import com.baily.template.weddinginvitation.common.db.repository.IUserRepository;
import com.baily.template.weddinginvitation.domain.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qubin
 * @date 2017-07-21
 */

@Service("iUserService")
public class IUserServiceImpl implements IUserService {

    @Autowired
    IUserRepository iUserRepository;


	/**
	 * 根据用户名密码获取user
	 * @param account
	 * @param password
	 * @return
	 */
	@Override
	public IUser getUserByLoginAndPwd(String account, String password) {
		IUser iUser = new IUser();
		iUser.setAccount(account);
        iUser.setPassword(password);
        Example<IUser> userExample = Example.of(iUser);
        return iUserRepository.findOne(userExample);
	}

	/**
	 * 保存用户
	 * @param user
	 */
    @Override
	public void saveUser(IUser user){
        iUserRepository.save(user);
	}
	/**
	 * 关联用户
	 * @param user
	 */
    @Override
	public void updateUser(IUser user){
        iUserRepository.saveAndFlush(user);
	}

	/**
	 * 根据身份证号、手机号获取user
	 * @param idcard
	 * @param phone
	 * @phone
	 */
    @Override
	public IUser getUserByIdCardAndPhone(String idcard, String phone) {
        IUser iUser = new IUser();
        iUser.setIdCard(idcard);
        iUser.setPhone(phone);
        Example<IUser> userExample = Example.of(iUser);
        return iUserRepository.findOne(userExample);
	}

	/**
	 * 根据ID获取user
	 * @param id
	 * @return
	 */
    @Override
	public IUser getUserById(String id) {
        return iUserRepository.getOne(id);
	}

	/**
	 * 获取所有用户信息
	 * @return
	 */
    @Override
	public List<IUser> getUsers() {
        return iUserRepository.findAll();
	}
	/**
	 * 根据openid获取user
	 * @param openId
	 * @return
	 */
    @Override
	public IUser getUserByOpenId(String openId) {
        IUser iUser = new IUser();
        iUser.setOpenId(openId);
        Example<IUser> userExample = Example.of(iUser);
        return iUserRepository.findOne(userExample);
	}

	/**
	 * 根据身份证号获取user
	 * @param idCard
	 * @return
	 */
    @Override
	public IUser getUserByuserIdCard(String idCard) {
        IUser iUser = new IUser();
        iUser.setIdCard(idCard);
        Example<IUser> userExample = Example.of(iUser);
        return iUserRepository.findOne(userExample);
	}
}

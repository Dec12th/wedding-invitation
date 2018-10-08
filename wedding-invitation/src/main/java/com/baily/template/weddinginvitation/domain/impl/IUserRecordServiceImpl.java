package com.baily.template.weddinginvitation.domain.impl;

import com.baily.template.weddinginvitation.common.db.entity.IUserRecord;
import com.baily.template.weddinginvitation.common.db.repository.IUserRecordRepository;
import com.baily.template.weddinginvitation.domain.IUserRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qubin
 * @date 2017-07-21
 */

@Service("iUserRecordService")
public class IUserRecordServiceImpl implements IUserRecordService {

    @Autowired
	IUserRecordRepository iUserRecordRepository;


	/**
	 * 根据用户名密码获取user
	 * @param account
	 * @param password
	 * @return
	 */
	@Override
	public IUserRecord getUserByLoginAndPwd(String account, String password) {
		IUserRecord iUser = new IUserRecord();
		iUser.setAccount(account);
        iUser.setPassword(password);
        Example<IUserRecord> userExample = Example.of(iUser);
        return iUserRecordRepository.findOne(userExample);
	}

	/**
	 * 保存用户
	 * @param user
	 */
    @Override
	public void saveUser(IUserRecord user){
		iUserRecordRepository.save(user);
	}
	/**
	 * 关联用户
	 * @param user
	 */
    @Override
	public void updateUser(IUserRecord user){
		iUserRecordRepository.saveAndFlush(user);
	}

	/**
	 * 根据身份证号、手机号获取user
	 * @param idcard
	 * @param phone
	 * @phone
	 */
    @Override
	public IUserRecord getUserByIdCardAndPhone(String idcard, String phone) {
		IUserRecord iUser = new IUserRecord();
        iUser.setIdCard(idcard);
        iUser.setPhone(phone);
        Example<IUserRecord> userExample = Example.of(iUser);
        return iUserRecordRepository.findOne(userExample);
	}

	/**
	 * 根据ID获取user
	 * @param id
	 * @return
	 */
    @Override
	public IUserRecord getUserById(String id) {
        return iUserRecordRepository.getOne(id);
	}

	/**
	 * 获取所有用户信息
	 * @return
	 */
    @Override
	public List<IUserRecord> getUsers() {
        return iUserRecordRepository.findAll();
	}
	/**
	 * 根据openid获取user
	 * @param openId
	 * @return
	 */
    @Override
	public IUserRecord getUserByOpenId(String openId) {
		IUserRecord iUser = new IUserRecord();
        iUser.setOpenId(openId);
        Example<IUserRecord> userExample = Example.of(iUser);
        return iUserRecordRepository.findOne(userExample);
	}

	/**
	 * 根据身份证号获取user
	 * @param idCard
	 * @return
	 */
    @Override
	public IUserRecord getUserByuserIdCard(String idCard) {
		IUserRecord iUser = new IUserRecord();
        iUser.setIdCard(idCard);
        Example<IUserRecord> userExample = Example.of(iUser);
        return iUserRecordRepository.findOne(userExample);
	}
}

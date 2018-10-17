package com.baily.template.weddinginvitation.domain.impl;

import com.baily.template.weddinginvitation.common.db.entity.BlessComment;
import com.baily.template.weddinginvitation.common.db.entity.BlessUser;
import com.baily.template.weddinginvitation.common.db.repository.BlessCommentRepository;
import com.baily.template.weddinginvitation.common.db.repository.BlessUserRepository;
import com.baily.template.weddinginvitation.domain.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * @author wangsong
 *
 */
@Service("mobileService")
//@Transactional
public class MobileServiceImpl implements MobileService {

    @Autowired
	BlessUserRepository blessUserRepository;

    @Autowired
    BlessCommentRepository blessCommentRepository;


	//根据nick_image查找
    @Override
	public List<BlessUser> getBlessUserByNickImage(String nick_image){
        return blessUserRepository.findAllByNickImage(nick_image);
	}

	//根据open_id查找
    @Override
	public List<BlessUser> getBlessUserByOpenId(String open_id){
        return blessUserRepository.findAllByOpenId(open_id);
	}

	//获取赞列表
    @Override
	public List<BlessUser> getAllBlessUser(){
        return blessUserRepository.findAll(new Sort(Sort.Direction.DESC,"createTime"));
	}

	//获取评论列表
    @Override
	public List<BlessComment> getAllBlessComment(){
        return blessCommentRepository.findAll(new Sort(Sort.Direction.DESC,"createTime"));
	}

    @Override
	public void save(BlessUser blessUser) {
        blessUserRepository.save(blessUser);
	}

    @Override
	public void save(BlessComment blessComment) {
        blessCommentRepository.save(blessComment);
	}

	
}

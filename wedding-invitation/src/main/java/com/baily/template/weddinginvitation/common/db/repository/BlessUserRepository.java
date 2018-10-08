package com.baily.template.weddinginvitation.common.db.repository;

import com.baily.template.weddinginvitation.common.db.entity.BlessUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName: BlessUserRepository
 * @Description:
 * @author:大贝
 * @date:2018年10月08日 21:25
 */
public interface BlessUserRepository extends JpaRepository<BlessUser,String> {

    public List<BlessUser> findAllByNickImage(String nickImage);

    public List<BlessUser> findAllByOpenId(String openId);
}

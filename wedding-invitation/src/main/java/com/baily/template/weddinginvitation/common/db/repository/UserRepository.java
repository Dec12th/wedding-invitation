package com.baily.template.weddinginvitation.common.db.repository;

import com.baily.template.weddinginvitation.common.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: UserRepository
 * @Description:
 * @author:大贝
 * @date:2018年10月07日 20:49
 */
//@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}

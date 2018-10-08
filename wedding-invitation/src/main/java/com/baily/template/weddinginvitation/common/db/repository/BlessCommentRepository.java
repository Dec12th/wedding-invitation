package com.baily.template.weddinginvitation.common.db.repository;

import com.baily.template.weddinginvitation.common.db.entity.BlessComment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName: BlessUserRepository
 * @Description:
 * @author:大贝
 * @date:2018年10月08日 21:25
 */
public interface BlessCommentRepository extends JpaRepository<BlessComment,String> {

}

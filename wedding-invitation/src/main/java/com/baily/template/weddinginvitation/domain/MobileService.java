package com.baily.template.weddinginvitation.domain;

import com.baily.template.weddinginvitation.common.db.entity.BlessComment;
import com.baily.template.weddinginvitation.common.db.entity.BlessUser;

import java.util.List;

/**
 * @ClassName: MobileService
 * @Description:
 * @author:大贝
 * @date:2018年10月08日 21:29
 */
public interface MobileService {

    public List<BlessUser> getBlessUserByNickImage(String nick_image);

    public List<BlessUser> getBlessUserByOpenId(String open_id);

    public List<BlessUser> getAllBlessUser();

    public List<BlessComment> getAllBlessComment();

    public void save(BlessUser blessUser);

    public void save(BlessComment blessUser);
}

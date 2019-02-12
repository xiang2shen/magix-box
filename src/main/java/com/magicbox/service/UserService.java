package com.magicbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicbox.base.support.Page;
import com.magicbox.base.utilities.XCollectionUtils;
import com.magicbox.mapper.UserMapper;
import com.magicbox.model.User;
import com.magicbox.model.UserExample;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    protected Page<User> selectPageByExample(UserExample example) {
        return new Page<>(example.getPageNo(), example.getLimit(), userMapper.selectByExample(example), userMapper.countByExample(example));
    }

	public User selectOneByUsername(String username) {
		UserExample example = new UserExample();
		example.or().andUsernameEqualTo(username);
		
		return XCollectionUtils.getFirstElement(userMapper.selectByExample(example));
	}
}
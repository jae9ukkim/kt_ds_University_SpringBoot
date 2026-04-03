package org.themoviedb.members.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.themoviedb.members.dao.MembersDao;

@Service
public class MembersServiceImpl implements MembersService {

	@Autowired
	private MembersDao membersDao;
}

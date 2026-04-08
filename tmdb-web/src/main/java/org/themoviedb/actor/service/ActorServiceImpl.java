package org.themoviedb.actor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.themoviedb.actor.dao.ActorDao;
import org.themoviedb.actor.vo.request.WriteVO;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorDao actorDao;
    
    @Transactional
    @Override
    public Boolean createNewActor(WriteVO writeVO) {
        
        int insertResult = this.actorDao.insertNewActor(writeVO);
        
        return insertResult == 1;
    }

}

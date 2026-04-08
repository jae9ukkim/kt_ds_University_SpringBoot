package org.themoviedb.actor.dao;

import org.apache.ibatis.annotations.Mapper;
import org.themoviedb.actor.vo.request.WriteVO;

@Mapper
public interface ActorDao {

    int insertNewActor(WriteVO writeVO);

}

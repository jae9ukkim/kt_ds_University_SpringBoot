package org.themoviedb.actor.service;

import org.themoviedb.actor.vo.request.WriteVO;

public interface ActorService {

    Boolean createNewActor(WriteVO writeVO);

}

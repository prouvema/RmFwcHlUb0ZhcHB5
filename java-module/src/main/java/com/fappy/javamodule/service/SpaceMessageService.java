package com.fappy.javamodule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fappy.javamodule.domain.entity.space.SpaceMessage;
import com.fappy.javamodule.repository.SpaceMessageRepository;

@Service
public class SpaceMessageService {

	@Autowired
	private SpaceMessageRepository spaceMessageRepository;

	public Page<SpaceMessage> findBySpace(long spaceId, Pageable pageRequest) {
		return this.spaceMessageRepository.findBySpaceId(spaceId, pageRequest);
	}

	public SpaceMessage save(SpaceMessage spaceMessage) {
		return this.spaceMessageRepository.saveAndFlush(spaceMessage);
	}
	
	
}

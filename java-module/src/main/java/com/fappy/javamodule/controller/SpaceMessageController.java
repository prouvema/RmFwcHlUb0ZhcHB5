package com.fappy.javamodule.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fappy.javamodule.builder.dto.SpaceMessageDTOBuilder;
import com.fappy.javamodule.builder.entity.SpaceMessageBuidler;
import com.fappy.javamodule.domain.entity.space.SpaceMessage;
import com.fappy.javamodule.dto.SpaceMessageDTO;
import com.fappy.javamodule.service.FamilySpaceService;
import com.fappy.javamodule.service.SpaceMessageService;
import com.fappy.javamodule.service.UserService;

@RestController
@RequestMapping(path="/api/spacemessages")
public class SpaceMessageController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private FamilySpaceService familySpaceService;
	
	@Autowired
	private SpaceMessageService spaceMessageService;
	
	@GetMapping(path = "space/{spaceId}/page/{page}/size/{size}")
	public ResponseEntity<List<SpaceMessageDTO>> findByPagination(@PathVariable long spaceId, @PathVariable int page, @PathVariable int size) {
		Pageable pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "createTime");
		Page<SpaceMessage> messagesPage = this.spaceMessageService.findBySpaceAndPageable(spaceId, pageRequest);
		List<SpaceMessageDTO> dtos = messagesPage.stream()
			.map(message -> new SpaceMessageDTOBuilder().withSpaceMessage(message).build())
			.collect(Collectors.toList());
		return ResponseEntity.ok(dtos);
	}
	
	@PostMapping
	public ResponseEntity<SpaceMessageDTO> post(@RequestBody SpaceMessageDTO dto) {
		SpaceMessage spaceMessage = new SpaceMessageBuidler(this.userService, this.familySpaceService)
				.withSpaceMessageDto(dto)
				.build();
		SpaceMessage spaceMessageSaved = this.spaceMessageService.save(spaceMessage);
		SpaceMessageDTO dtoSaved = new SpaceMessageDTOBuilder().withSpaceMessage(spaceMessageSaved).build();
		return ResponseEntity.ok(dtoSaved);
	}
}

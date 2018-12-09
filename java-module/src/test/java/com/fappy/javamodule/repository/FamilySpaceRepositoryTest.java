package com.fappy.javamodule.repository;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fappy.javamodule.domain.entity.space.FamilySpace;
import com.fappy.javamodule.test.builder.FamilySpaceTestBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FamilySpaceRepositoryTest {

	@Autowired
	private FamilySpaceRepository familySpaceRepository;
	
	@Test
	public void should_succeed_the_crud_operation() {
		
		FamilySpace familySpace = new FamilySpaceTestBuilder().withNotNullField().build();
		FamilySpace familySpaceSaved = this.familySpaceRepository.save(familySpace);
		
		assertNotNull(familySpaceSaved);
		assertTrue(familySpaceSaved.getId() > 0);
		
		Optional<FamilySpace> familySpaceReaded = this.familySpaceRepository.findById(familySpaceSaved.getId());
		
		assertTrue(familySpaceReaded.isPresent());
		
		familySpaceSaved.setName("Name updated");
		FamilySpace familySpaceUpdated = this.familySpaceRepository.save(familySpaceSaved);
		
		assertNotNull(familySpaceUpdated);
		assertEquals("Name updated", familySpaceSaved.getName());
		
		this.familySpaceRepository.delete(familySpaceUpdated);
		Optional<FamilySpace> familySpaceDeleted = this.familySpaceRepository.findById(familySpaceUpdated.getId());
		
		assertFalse(familySpaceDeleted.isPresent());
	}
}

package com.fappy.javamodule.test.builder;

import com.fappy.javamodule.domain.entity.space.FamilySpace;

public class FamilySpaceTestBuilder {

	private FamilySpace familySpace;
	
	public FamilySpaceTestBuilder() {
		super();
		this.familySpace = new FamilySpace();
	}
	
	public FamilySpaceTestBuilder withNotNullField() {
		this.familySpace.setName("FamilySpaceTest");
		return this;
	}

	public FamilySpace build() {
		return this.familySpace;
	}

}

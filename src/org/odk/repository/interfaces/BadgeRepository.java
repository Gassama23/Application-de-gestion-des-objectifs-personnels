package org.odk.repository.interfaces;

import java.util.List;

import org.odk.model.Badge;

public interface BadgeRepository {
	
	Badge sauvergarder(Badge bdg);

	 Badge findById(int id);

	 List<Badge> findAll();
}

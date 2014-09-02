package org.networkedassets.atlassian.stash.personalstash.group;

import java.util.Set;

import com.atlassian.stash.user.StashUser;

public interface StoredGroupsService {

	Set<String> getAll();

	Set<String> add(Set<String> groupNames);

	void remove(Set<String> groupNames);

	void removeAll();

	boolean isAllowed(String group);

	boolean isDenied(String group);

}
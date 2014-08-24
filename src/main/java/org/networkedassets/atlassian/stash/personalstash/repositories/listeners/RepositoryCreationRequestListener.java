package org.networkedassets.atlassian.stash.personalstash.repositories.listeners;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.networkedassets.atlassian.stash.personalstash.auth.UserPermissionsExaminer;
import org.networkedassets.atlassian.stash.personalstash.license.LicenseManager;
import org.networkedassets.atlassian.stash.personalstash.repositories.RepositoryTypeVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.stash.event.RepositoryCreationRequestedEvent;
import com.atlassian.stash.i18n.KeyedMessage;

@Component
public class RepositoryCreationRequestListener {

	@Autowired
	private UserPermissionsExaminer userPermissionsExaminer;
	@Autowired
	private RepositoryTypeVerifier repositoryTypeVerifier;
	@Autowired
	private EventPublisher eventPublisher;
	@Autowired
	private LicenseManager licenseManager;

	private final Logger log = LoggerFactory
			.getLogger(RepositoryCreationRequestListener.class);

	@PostConstruct
	public void registerEvents() {
		eventPublisher.register(this);
	}

	@PreDestroy
	public void unregisterEvents() {
		eventPublisher.unregister(this);
	}

	@EventListener
	public void handleCreationEvent(RepositoryCreationRequestedEvent event) {
		if (licenseManager.isLicenseInvalid()) {
			return;
		}
		log.debug("Repository Creation Request received");
		if (repositoryTypeVerifier.isPersonal(event.getRepository())) {
			log.debug("Repository is personal");
			if (!userPermissionsExaminer.canUsePersonalRepositories()) {
				log.debug("User can't use presonal repositories");
				event.cancel(new KeyedMessage("lol", "You don't have permissions to create personal repositories", "fwefw"));
			}
		}
	}

}

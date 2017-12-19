package org.openmrs.module.bahmnicore.i18n.impl;

import java.util.List;

import org.openmrs.annotation.OpenmrsProfile;
import org.openmrs.module.bahmnicore.i18n.Internationalizer;
import org.openmrs.module.exti18n.ExtI18nConstants;
import org.openmrs.module.exti18n.api.AddressHierarchyI18nCache;
import org.openmrs.module.exti18n.api.ReverseI18nCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@OpenmrsProfile(modules = {"exti18n:*"})
public class InternationalizerImpl implements Internationalizer {

	@Autowired
	@Qualifier(ExtI18nConstants.COMPONENT_AH_REVI18N)
	private AddressHierarchyI18nCache ahCache;
	
	@Autowired
	@Qualifier(ExtI18nConstants.COMPONENT_REVI18N)
	private ReverseI18nCache cache = ahCache;
	
	@Override
	public boolean isEnabled() {
		return cache.isEnabled() || ahCache.isEnabled();
	}
	
	@Override
	public String getMessageKey(String msg) {
		return cache.getMessageKey(ahCache.getMessageKey(msg));
	}

	@Override
	public List<String> getAddressMessageKeysByLikeName(String addressSearchString) {
		return ahCache.getMessageKeysByLikeName(addressSearchString);
	}
}
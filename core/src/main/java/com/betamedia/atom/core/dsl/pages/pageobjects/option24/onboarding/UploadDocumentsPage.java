package com.betamedia.atom.core.dsl.pages.pageobjects.option24.onboarding;

/**
 * @author Leonid Artemiev
 * @since 7/20/17
 */
public interface UploadDocumentsPage {
    public boolean exists();

	void goToDocumentsUpload();

	void selsetPOI();

	void browseForFileUpload();
}

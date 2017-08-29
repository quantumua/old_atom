package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding;

import com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations;
/**
 * Created by vsnigur on 6/7/17.
 */
public interface UploadDocumentsDialog extends LocalizationOperations {

    boolean exists();

    boolean porCheckmarkImageExists();

    boolean porXImageExists();

    UploadDocumentsDialog porUploadElectricityBill(String imagePath);

    void poiClickHeader();

    UploadDocumentsDialog poiUploadIdCard();

    UploadDocumentsDialog poiUploadIdCard(String frontImagePath, String backImagePath);

    UploadDocumentsDialog poiUploadPassport(String imagePath);

    boolean poiXImageExists();

    UploadDocumentsDialog poiUploadDriverLicense(String frontImagePath, String backImagePath);

    boolean poiBackImageExists();

    void porClickHeader();

    boolean isExitButtonExists();

    boolean isPoiExpanded();

    boolean isPoiCollapsed();

    boolean isPorExpanded();

    boolean isPorCollapsed();

    void close();
}
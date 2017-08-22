package com.betamedia.atom.core.dsl.pages.pageobjects.option24.web.onboarding;

import com.betamedia.atom.core.dsl.pages.extensions.LocalizationOperations;
/**
 * Created by vsnigur on 6/7/17.
 */
public interface UploadDocumentDialog extends LocalizationOperations {

    boolean exists();

    boolean porCheckmarkImageExists();

    boolean porXImageExists();

    UploadDocumentDialog porUploadElectricityBill(String imagePath);

    void poiClickHeader();

    UploadDocumentDialog poiUploadIdCard();

    UploadDocumentDialog poiUploadIdCard(String frontImagePath, String backImagePath);

    UploadDocumentDialog poiUploadPassport(String imagePath);

    boolean poiXImageExists();

    UploadDocumentDialog poiUploadDriverLicense(String frontImagePath, String backImagePath);

    boolean poiBackImageExists();

    void porClickHeader();

    boolean isExitButtonExists();

    boolean isPoiExpanded();

    boolean isPoiCollapsed();

    boolean isPorExpanded();

    boolean isPorCollapsed();

    void close();
}
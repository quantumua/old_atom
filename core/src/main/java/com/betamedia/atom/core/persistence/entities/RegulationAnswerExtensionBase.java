package com.betamedia.atom.core.persistence.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by vsnigur on 9/7/17.
 */
@Entity
@Table(name = "bt_regulationanswerExtensionBase")
public class RegulationAnswerExtensionBase {

    @Id
    @Column(name = "bt_regulationanswerId", columnDefinition = "uniqueidentifier")
    private String regulationAnswerId;

    @Column(name = "bt_CustomerId")
    private String customerId;

    @Column(name = "bt_AnswerKey")
    private String answerKey;

    @Column(name = "bt_AnswerTextEnglish")
    private String answerTextEnglish;

    @Column(name = "bt_QuestionTextEnglish")
    private String questionTextEnglish;

    @Column(name = "bt_QuestionKey")
    private String questionKey;

    @Column(name = "bt_AnswerText")
    private String answerText;

    public String getRegulationAnswerId() {
        return regulationAnswerId;
    }

    public void setRegulationAnswerId(String regulationAnswerId) {
        this.regulationAnswerId = regulationAnswerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAnswerKey() {
        return answerKey;
    }

    public void setAnswerKey(String answerKey) {
        this.answerKey = answerKey;
    }

    public String getAnswerTextEnglish() {
        return answerTextEnglish;
    }

    public void setAnswerTextEnglish(String answerTextEnglish) {
        this.answerTextEnglish = answerTextEnglish;
    }

    public String getQuestionTextEnglish() {
        return questionTextEnglish;
    }

    public void setQuestionTextEnglish(String questionTextEnglish) {
        this.questionTextEnglish = questionTextEnglish;
    }

    public String getQuestionKey() {
        return questionKey;
    }

    public void setQuestionKey(String questionKey) {
        this.questionKey = questionKey;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}

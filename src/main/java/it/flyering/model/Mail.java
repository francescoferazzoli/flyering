/*
* Copyright (c) 2017, Liberty Mutual
* Proprietary and Confidential
* All Rights Reserved
*/
package it.flyering.model;

import java.util.List;
import java.util.Map;

/**
 * Class to represent mail sent by application.
 */
public class Mail {

    private String from;
    private String to;
    private String subject;
    private String details;
    private List<Object> attachments;
    private Map<String, Object> model;

    public Mail() {

    }

    /**
     * Gets address email was sent from.
     *
     * @return email address of mail sender
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets address email was sent from.
     *
     * @param from email address of mail sender
     */
    public void setFrom(final String from) {
        this.from = from;
    }

    /**
     * Gets address email will be sent to.
     *
     * @return email address mail will be sent to
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets address email will be sent to.
     *
     * @param to email address mail will be sent to
     */
    public void setTo(final String to) {
        this.to = to;
    }

    /**
     * Gets subject of email.
     *
     * @return subject of email
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets subject of email.
     *
     * @param subject subject of email
     */
    public void setSubject(final String subject) {
        this.subject = subject;
    }

    /**
     * Gets email attachments.
     *
     * @return email attachments
     */
    public List<Object> getAttachments() {
        return attachments;
    }

    /**
     * Sets email attachements.
     *
     * @param attachments email attachments
     */
    public void setAttachments(final List<Object> attachments) {
        this.attachments = attachments;
    }

    /**
     * Gets model of email.
     *
     * @return model of email
     */
    public Map<String, Object> getModel() {
        return model;
    }

    /**
     * Sets model of email.
     *
     * @param model model of email
     */
    public void setModel(final Map<String, Object> model) {
        this.model = model;
    }

    /**
     * Gets the user input for the details field.
     *
     * @return details input by the claim submitter
     */
    public String getDetails() {
        return details;
    }

    /**
     * Gets the user input for the details field.
     *
     * @param details details input by the claim submitter
     */
    public void setDetails(final String details) {
        this.details = details;
    }
}

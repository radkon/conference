/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.datamodel.Speaker;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@Named
@SessionScoped
public class SpeakerBean implements Serializable {

    @EJB
    private SpeakerResource speakerResource;

    private Speaker speaker = new Speaker();

    private List<Speaker> speakerList;

    public void saveSpeaker() {
        this.speakerResource.save(this.speaker);
        reset();
    }

    private void reset() {
        this.speaker = new Speaker();
        this.speakerList = null;
    }

    public List<Speaker> getSpeakers() {
        if (this.speakerList == null || this.speakerList.isEmpty()) {
            this.speakerList = this.speakerResource.findAll();
        }
        return this.speakerList;
    }

    public Speaker getSpeaker() {
        return this.speaker;
    }

}

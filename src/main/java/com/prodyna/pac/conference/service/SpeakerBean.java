package com.prodyna.pac.conference.service;

import com.prodyna.pac.conference.entity.Speaker;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Markus Konrad <markus.konrad@prodyna.com>
 */
@Named("speakerBean")
@SessionScoped
public class SpeakerBean implements Serializable {

    private static final long serialVersionUID = 6538999479484882364L;

    @EJB
    private SpeakerResource speakerResource;

    private Speaker speaker = new Speaker();

    private List<Speaker> speakerList;

    public void saveSpeaker() {
        this.speakerResource.create(speaker);
        reset();
    }

    private void reset() {
        this.speaker = new Speaker();
        this.speakerList = null;
    }

    public List<Speaker> getSpeakerList() {
        if (this.speakerList == null || this.speakerList.isEmpty()) {
            this.speakerList = this.speakerResource.findAll();
        }
        return this.speakerList;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

}

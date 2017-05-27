package com.notoeat.talking.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by l on 2017/5/7.
 */
@Entity
public interface Talkable {
    @Id
    public Integer getId();
    public void setId(Integer id);
    @OneToOne
    public TalkingModel getTalking();
    public void setTalking(TalkingModel talking);
}

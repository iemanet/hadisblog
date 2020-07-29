package com.emanet.blog.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "rmzlhds")
public class Rmzlhds {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rmzlhds_id")
    private Long id;
    
    @Column(name = "subject")
    private String subject;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Column(name = "source")
    private String source;
    
    @Column(name = "page_id")
    private Long pageId;
    
    @Column(name = "sequence_id")
    private Long sequenceId;

    @OneToMany(mappedBy = "rmzlhds", cascade = CascadeType.REMOVE)
    private Collection<CommentRmzlhds> rmzlhdsComments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Long getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Long sequenceId) {
        this.sequenceId = sequenceId;
    }

    public Collection<CommentRmzlhds> getRmzlhdsComments() {
        return rmzlhdsComments;
    }

    public void setRmzlhdsComments(Collection<CommentRmzlhds> rmzlhdsComments) {
        this.rmzlhdsComments = rmzlhdsComments;
    }
}

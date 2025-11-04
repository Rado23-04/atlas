package com.atlas.modal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Table(name = "ideas",indexes = {
    @Index(name = "idx_ideas_user_id", columnList = "user_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Ideas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String markdown;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String summary;

    private Integer depthLevel;
    
    private String category;
    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;

    @ManyToMany
    @JoinTable(name = "idea_tags",joinColumns = @JoinColumn(name = "idea_id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @Builder.Default
    private Set<Tag> tags = new HashSet<>();

     
}

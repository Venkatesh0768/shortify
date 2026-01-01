package org.shortify.shortifybackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "url_mappings")
public class UrlMapping extends BaseModel {
    private String originalUrl;
    private String shortUrl;
    private int clickCount = 0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "urlMapping", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClickEvent> clickEvents;
}

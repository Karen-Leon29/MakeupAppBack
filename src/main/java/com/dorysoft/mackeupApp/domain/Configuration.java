package com.dorysoft.mackeupApp.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String principalImageUrl;

    @Column(length = 25)
    private String formatImageUrl;

    @Column()
    private String principalText;
}

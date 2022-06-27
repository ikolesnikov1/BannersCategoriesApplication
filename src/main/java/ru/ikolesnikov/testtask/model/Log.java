package ru.ikolesnikov.testtask.model;

import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "banner_id")
    private Banner banner;

    @Column(columnDefinition = "text", nullable = false)
    private String userAgent;

    @Column(nullable = false)
    private String ipAddress;

    @Column(nullable = false)
    private Date date;
}
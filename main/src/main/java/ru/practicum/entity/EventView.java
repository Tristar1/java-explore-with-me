package ru.practicum.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "EventView")
@Table(name = "event_views")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class EventView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "view_ip", nullable = false)
    private String ip;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventView)) return false;
        EventView view = (EventView) o;
        return Objects.equals(ip, view.ip)
                && Objects.equals(event, view.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ip, event);
    }

}

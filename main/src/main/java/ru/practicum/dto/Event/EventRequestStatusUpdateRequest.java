package ru.practicum.dto.Event;

import lombok.Data;
import ru.practicum.entity.State;

import java.util.Set;

@Data
public class EventRequestStatusUpdateRequest {

    Set<Long> requestIds;
    State status;

}

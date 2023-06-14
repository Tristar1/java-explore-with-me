package ru.practicum.dto.Event;

import lombok.Data;
import ru.practicum.entity.RequestState;

import java.util.Set;

@Data
public class EventRequestStatusUpdateRequest {

    Set<Long> requestIds;
    RequestState status;

}

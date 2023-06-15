package ru.practicum.service.Request;

import ru.practicum.dto.Event.EventRequestStatusUpdateRequest;
import ru.practicum.dto.Event.EventRequestStatusUpdateResult;
import ru.practicum.entity.Request;

import java.util.List;

public interface RequestService {

    List<Request> getAll(long userId);

    Request create(long userId, long eventId);

    List<Request> getEventRequests(long userId, long eventId);

    EventRequestStatusUpdateResult updateStatus(long userId, long eventId, EventRequestStatusUpdateRequest requestStatusUpdate);

    Request cancelRequest(long userId, long requestId);

}

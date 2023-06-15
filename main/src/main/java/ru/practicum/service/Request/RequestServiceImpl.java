package ru.practicum.service.Request;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.dto.Event.EventRequestStatusUpdateRequest;
import ru.practicum.dto.Event.EventRequestStatusUpdateResult;
import ru.practicum.entity.*;
import ru.practicum.error.Exception.AccessException;
import ru.practicum.error.Exception.ObjectNotFoundException;
import ru.practicum.mapper.RequestMapper;
import ru.practicum.repository.RequestRepository;
import ru.practicum.service.Event.EventService;
import ru.practicum.service.User.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final UserService userService;
    private final EventService eventService;

    @Override
    public List<Request> getAll(long userId) {
        userService.getById(userId);
        return requestRepository.findAllByRequesterId(userId);
    }

    @Override
    public Request create(long userId, long eventId) {
        User user = userService.getById(userId);
        Event event = Optional.of(eventService.getById(eventId)).orElseThrow(
                () -> new AccessException("Invalid request"));

        Request newRequest = Request.builder()
                .created(Timestamp.valueOf(LocalDateTime.now()))
                .requester(user)
                .event(event)
                .status(RequestState.PENDING)
                .build();

        if (event.getInitiator().getId() == userId
                || !event.getState().equals(EventState.PUBLISHED)
                || (event.getConfirmedRequests() >= event.getParticipantLimit() && event.getParticipantLimit() != 0)
                || requestRepository.findByRequesterAndAndEvent(user, event).isPresent()) {
            throw new AccessException("Invalid request");
        }

        if (!event.getRequestModeration() || event.getParticipantLimit() == 0) {
            newRequest.setStatus(RequestState.CONFIRMED);
        }

        newRequest = requestRepository.save(newRequest);

        event.setConfirmedRequests(getRequestsByEventAndStatus(event.getId(), RequestState.CONFIRMED));
        eventService.save(event);

        return newRequest;
    }

    @Override
    public List<Request> getEventRequests(long userId, long eventId) {
        eventService.getInitiatorEventById(eventId, userId);
        return requestRepository.findAllByEventId(eventId);
    }

    @Override
    public EventRequestStatusUpdateResult updateStatus(long userId, long eventId,
                                                       EventRequestStatusUpdateRequest requestStatusUpdate) {
        userService.getById(userId);
        var event = eventService.getById(eventId);
        var requests = requestRepository.findAllById(requestStatusUpdate.getRequestIds());

        var countConfirmations = 0;
        List<Request> requestsForUpdate = new ArrayList<>();

        for (Request request : requests) {
            if (eventId != request.getEvent().getId()) {
                throw new AccessException("Incorrect event");
            }

            if (!event.getRequestModeration() || event.getParticipantLimit() == 0
                    || event.getConfirmedRequests() >= event.getParticipantLimit()
                    || request.getStatus() != RequestState.PENDING) {
                throw new AccessException("Error: confirming request");
            }
            if (requestStatusUpdate.getStatus() == RequestState.CONFIRMED
                    && (event.getConfirmedRequests() + countConfirmations) < event.getParticipantLimit()) {
                request.setStatus(RequestState.CONFIRMED);
                requestsForUpdate.add(request);
                countConfirmations++;
            } else {
                request.setStatus(RequestState.REJECTED);
                requestsForUpdate.add(request);
            }
        }

        requests = requestRepository.saveAll(requestsForUpdate);

        event.setConfirmedRequests(getRequestsByEventAndStatus(event.getId(), RequestState.CONFIRMED));
        eventService.save(event);

        EventRequestStatusUpdateResult requestResult = new EventRequestStatusUpdateResult();
        requestResult.setConfirmedRequests(RequestMapper.toParticipationRequestDtoList(requests.stream()
                .filter(r -> r.getStatus() == RequestState.CONFIRMED)
                .collect(Collectors.toList())));
        requestResult.setRejectedRequests(RequestMapper.toParticipationRequestDtoList(requests.stream()
                .filter(r -> r.getStatus() == RequestState.REJECTED)
                .collect(Collectors.toList())));
        return requestResult;
    }

    @Override
    public Request cancelRequest(long userId, long requestId) {
        userService.getById(userId);
        Request request = getRequestById(requestId);
        request.setStatus(RequestState.CANCELED);
        return requestRepository.save(request);
    }

    private Request getRequestById(long requestId) {
        return requestRepository.findById(requestId).orElseThrow(
                () -> new ObjectNotFoundException("Request with id=" + requestId));
    }

    private Integer getRequestsByEventAndStatus(long eventId, RequestState status) {
        return requestRepository.findCountRequestByEventIdAndStatus(eventId, status).orElse(0);
    }

}

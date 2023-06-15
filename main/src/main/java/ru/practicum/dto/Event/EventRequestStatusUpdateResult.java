package ru.practicum.dto.Event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.dto.Request.ParticipationRequestDto;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestStatusUpdateResult {

    List<ParticipationRequestDto> confirmedRequests = new ArrayList<ParticipationRequestDto>();
    List<ParticipationRequestDto> rejectedRequests = new ArrayList<ParticipationRequestDto>();

}

package com.rocketseat.planner.participant;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/participant")
public class ParticipantController {

  @Autowired
  private ParticipantRepository participantRepository;

  @PatchMapping("/{id}/confirm")
  public ResponseEntity<Participant> confirmParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload) {
    Optional<Participant> participant = participantRepository.findById(id);

    if (participant.isPresent()) {
      Participant rawParticipant = participant.get();
      rawParticipant.setIsConfirmed(true);
      rawParticipant.setName(payload.name());

      this.participantRepository.save(rawParticipant);

      return ResponseEntity.ok(rawParticipant);
    }

    return ResponseEntity.notFound().build();
  }
}

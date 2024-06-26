package com.juny.dddstart2.eventstore.ui;

import com.juny.dddstart2.eventstore.api.EventEntry;
import com.juny.dddstart2.eventstore.api.EventStore;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventApi {
  private EventStore eventStore;

  public EventApi(EventStore eventStore) {
    this.eventStore = eventStore;
  }

  @RequestMapping(value = "/api/events", method = RequestMethod.GET)
  public List<EventEntry> list(
      @RequestParam("offset") Long offset,
      @RequestParam("limit") Long limit) {
    return eventStore.get(offset, limit);
  }
}

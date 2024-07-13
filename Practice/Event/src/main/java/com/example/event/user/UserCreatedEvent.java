package com.example.event.user;

public record UserCreatedEvent(
  String email,
  String recommender) {
}

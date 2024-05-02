package com.juny.core.ch04;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class UrlNotAccessibleFailureAnalyzer extends AbstractFailureAnalyzer<UrlNotAccessibleException> {

  @Override
  protected FailureAnalysis analyze(Throwable rootFailure, UrlNotAccessibleException cause) {
    return new FailureAnalysis(
        "Unable to access the URL=" + cause.getUrl(),
        "Validate the URL and ensure it is accessible",
        cause);
  }
}

package com.juny.batchtest.domain.monitoring.sale;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChartController {

  @GetMapping("/admin/chart")
  public String showChart() {

    return "chartView";
  }
}

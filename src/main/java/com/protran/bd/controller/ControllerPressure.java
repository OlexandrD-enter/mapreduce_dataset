package com.protran.bd.controller;

import com.protran.bd.DateTransporter;
import com.protran.bd.Type;
import com.protran.bd.converter.CopyToLocal;
import com.protran.bd.converter.TxtToCsv;
import com.protran.bd.pressure.day.PressureDayDriver;
import com.protran.bd.pressure.day.PressureDayMapper;
import com.protran.bd.pressure.month.PressureMonthDriver;
import com.protran.bd.pressure.week.PressureWeekDriver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.format.DateTimeFormatter;

@Controller
public class ControllerPressure {
    @GetMapping("/pressure")
    public String temperaturePage(Model model){
        model.addAttribute("pressTrans", new DateTransporter());
        return "/pressure";
    }

    @PostMapping("/analyze_pressure")
    public String analyzeTemperature(@ModelAttribute("pressTrans") DateTransporter dateTransporter,
                                     BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "/pressure";
        }
        String [] args = new String[]{CONSTANTS.INPUT_FILE_DIR, CONSTANTS.OUTPUT_FILE_DIR};
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String before = dateTransporter.getDateStart().format(formatter);
        String after = dateTransporter.getDateEnd().format(formatter);
        PressureDayMapper pressureDayMapper = PressureDayMapper.getInstance();
        pressureDayMapper.setUserDateBefore(before);
        pressureDayMapper.setUserDateAfter(after);
        if(dateTransporter.getType().equals(Type.day)){
            PressureDayDriver.main(args);
            CopyToLocal.copyToLocalPress(Type.day.name());
            TxtToCsv.converterPress(Type.day.name());
        }else if(dateTransporter.getType().equals(Type.week)){
            PressureWeekDriver.main(args);
            CopyToLocal.copyToLocalPress(Type.week.name());
            TxtToCsv.converterPress(Type.week.name());
        }else if(dateTransporter.getType().equals(Type.month)){
            PressureMonthDriver.main(args);
            CopyToLocal.copyToLocalPress(Type.month.name());
            TxtToCsv.converterPress(Type.month.name());
        }
        return "redirect:/pressure?success";
    }
}

package com.protran.bd.controller;

import com.protran.bd.DateTransporter;
import com.protran.bd.Type;
import com.protran.bd.converter.CopyToLocal;
import com.protran.bd.converter.TxtToCsv;
import com.protran.bd.pressure.day.PressureDayDriver;
import com.protran.bd.pressure.day.PressureDayMapper;
import com.protran.bd.temperature.day.TempDayDriver;
import com.protran.bd.temperature.day.TempDayMapper;
import com.protran.bd.temperature.month.TempMonthDriver;
import com.protran.bd.temperature.week.TempWeekDriver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.format.DateTimeFormatter;

@Controller
public class ControllerTemperature {

    @GetMapping("/temperature")
    public String temperaturePage(Model model){
        model.addAttribute("tempTrans", new DateTransporter());
        return "/temperature";
    }

    @PostMapping("/analyze_temp")
    public String analyzeTemperature(@ModelAttribute("tempTrans") DateTransporter dateTransporter,
                                     BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "/temperature";
        }
        String [] args = new String[]{CONSTANTS.INPUT_FILE_DIR, CONSTANTS.OUTPUT_FILE_DIR};
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String before = dateTransporter.getDateStart().format(formatter);
        String after = dateTransporter.getDateEnd().format(formatter);
        TempDayMapper tempDayMapper = TempDayMapper.getInstance();
        tempDayMapper.setUserDateBefore(before);
        tempDayMapper.setUserDateAfter(after);
        if(dateTransporter.getType().equals(Type.day)){
            TempDayDriver.main(args);
            CopyToLocal.copyToLocalTemp(Type.day.name());
            TxtToCsv.converterTemp(Type.day.name());
        }else if(dateTransporter.getType().equals(Type.week)){
            TempWeekDriver.main(args);
            CopyToLocal.copyToLocalTemp(Type.week.name());
            TxtToCsv.converterTemp(Type.week.name());
        }else if(dateTransporter.getType().equals(Type.month)){
            TempMonthDriver.main(args);
            CopyToLocal.copyToLocalTemp(Type.month.name());
            TxtToCsv.converterTemp(Type.month.name());
        }
        return "redirect:/temperature?success";
    }
}

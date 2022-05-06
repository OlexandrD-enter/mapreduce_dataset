package com.protran.bd.controller;

import com.protran.bd.DateTransporter;
import com.protran.bd.Type;
import com.protran.bd.converter.CopyToLocal;
import com.protran.bd.converter.TxtToCsv;
import com.protran.bd.dew_point.day.DewDayDriver;
import com.protran.bd.dew_point.day.DewDayMapper;
import com.protran.bd.dew_point.month.DewMonthDriver;
import com.protran.bd.dew_point.week.DewWeekDriver;
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
public class ControllerDewPoint {
    @GetMapping("/dew_point")
    public String temperaturePage(Model model){
        model.addAttribute("dewTrans", new DateTransporter());
        return "/dew_point";
    }

    @PostMapping("/analyze_dew_point")
    public String analyzeTemperature(@ModelAttribute("dewTrans") DateTransporter dateTransporter,
                                     BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "/dew_point";
        }
        String [] args = new String[]{CONSTANTS.INPUT_FILE_DIR, CONSTANTS.OUTPUT_FILE_DIR};
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        String before = dateTransporter.getDateStart().format(formatter);
        String after = dateTransporter.getDateEnd().format(formatter);
        DewDayMapper dewDayMapper = DewDayMapper.getInstance();
        dewDayMapper.setUserDateBefore(before);
        dewDayMapper.setUserDateAfter(after);
        if(dateTransporter.getType().equals(Type.day)){
            DewDayDriver.main(args);
            CopyToLocal.copyToLocalDew(Type.day.name());
            TxtToCsv.converterDew(Type.day.name());
        }else if(dateTransporter.getType().equals(Type.week)){
            DewWeekDriver.main(args);
            CopyToLocal.copyToLocalDew(Type.week.name());
            TxtToCsv.converterDew(Type.week.name());
        }else if(dateTransporter.getType().equals(Type.month)){
            DewMonthDriver.main(args);
            CopyToLocal.copyToLocalDew(Type.month.name());
            TxtToCsv.converterDew(Type.month.name());
        }
        return "redirect:/pressure?success";
    }
}

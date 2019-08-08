package com.ruoyi.web.controller.tic;

import com.ruoyi.web.core.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tic")
public class TicController extends BaseController {

    @GetMapping("/main")
    public String main(ModelMap mmap) {
        return "/tic/tic_main";
    }

    @GetMapping("/station")
    public String station(ModelMap mmap) {
        return "/tic/tic_station";
    }

    @GetMapping("/record")
    public String record(ModelMap mmap) {
        return "/tic/tic_record";
    }


    @GetMapping("/weight")
    public String weight(ModelMap mmap) {
        return "/tic/tic_weight";
    }
}

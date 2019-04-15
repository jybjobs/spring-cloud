package com.rkhd.sre.actuator.info;


import com.rkhd.sre.actuator.utils.PropertSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InfoController {

    @Autowired
    PropertSettings propertSettings;

    @RequestMapping("/info")
    @ResponseBody
    public Info info() {
        return new Info.Builder().withDetail("name",propertSettings.getName()).withDetail("version",propertSettings.getVersion()).build();
    }
}

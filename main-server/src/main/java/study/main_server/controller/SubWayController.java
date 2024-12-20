package study.main_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import study.main_server.dto.UserInfo;
import study.main_server.service.SubWayService;

@RestController
@RequiredArgsConstructor
public class SubWayController {

    private final SubWayService subWayService;

    @PostMapping("/subway")
    public void getSubWayInfo(@RequestParam("station") String station,
                              @RequestBody UserInfo userInfo) {

        subWayService.getSubWayInfo(station, userInfo);

    }

}

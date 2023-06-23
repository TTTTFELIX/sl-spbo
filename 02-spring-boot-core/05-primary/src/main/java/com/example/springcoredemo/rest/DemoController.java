package com.example.springcoredemo.rest;

import com.example.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

//    Define a private field for the dependency
    private Coach myCoach;

//    Define a Consturtion for a dependency injection
//    qualifier has a higher priority than the primary
//    only one primary component
//    this will use trackCoach as our main coach instead of baseballCoach
    @Autowired
    public void DemoController(@Qualifier("trackCoach") Coach theCoach){
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }

}

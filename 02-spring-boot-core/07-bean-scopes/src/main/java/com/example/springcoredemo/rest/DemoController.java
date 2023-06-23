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

    private Coach anotherCoach;

//    Define a Consturtion for a dependency injection
//    specific "cricketCoach" as the main one (use lowercase for the first letter)
    @Autowired
    public void DemoController(
            @Qualifier("cricketCoach") Coach theCoach,
            @Qualifier("cricketCoach") Coach theAnotherCoach){

        System.out.println("In constructor: " + getClass().getSimpleName());

        myCoach = theCoach;
        anotherCoach = theAnotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/check")
    public String check(){
//        Singleton return True, Prototype return False
        return "The compression beans with myCoach == myAnotherCoach: " + (myCoach == anotherCoach);
    }

}

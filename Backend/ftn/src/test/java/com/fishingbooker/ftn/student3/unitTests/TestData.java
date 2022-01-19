package com.fishingbooker.ftn.student3.unitTests;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.users.FishingInstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestData {

    public static List<Adventure> getAdventures(){
        Adventure adventure=new Adventure();
        adventure.setDeleted(false);
        adventure.setPrice(150.0);
        adventure.setInstructor(generateInstructors().get(0));
        adventure.setGuestLimit(10);
        adventure.setName("Arctic Fishing");
        adventure.setCancellationPercentageKeep(10.0);
        adventure.setDescription("Great adventure");
        adventure.setRating(4.2);
        List<Adventure> adventures=Arrays.asList(adventure);
        return adventures;


    }

    public static List<FishingInstructor> generateInstructors(){
        FishingInstructor instructor=new FishingInstructor();
        instructor.setDeleted(false);
        instructor.setName("Mark");
        instructor.setLastName("Kurz");
        instructor.setId(1l);
        instructor.setBiography("Lorem ipsum");
        instructor.setPhone("123");
        instructor.setPassword("123");
        instructor.setEnabled(true);
        instructor.setRating(5.0);

        FishingInstructor instructor1=new FishingInstructor();
        instructor1.setDeleted(false);
        instructor1.setName("Marko");
        instructor.setLastName("Kurzawa");
        instructor.setId(2l);
        instructor.setBiography("Lorem ipsum");
        instructor.setPhone("123");
        instructor.setPassword("123");
        instructor.setEnabled(true);
        instructor.setRating(5.0);

        List<FishingInstructor> instructors= Arrays.asList(instructor,instructor1);
        return instructors;
    }
}

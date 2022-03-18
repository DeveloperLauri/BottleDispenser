package com.example.l82;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BottleDispenser{
    private int bottles;
    private ArrayList<Bottle> bottle_array;
    private double money;
    private String tmp;
    private TextView text2;

    //Singleton pattern
    private static final BottleDispenser bd = new BottleDispenser();

    private BottleDispenser() {
        System.out.println("testi");
        bottles = 5;
        money = 0;

        bottle_array = new ArrayList<Bottle>();
        String n, m;
        double e, s, p;

        for(int i = 0; i<bottles; i++) {
            if(i<2) {
                n = "Pepsi Max";
                m = "Pepsi";
                e = 0.3;
                if(i<1) {
                    s = 0.5;
                    p = 1.80;
                }
                else {
                    s = 1.5;
                    p = 2.20;
                }
            }
            else if(i < 4) {
                n = "Coca-Cola Zero";
                m = "Coca-Cola";
                e = 0.4;
                if(i<3) {
                    s = 0.5;
                    p = 2.00;
                }
                else {
                    s = 1.5;
                    p = 2.50;
                }
            }
            else {
                n = "Fanta Zero";
                m = "Coca-Cola";
                e = 0.5;
                s = 0.5;
                p = 1.95;
            }
            bottle_array.add(i, new Bottle(n, m, e, s, p));
        }
    }

    //singleton pattern:
    //static class method (?)
    public static BottleDispenser getInstance(){
        return bd;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public String buyBottle(int b_index) {
        Bottle b = bottle_array.get(b_index-1);
        if(bottles <= 0) {
            return "No more bottles!";
        }
        else if(money < b.getPrize()) {
            return "Add money first!";
        }
        else {
            bottles -= 1;
            money -= b.getPrize();
            deleteBottle(b);
            return "KACHUNK! " + b.getName() + " came out of the dispenser!";
        }
    }

    //NOTE! List of bottles printed to terminal, must be changed so that list is printed to
    //screen
    public void listBottles() {
        for(int i = 0; i < bottles; i++) {
            System.out.println(i+1 + ". Name: " + bottle_array.get(i).getName());
            System.out.print("\tSize: " + bottle_array.get(i).getSize());
            System.out.println("\tPrice: " + bottle_array.get(i).getPrize());
        }
    }

    private void deleteBottle(Bottle b) {
        bottle_array.remove(b);
    }

    public String returnMoney() {
        tmp = Double.toString(money);
        money = 0;
        return "Money returned " + tmp + " eur!";
    }
}

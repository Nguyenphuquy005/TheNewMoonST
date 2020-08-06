package com.example.demo.multithreading.ticketAirprogram;

import java.util.Scanner;

public class TicketAir {
    private String id ;
    private  String name;
    private  String date ;
    private String bag ;
    private int price ;

      public void  input(){
          Scanner input = new Scanner(System.in);
          System.out.println("Nhap Id");
          id = input.nextLine();
          System.out.println("Nhap Name");
          name = input.nextLine();
          System.out.println("Nhap Ngay");
          date = input.nextLine();
          System.out.println("Nhap Hnah ly ky gui");
          bag = input.nextLine();
          System.out.println("Nhap gia");
          price = Integer.parseInt(input.nextLine());
      }
    @Override
    public String toString() {
        return "TicketAir{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", bag='" + bag + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
    public void  display(){
        System.out.println(toString());
    }
    public TicketAir() {
    }

    public TicketAir(String id, String name, String date, String bag, int price) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.bag = bag;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBag() {
        return bag;
    }

    public void setBag(String bag) {
        this.bag = bag;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

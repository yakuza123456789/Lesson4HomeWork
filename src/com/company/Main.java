package com.company;


import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 30;
    public static String bossDeffenceType = "";

    public static int[] heroesHealth = {250, 250, 250, 250, 250};
    public static int[] heroesDamage = {20, 20, 20, 20};
    public static String[] heroesAttackType = {"Physikal", "Magical", "Mental", "Treatment", "Thor",};

    public static void main(String[] args) {
        printStatistic();
        while (!isFinished()) {
            round();
        }
    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        bossHit();
        treatmentOfTheHeroes();
        printStatistic();
    }

    public static void changeBossDefence() {
        Random r = new Random();
        int randomIndex = r.nextInt(heroesAttackType.length);
        bossDeffenceType = heroesAttackType[randomIndex];
    }

    public static boolean isFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes  won!!!");
            return true;
        }
        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;
    }

    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                Random r = new Random();
                bossDamage = r.nextInt(110);
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }


    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length - 1; i++) {
            if (heroesHealth[i] > 0) {
                if (bossDeffenceType.equals(heroesAttackType[i])) {
                    Random r = new Random();
                    int coef = r.nextInt(6) + 2;  //2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coef < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coef;
                    }
                    System.out.println(heroesAttackType[i] +
                            " critically hit " + heroesDamage[i] * coef);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }


    public static void treatmentOfTheHeroes() {
        Random random = new Random();
        int r = random.nextInt(4);
        if (heroesHealth[r] > 0 && heroesHealth[r] <= 100) {
            heroesHealth[r] = heroesHealth[r] + heroesDamage[3] * r;
            System.out.println("cured of the player " + r);

        }
    }



    public static void printStatistic() {
        System.out.println("________________");
        System.out.println("Boss health " + bossHealth);
        System.out.println("Warrior health " + heroesHealth[0]);
        System.out.println("Magic health " + heroesHealth[1]);
        System.out.println("Kinetik health " + heroesHealth[2]);
        System.out.println("Doctor health " + heroesHealth[3]);
        System.out.println("________________");
    }
}
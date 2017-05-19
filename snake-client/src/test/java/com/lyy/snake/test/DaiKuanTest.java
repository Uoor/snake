package com.lyy.snake.test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by fanshuai on 17/5/10.
 */
public class DaiKuanTest {


    @Test
    public void daikuan(){
        List<Product> allProduct = Lists.newArrayList(
                new Product(1, "招商", "不限", 100000, 60, 5.3d, false, 2)
                , new Product(2, "工商", "宝马,宝俊", 200000, 60, 5.6d, false, 7)
                , new Product(3, "陆金所", "宝马,宝俊", 500000, 60, 7.5d, true, 1)
                , new Product(4, "支付宝", "不限", 100000, 60, 6.5d, true, 3)
                , new Product(5, "微信", "宝马,宝俊", 300000, 60, 2.5d, true, 5)
                , new Product(6, "银行", "不限", 300000, 60, 3.5d, false, 4)
                , new Product(7, "宝马主机场", "宝马", 300000, 60, 3.5d, false, 4)
        );
        Car cat = new Car("宝马a",500000,100000,36);
        List<Product> canntUse = new ArrayList<Product>();
        for (Product p:allProduct){
            if (p.getBrand().equals("不限")){
                continue;
            }
            if (p.getBrand().contains(cat.brand)){
                continue;
            }
            canntUse.add(p);
        }
        allProduct.removeAll(canntUse);
        Plan p1 = getPlan(cat,allProduct,"最省钱");
        Plan p2 = getPlan(cat,allProduct,"最快");
        Plan p3 = getPlan(cat,allProduct,"最便捷");
        List<Plan> onePrudoctPlan = getOneProductPlan(cat,allProduct);
        System.out.println("");
    }

    private List<Plan> getOneProductPlan(Car cat, List<Product> allProduct) {
        List<Plan> plans = new ArrayList<Plan>();
        int money = cat.money;
        for (Product pro:allProduct){
            int maxMoney = pro.getMaxMoney();//最大总额
            int rateMaxMoney = pro.getMaxRatio()*cat.allMoney/100;//按比例最大总额
            int productMaxMoney = maxMoney>rateMaxMoney?rateMaxMoney:maxMoney;
            if (money<=productMaxMoney){
                Plan plan = new Plan();
                List<PlanItem> itemList = new ArrayList<PlanItem>();
                PlanItem item = new PlanItem();
                item.money = productMaxMoney;
                item.product = pro;
                itemList.add(item);
                plan.car = cat;
                plan.planItems = itemList;
                plan.type="一家公司全代";
                plans.add(plan);
            }
        }
        return plans;
    }

    public Plan getPlan(Car cat,List<Product> allProduct,String type){
        //最省钱
        if ("最省钱".equals(type)){
            Collections.sort(allProduct, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    if (o1.getRate() == o2.getRate()) {
                        return 0;
                    }
                    if (o1.getRate() > o2.getRate()) {
                        return 1;
                    } else {
                        return -1;
                    }

                }
            });
        }else if ("最快".equals(type)){
            Collections.sort(allProduct, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    if (o1.getDays()==o2.getDays()){
                        return 0;
                    }
                    if (o1.getDays()>o2.getDays()){
                        return 1;
                    }else {
                        return -1;
                    }

                }
            });
        }else if ("最便捷".equals(type)){
            Collections.sort(allProduct, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    if (o1.getRate()==o2.getRate()){
                        return 0;
                    }
                    if (o1.getRate()>o2.getRate()){
                        return 1;
                    }else {
                        return -1;
                    }

                }
            });
            Collections.sort(allProduct, new Comparator<Product>() {
                @Override
                public int compare(Product o1, Product o2) {
                    if (o1.isOnline() && o1.isOnline()){
                        return 0;
                    }
                    if (o1.isOnline()){
                        return -1;
                    }
                    return 1;

                }
            });
        }else {
            return null;
        }
        Plan p = new Plan();
        p.car = cat;
        p.type=type;
        int money = cat.money;
        List<PlanItem> itemList = new ArrayList<PlanItem>();
        for (Product pro:allProduct){
            if (money<=0){
                break;
            }
            int maxMoney = pro.getMaxMoney();//最大总额
            int rateMaxMoney = pro.getMaxRatio()*cat.allMoney/100;//按比例最大总额
            int productMaxMoney = maxMoney>rateMaxMoney?rateMaxMoney:maxMoney;
            PlanItem item = new PlanItem();
            int curProductDaiMoney = 0;
            if (money<=productMaxMoney){
                curProductDaiMoney = money;
            }else {
                curProductDaiMoney = productMaxMoney;
            }
            money = money - curProductDaiMoney;
            item.money = curProductDaiMoney;
            item.product = pro;
            itemList.add(item);
        }
        p.planItems = itemList;
        return p;
    }
    public static class Plan{
        String type;
        Car car;
        List<PlanItem> planItems;
    }
    public static class PlanItem{
        Product product;
        int money;
    }
    public static class Car{
        public String brand;
        public int allMoney;
        public int money;
        int monthNum;

        public Car(String brand, int allMoney, int money, int monthNum) {
            this.brand = brand;
            this.allMoney = allMoney;
            this.money = money;
            this.monthNum = monthNum;
        }
    }
    public static class Product{
        public String name;
        public int id;
        public String brand;
        public int maxMoney;
        public int maxRatio;
        public double rate;
        public boolean online;
        public int days;

        public Product(int id,String name,String brand, int maxMoney, int maxRatio, double rate, boolean online, int days) {
            this.id = id;
            this.name = name;
            this.brand = brand;
            this.maxMoney = maxMoney;
            this.maxRatio = maxRatio;
            this.rate = rate;
            this.online = online;
            this.days = days;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getMaxMoney() {
            return maxMoney;
        }

        public void setMaxMoney(int maxMoney) {
            this.maxMoney = maxMoney;
        }

        public int getMaxRatio() {
            return maxRatio;
        }

        public void setMaxRatio(int maxRatio) {
            this.maxRatio = maxRatio;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public boolean isOnline() {
            return online;
        }

        public void setOnline(boolean online) {
            this.online = online;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }
    }
}

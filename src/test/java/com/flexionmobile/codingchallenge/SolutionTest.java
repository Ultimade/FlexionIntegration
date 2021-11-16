package com.flexionmobile.codingchallenge;

import com.flexionmobile.codingchallenge.integration.Purchase;
import com.flexionmobile.codingchallenge.solution.IntegrationImpl;
import com.flexionmobile.codingchallenge.solution.PurchaseImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SolutionTest {


    @Test
    public void buyTest(){

        boolean isOk;

        //the response need to be a purchase object (problem in this test case that itemId created when api called, so buy method not fully testable)
        isOk = this.buyTest("item1") != null;
        Assert.assertTrue(isOk);
    }


    @Test
    public void buyFailTest(){

        boolean isOk;

        //the response need to be a null. itemId cannot be empty or null
        isOk = this.buyTest("") == null;
        Assert.assertTrue(isOk);
    }

    @Test
    public void testGetPurchase(){

        boolean isOk = true;
        IntegrationImpl integration = new IntegrationImpl();

        List<Purchase> purchases = integration.getPurchases();
        if (purchases == null){
            this.buyTest();
            purchases = integration.getPurchases();
            if (purchases == null){
                isOk = false;
            }
        }
        Assert.assertTrue(isOk);
    }

    @Test
    public void testConsume(){

        boolean isOk = false;
        IntegrationImpl integration = new IntegrationImpl();

        Purchase purchase = this.buyTest("item1");
        if(purchase != null){
            integration.consume(purchase);
        }
        List<Purchase> pList = integration.getPurchases();
        for (Purchase p: pList
             ) {
            assert purchase != null;
            if (p.getId().equals(purchase.getId()) && p.getConsumed()){
                isOk = true;
                break;
            }
        }
        Assert.assertTrue(isOk);
    }

    private Purchase buyTest(String var1){

        //the response need to be a purchase object
        IntegrationImpl integration = new IntegrationImpl();
        return integration.buy(var1);
    }

}

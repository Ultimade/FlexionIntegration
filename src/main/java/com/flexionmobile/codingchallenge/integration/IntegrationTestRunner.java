//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.flexionmobile.codingchallenge.integration;

import java.util.Iterator;
import java.util.List;

public class IntegrationTestRunner {
    public IntegrationTestRunner() {
    }

    public void runTests(Integration integration) {
        if (integration == null) {
            throw new IllegalStateException("test failed: you must pass an instance of Integration");
        } else {
            Purchase purchase = integration.buy("item1");
            if (purchase == null) {
                throw new IllegalStateException("test failed: buy did not result in a purchase");
            } else if (purchase.getConsumed()) {
                throw new IllegalStateException("test failed: purchase should not be marked as consumed before consumption");
            } else {
                try {
                    integration.consume(purchase);
                } catch (Exception var8) {
                    throw new IllegalStateException("test failed: failed to consume purchase", var8);
                }

                String alreadyConsumedPurchaseId = purchase.getId();
                if (alreadyConsumedPurchaseId != null && alreadyConsumedPurchaseId.length() != 0) {
                    List<Purchase> purchases = integration.getPurchases();
                    Iterator i$ = purchases.iterator();

                    while(i$.hasNext()) {
                        Purchase p = (Purchase)i$.next();
                        String purchaseId = p.getId();
                        if (purchaseId == null || purchaseId.length() == 0) {
                            throw new IllegalStateException("test failed: the returned purchase id is blank");
                        }

                        if (!p.getConsumed()) {
                            if (alreadyConsumedPurchaseId.equals(purchaseId)) {
                                throw new IllegalStateException("test failed: purchase with id '" + purchaseId + "' should already be consumed");
                            }

                            integration.consume(p);
                        }
                    }

                } else {
                    throw new IllegalStateException("test failed: the returned purchase id is blank");
                }
            }
        }
    }
}

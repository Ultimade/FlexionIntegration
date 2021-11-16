package com.flexionmobile.codingchallenge.solution;

import com.flexionmobile.codingchallenge.integration.Purchase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseImpl implements Purchase {

    private String id;
    private boolean consumed;
    private String itemId;

    @Override
    public boolean getConsumed() {
        return this.consumed;
    }
}
